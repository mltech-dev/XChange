package info.bitrich.xchangestream.bitmex.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang3.ObjectUtils;
import org.knowm.xchange.bitmex.BitmexAdapters;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.UserTrade;

public class BitmexOrder extends BitmexMarketDataEvent {

  public enum OrderStatus {
    NEW,
    PARTIALLYFILLED,
    FILLED,
    TBD,
    CANCELED,
    REJECTED,
    UNKNOW
  }

  private String orderID;

  private int account;

  private String side;

  private BigDecimal price;

  private BigDecimal avgPx;

  private String ordType;

  private OrderStatus ordStatus;

  private String clOrdID;

  private BigDecimal orderQty;

  private BigDecimal cumQty;

  public boolean isNotWorkingIndicator() {
    return !workingIndicator;
  }

  private boolean workingIndicator;

  @JsonCreator
  public BitmexOrder(
      @JsonProperty("symbol") String symbol,
      @JsonProperty("timestamp") String timestamp,
      @JsonProperty("orderID") String orderID,
      @JsonProperty("account") int account,
      @JsonProperty("side") String side,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("avgPx") BigDecimal avgPx,
      @JsonProperty("ordType") String ordType,
      @JsonProperty("ordStatus") String ordStatus,
      @JsonProperty("clOrdID") String clOrdID,
      @JsonProperty("orderQty") BigDecimal orderQty,
      @JsonProperty("cumQty") BigDecimal cumQty,
      @JsonProperty("workingIndicator") boolean workingIndicator) {
    super(symbol, timestamp);
    this.orderID = orderID;
    this.account = account;
    this.side = side;
    this.price = price;
    this.avgPx = avgPx;
    this.ordType = ordType;
    try {
      this.ordStatus = OrderStatus.valueOf(ordStatus.toUpperCase());
    } catch (Exception e) {
      this.ordStatus = OrderStatus.UNKNOW;
    }
    this.clOrdID = clOrdID;
    this.orderQty = orderQty;
    this.cumQty = cumQty;
    this.workingIndicator = workingIndicator;
  }

  public Order toOrder() {
    Order.Builder order;
    if(!ObjectUtils.isEmpty(ordType) &&
    		!ObjectUtils.isEmpty(side)) {
        if (ordType.equals("Market")) {
            order =
                new MarketOrder.Builder(
                    side.equals("Buy") ? Order.OrderType.BID : Order.OrderType.ASK,
                    new CurrencyPair(symbol.substring(0, 3), symbol.substring(3, symbol.length())));
          } else {
            order =
                new LimitOrder.Builder(
                    side.equals("Buy") ? Order.OrderType.BID : Order.OrderType.ASK,
                    new CurrencyPair(symbol.substring(0, 3), symbol.substring(3, symbol.length())));
          }    	
    } else {
    	order = new LimitOrder.Builder(
                null,
                new CurrencyPair(symbol.substring(0, 3), symbol.substring(3, symbol.length())));
    }
    order.id(orderID).averagePrice(avgPx).originalAmount(orderQty).cumulativeAmount(cumQty).userReference(clOrdID);

    switch (ordStatus) {
      case NEW:
        order.orderStatus(Order.OrderStatus.NEW);
        break;
      case PARTIALLYFILLED:
        order.orderStatus(Order.OrderStatus.PARTIALLY_FILLED);
        break;
      case FILLED:
        order.orderStatus(Order.OrderStatus.FILLED);
        break;
      case TBD:
        order.orderStatus(Order.OrderStatus.PENDING_CANCEL);
        break;
      case CANCELED:
        order.orderStatus(Order.OrderStatus.CANCELED);
        break;
      case REJECTED:
        order.orderStatus(Order.OrderStatus.REJECTED);
      default:
        order.orderStatus(Order.OrderStatus.UNKNOWN);
        break;
    }
    if (!ObjectUtils.isEmpty(ordType) && ordType.equals("Market")) {
      return ((MarketOrder.Builder) order).build();
    } else {
      return ((LimitOrder.Builder) order).build();
    }
  }

  public UserTrade toUserTrade() {
//    if (.equals("Trade")) throw new IllegalStateException("Not a trade");

    try {
      return new UserTrade.Builder()
              .type(side.equals("Buy") ? Order.OrderType.BID : Order.OrderType.ASK)
              .originalAmount(new BigDecimal(String.valueOf(orderQty)))
              .currencyPair(new CurrencyPair(symbol.substring(0, 3), symbol.substring(3, symbol.length())))
              .price(price)
              .timestamp(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(timestamp))
              .id(Long.toString(new Date().getTime()))
              .orderId(orderID)
  //            .feeAmount(commission)
  //            .feeCurrency(Currency.getInstance(settlCurrency.toLowerCase()))
              .build();
    } catch (ParseException e) {
      e.printStackTrace();
    }

    return null;
  }

  public String getOrderID() {
    return orderID;
  }

  public int getAccount() {
    return account;
  }

  public String getSide() {
    return side;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public BigDecimal getAvgPx() {
    return avgPx;
  }

  public String getOrdType() {
    return ordType;
  }

  public OrderStatus getOrdStatus() {
    return ordStatus;
  }

  public String getClOrdID() {
    return clOrdID;
  }

  public BigDecimal getOrderQty() {
    return orderQty;
  }

  public BigDecimal getCumQty() {
    return cumQty;
  }

  public boolean isWorkingIndicator() {
    return workingIndicator;
  }
}
