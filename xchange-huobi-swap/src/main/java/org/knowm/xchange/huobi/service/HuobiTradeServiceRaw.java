package org.knowm.xchange.huobi.service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.ObjectUtils;
import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.FuturesOrder;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.huobi.HuobiAdapters;
import org.knowm.xchange.huobi.HuobiUtils;
import org.knowm.xchange.huobi.dto.trade.HuobiCancelOrderRequest;
import org.knowm.xchange.huobi.dto.trade.HuobiCreateOrderRequest;
import org.knowm.xchange.huobi.dto.trade.HuobiMatchResult;
import org.knowm.xchange.huobi.dto.trade.HuobiOpenOrdersRequest;
import org.knowm.xchange.huobi.dto.trade.HuobiOrder;
import org.knowm.xchange.huobi.dto.trade.HuobiOrderByOrderIdRequest;
import org.knowm.xchange.huobi.dto.trade.results.HuobiCancelOrderResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiMatchesResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOrderInfoResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOrderResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOrdersResult;
import org.knowm.xchange.service.trade.params.CurrencyPairParam;
import org.knowm.xchange.service.trade.params.InstrumentParam;

public class HuobiTradeServiceRaw extends HuobiBaseService {
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

  HuobiTradeServiceRaw(Exchange exchange) {
    super(exchange);
  }

  // https://huobiapi.github.io/docs/spot/v1/en/#search-past-orders
  public HuobiOrder[] getHuobiTradeHistory(
      CurrencyPair currencyPair, Date startDate, Date endDate, String startId) throws IOException {
    String tradeStates = "partial-filled,partial-canceled,filled";
    HuobiOrdersResult result =
        huobi.getOrders(
            HuobiUtils.createHuobiInstument(currencyPair),
            tradeStates,
            null, // System.currentTimeMillis() - 48 * 60 * 60_000L,
            null,
            startDate == null ? null : DATE_FORMAT.format(startDate),
            endDate == null ? null : DATE_FORMAT.format(endDate),
            startId,
            null,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result).getOrders();
  }

  public HuobiOrder[] getHuobiOrderHistory(
      CurrencyPairParam params, Date startTime, Date endTime, String direct, Integer size)
      throws IOException {
    HuobiOrdersResult result =
        huobi.getOrdersHistory(
            params != null ? HuobiUtils.createHuobiInstument(params.getCurrencyPair()) : null,
            startTime != null ? startTime.getTime() : null,
            endTime != null ? endTime.getTime() : null,
            direct,
            size,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result).getOrders();
  }

  public HuobiOrder[] getHuobiOpenOrders(CurrencyPairParam params) throws IOException {
    HuobiOrdersResult result =
        huobi.getOpenOrders(
        	new HuobiOpenOrdersRequest(params != null ? HuobiUtils.createHuobiInstumentForOpenOrders(params.getCurrencyPair()) : null),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result).getOrders();
  }

  public HuobiMatchResult[] getHuobiMatchResults(
      CurrencyPairParam params,
      String types,
      Date startDate,
      Date endDate,
      String from,
      String direct,
      Integer size)
      throws IOException {
    HuobiMatchesResult result =
        huobi.getMatchResults(
            params != null ? HuobiUtils.createHuobiInstument(params.getCurrencyPair()) : null,
            types,
            HuobiUtils.createUTCDate(startDate),
            HuobiUtils.createUTCDate(endDate),
            from,
            direct,
            size,
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public String cancelHuobiOrder(String orderId,String clientOrderId,CurrencyPair currencyPair) throws IOException {
	  String currency = HuobiAdapters.toSymbol(currencyPair);
	  
    HuobiCancelOrderResult result =
        huobi.cancelSwapOrder(
            new HuobiCancelOrderRequest(clientOrderId, clientOrderId, currency),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);
    return checkResult(result);
  }

  public String placeHuobiSwapOrder(FuturesOrder futuresOrder) throws IOException {
    String direction;
    if (futuresOrder.getType() == OrderType.BID) {
    	direction = "buy";
    } else if (futuresOrder.getType() == OrderType.ASK) {
    	direction = "sell";
    } else {
      throw new ExchangeException("Unsupported order direction.");
    }

    HuobiOrderResult result =
        huobi.placeSwapOrder(
            new HuobiCreateOrderRequest(futuresOrder.getContractCode(),
            		direction,
            		futuresOrder.getLeverRate(),
            		futuresOrder.getOffset(),
            		HuobiAdapters.getOrderPriceType(futuresOrder.getOrderPriceType()),
            		futuresOrder.getPrice(),
            		futuresOrder.getOriginalAmount(),
            		Long.valueOf(futuresOrder.getUserReference())
            		),
            exchange.getExchangeSpecification().getApiKey(),
            HuobiDigest.HMAC_SHA_256,
            2,
            HuobiUtils.createUTCDate(exchange.getNonceFactory()),
            signatureCreator);

    return checkResult(result).getOrderIdStr();
  }

  public HuobiOrder getHuobiOrder(String symbol, String orderId) throws IOException {
    return checkResult(huobi.getOrder(
              new HuobiOrderByOrderIdRequest(symbol, orderId),
              exchange.getExchangeSpecification().getApiKey(),
              HuobiDigest.HMAC_SHA_256,
              2,
              HuobiUtils.createUTCDate(exchange.getNonceFactory()),
              signatureCreator))[0];
  }
}
