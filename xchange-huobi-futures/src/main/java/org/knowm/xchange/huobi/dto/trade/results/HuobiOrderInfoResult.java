package org.knowm.xchange.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.huobi.dto.HuobiResult;
import org.knowm.xchange.huobi.dto.trade.HuobiOrder;

public class HuobiOrderInfoResult extends HuobiResult<HuobiOrder[]> {

  public HuobiOrderInfoResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiOrder[] result,
      @JsonProperty("err_code") String errCode,
      @JsonProperty("err_msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
