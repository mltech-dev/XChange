package org.knowm.xchange.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.huobi.dto.HuobiResult;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HuobiCancelOrderResult extends HuobiResult<HuobiCancelOrderData> {

  public HuobiCancelOrderResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiCancelOrderData result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
