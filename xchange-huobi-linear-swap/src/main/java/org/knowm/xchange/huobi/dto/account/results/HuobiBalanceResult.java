package org.knowm.xchange.huobi.dto.account.results;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

import org.knowm.xchange.huobi.dto.HuobiListResult;
import org.knowm.xchange.huobi.dto.account.HuobiBalance;

public class HuobiBalanceResult extends HuobiListResult<HuobiBalance> {

  public HuobiBalanceResult(
      @JsonProperty("status") String status,
      @JsonProperty("data") List<HuobiBalance> result,
      @JsonProperty("err-code") String errCode,
      @JsonProperty("err-msg") String errMsg) {
    super(status, errCode, errMsg, result);
  }
}
