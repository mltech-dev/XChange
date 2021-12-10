package info.bitrich.xchangestream.huobi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import info.bitrich.xchangestream.core.StreamingTradeService;
import info.bitrich.xchangestream.huobi.dto.BaseHuobiWebSocketTransaction;
import info.bitrich.xchangestream.huobi.dto.ExecutionReportHuobiUserTransaction;
import info.bitrich.xchangestream.service.netty.StreamingObjectMapperHelper;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.exceptions.ExchangeException;
import org.knowm.xchange.exceptions.ExchangeSecurityException;

import java.io.IOException;

public class HuobiStreamingTradeService implements StreamingTradeService {

  private final Subject<ExecutionReportHuobiUserTransaction> executionReportsPublisher =
      PublishSubject.<ExecutionReportHuobiUserTransaction>create().toSerialized();

  private volatile Disposable executionReports;
  private volatile HuobiUserDataStreamingService huobiUserDataStreamingService;

  private final ObjectMapper mapper = StreamingObjectMapperHelper.getObjectMapper();

  public HuobiStreamingTradeService(
      HuobiUserDataStreamingService huobiUserDataStreamingService) {
    this.huobiUserDataStreamingService = huobiUserDataStreamingService;
  }

  public Observable<ExecutionReportHuobiUserTransaction> getRawExecutionReports() {
    if (huobiUserDataStreamingService == null || !huobiUserDataStreamingService.isSocketOpen())
      throw new ExchangeSecurityException("Not authenticated");
    return executionReportsPublisher;
  }

  public Observable<Order> getOrderChanges() {
    return getRawExecutionReports()
        .filter(r -> !r.getExecutionType().equals(ExecutionReportHuobiUserTransaction.ExecutionType.REJECTED))
        .map(ExecutionReportHuobiUserTransaction::toOrder);
  }

  @Override
  public Observable<Order> getOrderChanges(CurrencyPair currencyPair, Object... args) {
    return getOrderChanges().filter(oc -> currencyPair.equals(oc.getCurrencyPair()));
  }

  public Observable<UserTrade> getUserTrades() {
    return getRawExecutionReports()
        .filter(r -> r.getExecutionType().equals(ExecutionReportHuobiUserTransaction.ExecutionType.TRADE))
        .map(ExecutionReportHuobiUserTransaction::toUserTrade);
  }

  @Override
  public Observable<UserTrade> getUserTrades(CurrencyPair currencyPair, Object... args) {
    return getUserTrades().filter(t -> t.getCurrencyPair().equals(currencyPair));
  }

  /** Registers subsriptions with the streaming service for the given products. */
  public void openSubscriptions() {
    if (huobiUserDataStreamingService != null) {
      executionReports =
              huobiUserDataStreamingService
              .subscribeChannel(
                  BaseHuobiWebSocketTransaction.BinanceWebSocketTypes.EXECUTION_REPORT)
              .map(this::executionReport)
              .subscribe(executionReportsPublisher::onNext);
    }
  }

  /**
   * User data subscriptions may have to persist across multiple socket connections to different
   * URLs and therefore must act in a publisher fashion so that subscribers get an uninterrupted
   * stream.
   */
  void setUserDataStreamingService(
      HuobiUserDataStreamingService binanceUserDataStreamingService) {
    if (executionReports != null && !executionReports.isDisposed()) executionReports.dispose();
    this.huobiUserDataStreamingService = binanceUserDataStreamingService;
    openSubscriptions();
  }

  private ExecutionReportHuobiUserTransaction executionReport(JsonNode json) {
    try {
      return mapper.treeToValue(json, ExecutionReportHuobiUserTransaction.class);
    } catch (IOException e) {
      throw new ExchangeException("Unable to parse execution report", e);
    }
  }
}
