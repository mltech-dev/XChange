package info.bitrich.xchangestream.huobi;

import org.apache.commons.logging.Log;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huobi.client.TradeClient;
import com.huobi.client.req.trade.SubOrderUpdateV2Request;
import com.huobi.constant.HuobiOptions;
import com.huobi.model.trade.OrderUpdateV2Event;

import info.bitrich.xchangestream.core.StreamingTradeService;
import info.bitrich.xchangestream.huobi.dto.ExecutionReportHuobiUserTransaction;
import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;

public class HuobiStreamingTradeService implements StreamingTradeService {
  private final Logger LOG = LoggerFactory.getLogger(this.getClass());

  private final Subject<ExecutionReportHuobiUserTransaction> executionReportsPublisher =
      PublishSubject.<ExecutionReportHuobiUserTransaction>create().toSerialized();

  private volatile TradeClient tradeService;

  public HuobiStreamingTradeService(String apiKey, String secretKey) {
	  LOG.info("Initializing TradeClient");
	  tradeService = TradeClient.create(HuobiOptions.builder()
		        .apiKey(apiKey)
		        .secretKey(secretKey)
		        .build());
  }

  public Observable<ExecutionReportHuobiUserTransaction> getRawExecutionReports() {
    return executionReportsPublisher;
  }

  public Observable<Order> getOrderChanges() {
    return getRawExecutionReports()
        .map(ExecutionReportHuobiUserTransaction::toOrder);
  }

  @Override
  public Observable<Order> getOrderChanges(CurrencyPair currencyPair, Object... args) {
    return getOrderChanges().filter(oc -> currencyPair.equals(oc.getCurrencyPair()));
  }

  public Observable<UserTrade> getUserTrades() {
    return getRawExecutionReports()
        .map(ExecutionReportHuobiUserTransaction::toUserTrade);
  }

  @Override
  public Observable<UserTrade> getUserTrades(CurrencyPair currencyPair, Object... args) {
    return getUserTrades().filter(t -> t.getCurrencyPair().equals(currencyPair));
  }

  /** Registers subsriptions with the streaming service for the given products. */
  public void openSubscriptions() {
	  tradeService.subOrderUpdateV2(SubOrderUpdateV2Request.builder().symbols("*").build(), orderUpdateV2Event -> {
		  LOG.info(orderUpdateV2Event.toString());
	      executionReportsPublisher.onNext(executionReport(orderUpdateV2Event));
	    });
  }

  /**
   * User data subscriptions may have to persist across multiple socket connections to different
   * URLs and therefore must act in a publisher fashion so that subscribers get an uninterrupted
   * stream.
   */
  void setUserDataStreamingService() {
    openSubscriptions();
  }

  private ExecutionReportHuobiUserTransaction executionReport(OrderUpdateV2Event event) {
      return new ExecutionReportHuobiUserTransaction(event);
  }
}
