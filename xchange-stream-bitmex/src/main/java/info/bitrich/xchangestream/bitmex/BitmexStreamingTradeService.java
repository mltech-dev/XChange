package info.bitrich.xchangestream.bitmex;

import info.bitrich.xchangestream.bitmex.dto.BitmexOrder;
import info.bitrich.xchangestream.core.StreamingTradeService;
import io.reactivex.Observable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.NotYetImplementedForExchangeException;
import org.knowm.xchange.instrument.Instrument;

/** Created by Declan */
public class BitmexStreamingTradeService implements StreamingTradeService {

  private final BitmexStreamingService streamingService;

  public BitmexStreamingTradeService(BitmexStreamingService streamingService) {
    this.streamingService = streamingService;
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
        String channelName = "order";
        return streamingService
                .subscribeBitmexChannel(channelName)
                .flatMapIterable(
                        s -> {
                            BitmexOrder[] bitmexOrders = s.toBitmexOrders();

                            return Arrays.stream(bitmexOrders)
                                    .map(BitmexOrder::toOrder)
                                    .collect(Collectors.toList());
                        });
    }

    public Observable<UserTrade> getUserTrades(Instrument instrument, Object... args) {
        if (instrument instanceof CurrencyPair) {
            return this.getUserTrades((CurrencyPair)instrument, args);
        } else {
            throw new NotYetImplementedForExchangeException("getUserTrades");
        }
    }
}
