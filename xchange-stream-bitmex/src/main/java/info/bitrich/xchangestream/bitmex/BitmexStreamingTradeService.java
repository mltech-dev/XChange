package info.bitrich.xchangestream.bitmex;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.bitmex.dto.BitmexExecution;
import info.bitrich.xchangestream.bitmex.dto.BitmexOrder;
import info.bitrich.xchangestream.bitmex.dto.BitmexOrder.OrderStatus;
import info.bitrich.xchangestream.bitmex.dto.BitmexWebSocketTransaction;
import info.bitrich.xchangestream.core.StreamingTradeService;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import io.reactivex.Observable;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.ExchangeSecurityException;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.instrument.Instrument;

/** Created by Declan */
public class BitmexStreamingTradeService implements StreamingTradeService {

  private final BitmexStreamingService streamingService;
  private final ObjectMapper objectMapper = StreamingObjectMapperHelper.getObjectMapper();
  public Observable<BitmexWebSocketTransaction> rawWebTransactions;
  public BitmexStreamingTradeService(BitmexStreamingService streamingService) {
    this.streamingService = streamingService;
    this.rawWebTransactions =  streamingService
              .subscribeBitmexChannel("order");
  }

  public Observable<Order> getOrders(CurrencyPair currencyPair, Object... args) {
    String channelName = "order";
    String instrument = currencyPair.base.toString() + currencyPair.counter.toString();
    return streamingService
        .subscribeBitmexChannel(channelName)
        .flatMapIterable(
            s -> {
              BitmexOrder[] bitmexOrders = s.toBitmexOrders();
              return Arrays.stream(bitmexOrders)
                  .filter(bitmexOrder -> bitmexOrder.getSymbol().equals(instrument))
                  .filter(BitmexOrder::isNotWorkingIndicator)
                  .map(BitmexOrder::toOrder)
                  .collect(Collectors.toList());
            });
  }

    public Observable<Order> getOrderChanges() {
      return getRawExecutionReports()
              .flatMapIterable(
                s -> {
                    BitmexOrder[] bitmexOrders = s.toBitmexOrders();
                    List<Order> orders = Arrays.stream(bitmexOrders)
                    .map(BitmexOrder::toOrder)
                    .collect(Collectors.toList());

                    return orders;
                });
    }

    public Observable<BitmexWebSocketTransaction> getRawExecutionReports() {
        return rawWebTransactions;
    }

    public Observable<UserTrade> getUserTrades(){
        return  getRawExecutionReports()
                .flatMapIterable(
                s -> {
                    BitmexOrder[] bitmexOrders = s.toBitmexOrders();
                    List<UserTrade> userTrades = Arrays.stream(bitmexOrders)
                    .filter(bitmexOrder -> bitmexOrder.getOrdStatus().equals(OrderStatus.FILLED))
                    .map(BitmexOrder::toUserTrade)
                    .collect(Collectors.toList());

                    return userTrades;
                });

    }
}
