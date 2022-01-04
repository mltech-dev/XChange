package org.knowm.xchange.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.huobi.dto.HuobiResult;

public class HuobiOrderResult extends HuobiResult<HuobiOrderResultData> {

  public HuobiOrderResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiOrderResultData result,
      @JsonProperty("err_code") String errCode,
      @JsonProperty("err_msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
