package org.knowm.xchange.huobi;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderStatus;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.account.Balance;
import org.knowm.xchange.dto.account.FundingRecord;
import org.knowm.xchange.dto.account.FundingRecord.Status;
import org.knowm.xchange.dto.account.OpenPosition;
import org.knowm.xchange.dto.account.Wallet;
import org.knowm.xchange.dto.marketdata.Ticker;
import org.knowm.xchange.dto.marketdata.Trades.TradeSortType;
import org.knowm.xchange.dto.meta.CurrencyMetaData;
import org.knowm.xchange.dto.meta.CurrencyPairMetaData;
import org.knowm.xchange.dto.meta.ExchangeMetaData;
import org.knowm.xchange.dto.meta.FeeTier;
import org.knowm.xchange.dto.meta.WalletHealth;
import org.knowm.xchange.dto.trade.FuturesOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.huobi.dto.account.HuobiBalance;
import org.knowm.xchange.huobi.dto.account.HuobiBalanceRecord;
import org.knowm.xchange.huobi.dto.account.HuobiBalanceSum;
import org.knowm.xchange.huobi.dto.account.HuobiFundingRecord;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAllTicker;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAsset;
import org.knowm.xchange.huobi.dto.marketdata.HuobiAssetPair;
import org.knowm.xchange.huobi.dto.marketdata.HuobiCurrency;
import org.knowm.xchange.huobi.dto.marketdata.HuobiCurrencyWrapper;
import org.knowm.xchange.huobi.dto.marketdata.HuobiTicker;
import org.knowm.xchange.huobi.dto.trade.HuobiOrder;
import org.knowm.xchange.huobi.dto.trade.OrderSide;
import org.knowm.xchange.huobi.dto.trade.results.PositionResultData;

public class HuobiAdapters {
	
  private static final String ONLINE = "allowed";
  private static final String DELISTED = "delisted";
  private static final String TARGET_NETWORK = "ERC20";
  private static BigDecimal fee = new BigDecimal("0.002"); // Trading fee at Huobi is 0.2 %

  public static Ticker adaptTicker(HuobiTicker huobiTicker, CurrencyPair currencyPair) {
    Ticker.Builder builder = new Ticker.Builder();
    builder.open(huobiTicker.getOpen());
    builder.ask(huobiTicker.getAsk().getPrice());
    builder.bid(huobiTicker.getBid().getPrice());
    builder.last(huobiTicker.getClose());
    builder.high(huobiTicker.getHigh());
    builder.low(huobiTicker.getLow());
    builder.quoteVolume(huobiTicker.getVol());
    builder.timestamp(huobiTicker.getTs());
    builder.currencyPair(currencyPair);
    return builder.build();
  }
  

  public static List<Ticker> adaptAllTickers(HuobiAllTicker[] allTickers) {

    return Arrays.stream(allTickers)
        .filter(
            huobiTicker ->
                !"hb10".equals(huobiTicker.getSymbol()) // Fix on data error retrieved from api
            )
        .map(
            huobiTicker ->
                new Ticker.Builder()
                    .currencyPair(adaptCurrencyPair(huobiTicker.getSymbol()))
                    .open(huobiTicker.getOpen())
                    .ask(huobiTicker.getAsk().getPrice())
                    .bid(huobiTicker.getBid().getPrice())
                    .askSize(huobiTicker.getAsk().getVolume())
                    .bidSize(huobiTicker.getBid().getVolume())
                    .last(huobiTicker.getClose())
                    .high(huobiTicker.getHigh())
                    .low(huobiTicker.getLow())
                    .quoteVolume(huobiTicker.getVol())
                    .timestamp(huobiTicker.getTs())
                    .build())
        .collect(Collectors.toList());
  }

  static ExchangeMetaData adaptToExchangeMetaData(
      HuobiAssetPair[] assetPairs,
      HuobiAsset[] assets,
      ExchangeMetaData staticMetaData,
      HuobiCurrencyWrapper[] currencyWrapper) {

    HuobiUtils.setHuobiAssets(assets);
    HuobiUtils.setHuobiAssetPairs(assetPairs);

    Map<CurrencyPair, CurrencyPairMetaData> pairsMetaData = staticMetaData.getCurrencyPairs();
    Map<CurrencyPair, CurrencyPairMetaData> pairs = new HashMap<>();
    for (HuobiAssetPair assetPair : assetPairs) {
      CurrencyPair pair = adaptCurrencyPair(assetPair.getKey());
      pairs.put(pair, adaptPair(assetPair, pairsMetaData.getOrDefault(pair, null)));
    }

    Map<Currency, CurrencyMetaData> currencies = new HashMap<>();
    for (HuobiCurrencyWrapper huobiCurrencyWrapper : currencyWrapper) {
      if (huobiCurrencyWrapper.getHuobiCurrencies().length != 0) {
        boolean isDelisted = DELISTED.equals(huobiCurrencyWrapper.getInstStatus());
        CurrencyMetaData currencyMetaData = adaptCurrencyMetaData(huobiCurrencyWrapper, isDelisted);
        Currency currency =
            HuobiUtils.translateHuobiCurrencyCode(huobiCurrencyWrapper.getCurrency());
        currencies.put(currency, currencyMetaData);
      }
    }

    return new ExchangeMetaData(pairs, currencies, null, null, false);
  }

  private static CurrencyMetaData adaptCurrencyMetaData(
      HuobiCurrencyWrapper huobiCurrencyWrapper, boolean isDelisted) {
    CurrencyMetaData result = null;
    List<HuobiCurrency> huobiCurrencies = Arrays.asList(huobiCurrencyWrapper.getHuobiCurrencies());
    if (!huobiCurrencies.isEmpty()) {
      result = getCurrencyMetaData(huobiCurrencies.get(0), isDelisted);
      for (HuobiCurrency huobiCurrency : huobiCurrencies) {
        if (TARGET_NETWORK.equals(huobiCurrency.getDisplayName())) {
          result = getCurrencyMetaData(huobiCurrency, isDelisted);
          break;
        }
      }
    }
    return result;
  }

  private static CurrencyMetaData getCurrencyMetaData(
      HuobiCurrency huobiCurrency, boolean isDelisted) {
    int withdrawPrecision = huobiCurrency.getWithdrawPrecision();
    BigDecimal transactFeeWithdraw = new BigDecimal(huobiCurrency.getTransactFeeWithdraw());
    BigDecimal minWithdrawAmt = new BigDecimal(huobiCurrency.getMinWithdrawAmt());
    WalletHealth walletHealthStatus =
        isDelisted ? WalletHealth.OFFLINE : getWalletHealthStatus(huobiCurrency);
    return new CurrencyMetaData(
        withdrawPrecision, transactFeeWithdraw, minWithdrawAmt, walletHealthStatus);
  }

  private static WalletHealth getWalletHealthStatus(HuobiCurrency huobiCurrency) {
    WalletHealth walletHealth = WalletHealth.ONLINE;
    if (!ONLINE.equals(huobiCurrency.getDepositStatus())
        && !ONLINE.equals(huobiCurrency.getWithdrawStatus())) {
      walletHealth = WalletHealth.OFFLINE;
    } else if (!ONLINE.equals(huobiCurrency.getDepositStatus())) {
      walletHealth = WalletHealth.DEPOSITS_DISABLED;
    } else if (!ONLINE.equals(huobiCurrency.getWithdrawStatus())) {
      walletHealth = WalletHealth.WITHDRAWALS_DISABLED;
    }
    return walletHealth;
  }

  protected static CurrencyPair adaptCurrencyPair(String currencyPair) {
    return HuobiUtils.translateHuobiCurrencyPair(currencyPair);
  }

  private static CurrencyPairMetaData adaptPair(
      HuobiAssetPair pair, CurrencyPairMetaData metadata) {
    BigDecimal minQty =
        metadata == null
            ? null
            : metadata.getMinimumAmount().setScale(pair.getAmountPrecision(), RoundingMode.DOWN);
    FeeTier[] feeTiers = metadata == null ? null : metadata.getFeeTiers();
    return new CurrencyPairMetaData(
        fee,
        minQty,
        null,
        null,
        null,
        new Integer(pair.getAmountPrecision()),
        new Integer(pair.getPricePrecision()),
        null,
        feeTiers,
        null,
        null,
        true);
  }

  private static Currency adaptCurrency(String currency) {
    return HuobiUtils.translateHuobiCurrencyCode(currency);
  }

  public static Wallet adaptWallet(Map<String, HuobiBalanceSum> huobiWallet) {
    List<Balance> balances = new ArrayList<>(huobiWallet.size());
    for (Map.Entry<String, HuobiBalanceSum> record : huobiWallet.entrySet()) {
      try {
        Currency currency = adaptCurrency(record.getKey());
        if (currency == null) {
          // Avoid creating Balance objects with null currency.
          continue;
        }
        Balance balance =
            new Balance(
                currency,
                record.getValue().getTotal(),
                record.getValue().getAvailable(),
                record.getValue().getFrozen());
        balances.add(balance);
      } catch (ExchangeException e) {
        // It might be a new currency. Ignore the exception and continue with other currency.
      }
    }
    return Wallet.Builder.from(balances).build();
  }

  public static Map<String, HuobiBalanceSum> adaptBalance(List<HuobiBalance> list) {
		Map<String, HuobiBalanceSum> map = new HashMap<>();
		HuobiAsset[] huobiAssets = new HuobiAsset[list.size()];
		int counter = 0;
		for (HuobiBalance record : list) {
			huobiAssets[counter] = new HuobiAsset(record.getSymbol());
			counter++;
			
			HuobiBalanceSum sum = map.get(record.getSymbol());
			if (sum == null) {
				sum = new HuobiBalanceSum();
				map.put(record.getSymbol(), sum);
			}
//      if (record.getType().equals("trade")) {
			sum.setAvailable(record.getMarginAvailable());
//      } else if (record.getType().equals("frozen")) {
			sum.setFrozen(record.getMarginFrozen());
//      }
		}
		HuobiUtils.setHuobiAssets(huobiAssets);
		return map;
  }

  public static OpenOrders adaptOpenOrders(HuobiOrder[] openOrders) {
    List<FuturesOrder> limitOrders = new ArrayList<>();
    for (HuobiOrder openOrder : openOrders) {
        FuturesOrder order = (FuturesOrder) adaptOrder(openOrder);
        limitOrders.add(order);
    }
    return new OpenOrders(new ArrayList<>(), limitOrders);
  }

  public static Order adaptOrder(HuobiOrder openOrder) {
	  Order order = null;
		OrderType orderType = adaptOrderType(openOrder.getDirection());
		CurrencyPair currencyPair = adaptCurrencyPair(openOrder.getSymbol()+"/"+openOrder.getContractCode());
		OrderStatus orderStatus = adaptOrderStatus(openOrder.getStatus());
		BigDecimal cumulativeAmount = openOrder.getTradeTurnover();
		BigDecimal openOrderAvgPrice = openOrder.getTradeAvgPrice();
		Date timestamp=new Date(openOrder.getCreatedAt());
		order = new FuturesOrder.Builder(orderType, currencyPair)
					.averagePrice(openOrder.getTradeAvgPrice())
					.contractCode(openOrder.getContractCode())
					.contractType(openOrder.getContractType())
					.cumulativeAmount(cumulativeAmount)
					.fee(openOrder.getFee())
					.leverRate(openOrder.getLeverRate())
					.offset(openOrder.getOffset())
					.orderPriceType(openOrder.getOrderPriceType())
					.orderStatus(orderStatus)
					.orderType(orderType)
					.timestamp(timestamp)
					.originalAmount(BigDecimal.valueOf(openOrder.getVolume()))
					.price(openOrder.getPrice())
					.userReference(openOrder.getClientOrderId())
					.build();

    order.setAveragePrice(openOrderAvgPrice);
    return order;
  }

  /**
   * Huobi currently doesn't have trade history API. We simulate it by using the orders history.
   *
   * @param order
   * @return
   */
  private static UserTrade adaptTrade(Order order) {
    BigDecimal feeAmount =
        order.getFee();
    return new UserTrade.Builder()
        .type(order.getType())
        .originalAmount(order.getCumulativeAmount())
        .currencyPair(order.getCurrencyPair())
        .price(order.getAveragePrice())
        .timestamp(order.getTimestamp())
        .id(order.getId()) // Trade id
        .orderId(order.getId()) // Original order id
        .feeAmount(feeAmount)
        .feeCurrency(order.getCurrencyPair().counter)
        .build();
  }

  protected static OrderStatus adaptOrderStatus(int huobiStatus) {
    OrderStatus result = OrderStatus.UNKNOWN;
    switch (huobiStatus) {
      case 1:
        result = OrderStatus.PENDING_NEW;
        break;
      case 2:
        result = OrderStatus.PENDING_NEW;
        break;
      case 3:
        result = OrderStatus.NEW;
        break;
      case 4:
        result = OrderStatus.PARTIALLY_FILLED;
        break;
      case 5:
        result = OrderStatus.PARTIALLY_CANCELED;
        break;
      case 6:
        result = OrderStatus.FILLED;
        break;
      case 7:
        result = OrderStatus.CANCELED;
        break;
    }
    return result;
  }

  public static OrderType adaptOrderType(String orderType) {
    if (orderType.startsWith("buy")) {
      return OrderType.BID;
    }
    if (orderType.startsWith("sell")) {
      return OrderType.ASK;
    }
    return null;
  }

  public static List<Order> adaptOrders(List<HuobiOrder> huobiOrders) {
    List<Order> orders = new ArrayList<>();
    for (HuobiOrder order : huobiOrders) {
      orders.add(adaptOrder(order));
    }
    return orders;
  }

  public static UserTrades adaptTradeHistory(HuobiOrder[] openOrders) {
    OpenOrders orders = adaptOpenOrders(openOrders);
    List<UserTrade> trades = new ArrayList<>();
    for (Order order : orders.getOpenOrders()) {
      trades.add(adaptTrade(order));
    }
    return new UserTrades(trades, TradeSortType.SortByTimestamp);
  }

  public static List<FundingRecord> adaptFundingHistory(HuobiFundingRecord[] fundingRecords) {
    List<FundingRecord> records = new ArrayList<>();
    for (HuobiFundingRecord record : fundingRecords) {
      records.add(adaptFundingRecord(record));
    }
    return records;
  }

  public static FundingRecord adaptFundingRecord(HuobiFundingRecord r) {

    return new FundingRecord(
        r.getAddress(),
        r.getCreatedAt(),
        Currency.getInstance(r.getCurrency()),
        r.getAmount(),
        Long.toString(r.getId()),
        r.getTxhash(),
        r.getType(),
        adaptFundingStatus(r),
        null,
        r.getFee(),
        null);
  }

  private static Status adaptFundingStatus(HuobiFundingRecord record) {
    if (record.getType() == FundingRecord.Type.WITHDRAWAL) {
      return adaptWithdrawalStatus(record.getState());
    }
    return adaptDepostStatus(record.getState());
  }

  private static Status adaptWithdrawalStatus(String state) {
    switch (state) {
      case "pre-transfer":
      case "submitted":
      case "reexamine":
      case "pass":
      case "wallet-transfer":
        return Status.PROCESSING;
      case "canceled":
        return Status.CANCELLED;
      case "confirmed":
        return Status.COMPLETE;
      case "wallet-reject":
      case "reject	":
      case "confirm-error":
        return Status.FAILED;
      case "repealed":

      default:
        return null;
    }
  }

  /**
   * List of possible deposit state
   *
   * <p>State Description unknown On-chain transfer has not been received confirming On-chain
   * transfer waits for first confirmation confirmed On-chain transfer confirmed for at least one
   * block safe Multiple on-chain confirmation happened orphan Confirmed but currently in an orphan
   * branch
   */
  private static Status adaptDepostStatus(String state) {
    switch (state) {
      case "confirming":
      case "confirmed":
        return Status.PROCESSING;
      case "safe":
        return Status.COMPLETE;
      case "unknown":
      case "orphan":
        return Status.FAILED;
      default:
        return null;
    }
  }

  public static OrderType convert(OrderSide side) {
    switch (side) {
      case BUY:
        return OrderType.BID;
      case SELL:
        return OrderType.ASK;
      default:
        throw new RuntimeException("Not supported order side: " + side);
    }
  }

  public static OrderSide convert(OrderType type) {
    switch (type) {
      case ASK:
        return OrderSide.SELL;
      case BID:
        return OrderSide.BUY;
      default:
        throw new RuntimeException("Not supported order type: " + type);
    }
  }

  public static String toSymbol(CurrencyPair pair) {
    if (pair.equals(CurrencyPair.IOTA_BTC)) {
      return "IOTA";
    }
    return pair.base.getCurrencyCode();
  }

  public static String toSymbol(Currency currency) {
    if (Currency.IOT.equals(currency)) {
      return "IOTA";
    }
    return currency.getSymbol();
  }

  public static CurrencyPair adaptSymbol(String symbol) {
    int pairLength = symbol.length();
    if (symbol.endsWith("USDT")) {
      return new CurrencyPair(symbol.substring(0, pairLength - 4), "USDT");
    } else if (symbol.endsWith("USDC")) {
      return new CurrencyPair(symbol.substring(0, pairLength - 4), "USDC");
    } else if (symbol.endsWith("TUSD")) {
      return new CurrencyPair(symbol.substring(0, pairLength - 4), "TUSD");
    } else if (symbol.endsWith("USDS")) {
      return new CurrencyPair(symbol.substring(0, pairLength - 4), "USDS");
    } else if (symbol.endsWith("BUSD")) {
      return new CurrencyPair(symbol.substring(0, pairLength - 4), "BUSD");
    } else {
      return new CurrencyPair(
              symbol.substring(0, pairLength - 3), symbol.substring(pairLength - 3));
    }
  }
  
  public static List<OpenPosition> adaptPosition(List<PositionResultData> positionData){
	  List<OpenPosition> openPositions = new ArrayList<>();
	  
	  for(PositionResultData data : positionData) {
		  OpenPosition.Type type = data.getDirection().equals("buy") ? OpenPosition.Type.LONG : OpenPosition.Type.SHORT; 
		  openPositions.add(new OpenPosition(new CurrencyPair(data.getSymbol()+"/USDT"), type,data.getVolume() , data.getLastPrice())); 
	  }
	  
	  return openPositions;
  }

}
