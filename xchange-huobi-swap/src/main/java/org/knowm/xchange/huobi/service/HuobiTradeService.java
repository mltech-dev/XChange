package org.knowm.xchange.huobi.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import org.knowm.xchange.Exchange;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.IOrderFlags;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.OpenOrders;
import org.knowm.xchange.dto.trade.StopOrder;
import org.knowm.xchange.dto.trade.UserTrades;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.NotAvailableFromExchangeException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.huobi.HuobiAdapters;
import org.knowm.xchange.huobi.dto.trade.HuobiOrder;
import org.knowm.xchange.service.trade.TradeService;
import org.knowm.xchange.service.trade.params.CancelOrderByIdParams;
import org.knowm.xchange.service.trade.params.CancelOrderParams;
import org.knowm.xchange.service.trade.params.CurrencyPairParam;
import org.knowm.xchange.service.trade.params.InstrumentParam;
import org.knowm.xchange.service.trade.params.TradeHistoryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.TradeHistoryParams;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsIdSpan;
import org.knowm.xchange.service.trade.params.TradeHistoryParamsTimeSpan;
import org.knowm.xchange.service.trade.params.orders.DefaultOpenOrdersParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OpenOrdersParams;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParamCurrencyPair;
import org.knowm.xchange.service.trade.params.orders.OrderQueryParams;

public class HuobiTradeService extends HuobiTradeServiceRaw implements TradeService {
  public static IOrderFlags FOK = new IOrderFlags() {};
  public static IOrderFlags IOC = new IOrderFlags() {};
  public static IOrderFlags GTC = new IOrderFlags() {};
  public static IOrderFlags POST_ONLY = new IOrderFlags() {};

  public HuobiTradeService(Exchange exchange) {
    super(exchange);
  }

  /** Huobi trade history only goes back 48h - a new API was promised in 2019-Q1 */
  @Override
  public UserTrades getTradeHistory(TradeHistoryParams tradeHistoryParams) throws IOException {
    CurrencyPair currencyPair = null;
    if (tradeHistoryParams instanceof TradeHistoryParamCurrencyPair) {
      currencyPair = ((TradeHistoryParamCurrencyPair) tradeHistoryParams).getCurrencyPair();
    }
    if (currencyPair == null) {
      throw new IllegalArgumentException("Currency pair is required.");
    }
    Date startDate;
    Date endDate;
    if (tradeHistoryParams instanceof TradeHistoryParamsTimeSpan) {
      startDate = ((TradeHistoryParamsTimeSpan) tradeHistoryParams).getStartTime();
      endDate = ((TradeHistoryParamsTimeSpan) tradeHistoryParams).getEndTime();
    } else {
      startDate = null;
      endDate = null;
    }
    String startId;
    if (tradeHistoryParams instanceof TradeHistoryParamsIdSpan) {
      startId = ((TradeHistoryParamsIdSpan) tradeHistoryParams).getStartId();
    } else {
      startId = null;
    }

    HuobiOrder[] openOrders = getHuobiTradeHistory(currencyPair, startDate, endDate, startId);
    return HuobiAdapters.adaptTradeHistory(openOrders);
  }

  @Override
  public Collection<Order> getOrder(String... orderIds) throws IOException {
	    throw new NotAvailableFromExchangeException();
  }
  
  @Override
  public Collection<Order> getOrder(OrderQueryParams... params) throws IOException {
      Collection<Order> orders = new ArrayList<>();
      for (OrderQueryParams param : params) {
        if (!(param instanceof OrderQueryParamCurrencyPair)) {
          throw new ExchangeException(
              "Parameters must be an instance of OrderQueryParamCurrencyPair");
        }
        OrderQueryParamCurrencyPair orderQueryParamCurrencyPair =
            (OrderQueryParamCurrencyPair) param;
        if (orderQueryParamCurrencyPair.getCurrencyPair() == null
            || orderQueryParamCurrencyPair.getOrderId() == null) {
          throw new ExchangeException(
              "You need to provide the currency pair and the order id to query an order.");
        }

        orders.add(
        		HuobiAdapters.adaptOrder(
                getHuobiOrder(
                    HuobiAdapters.toSymbol(orderQueryParamCurrencyPair.getCurrencyPair()),
                    orderQueryParamCurrencyPair.getOrderId())));
      }
      return orders;
  }

  @Override
  public OpenOrdersParams createOpenOrdersParams() {
    return new DefaultOpenOrdersParamCurrencyPair();
  }

  @Override
  public TradeHistoryParams createTradeHistoryParams() {
    return new HuobiTradeHistoryParams();
  }

  public boolean cancelOrder(String orderId,String clientOrderId,CurrencyPair currencyPair) throws IOException {
    return cancelHuobiOrder(orderId,clientOrderId,currencyPair).length() > 0;
  }

  @Override
  public boolean cancelOrder(CancelOrderParams cancelOrderParams) throws IOException {
    return cancelOrderParams instanceof CancelOrderByIdParams
        && cancelOrder(((CancelOrderByIdParams) cancelOrderParams).getOrderId());
  }

  @Override
  public String placeMarketOrder(MarketOrder marketOrder) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  @Override
  public OpenOrders getOpenOrders() throws IOException {
    return getOpenOrders(createOpenOrdersParams());
  }

  @Override
  public OpenOrders getOpenOrders(OpenOrdersParams openOrdersParams) throws IOException {
    if (!(openOrdersParams instanceof CurrencyPairParam)) throw new IllegalArgumentException();

    HuobiOrder[] openOrders = getHuobiOpenOrders((CurrencyPairParam)openOrdersParams);
    return HuobiAdapters.adaptOpenOrders(openOrders);
  }

  @Override
  public String placeLimitOrder(LimitOrder limitOrder) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }

  @Override
  public String placeStopOrder(StopOrder stopOrder) throws IOException {
    throw new NotYetImplementedForExchangeException();
  }
}
