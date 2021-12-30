package info.bitrich.xchangestream.huobi.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.huobi.HuobiAdapters;

public class ProductHuobiWebSocketTransaction extends BaseHuobiWebSocketTransaction {

  protected final CurrencyPair currencyPair;

  public ProductHuobiWebSocketTransaction(
      @JsonProperty("e") String eventType,
      @JsonProperty("E") String eventTime,
      @JsonProperty("s") String symbol) {
    super(eventType, eventTime);
    currencyPair = HuobiAdapters.adaptSymbol(symbol);
  }

  public CurrencyPair getCurrencyPair() {
    return currencyPair;
  }
}
