package org.knowm.xchange.huobi.dto.trade.results;

import org.knowm.xchange.huobi.dto.HuobiResult;
import org.knowm.xchange.huobi.dto.trade.HuobiOrderResponseWrapper;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrdersResult extends HuobiResult<HuobiOrderResponseWrapper> {

  public HuobiOrdersResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") HuobiOrderResponseWrapper result,
      @JsonProperty("err_code") String errCode,
      @JsonProperty("err_msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
