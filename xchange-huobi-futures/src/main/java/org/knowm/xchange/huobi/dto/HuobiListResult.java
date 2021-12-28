package org.knowm.xchange.huobi.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiListResult<V> {

  private final String status;
  private final String errCode;
  private final String errMsg;
  private final List<V> result;

  @JsonCreator
  public HuobiListResult(
      @JsonProperty("status") String status,
      @JsonProperty("err_code") String errCode,
      @JsonProperty("err_msg") String errMsg,
      List<V> result) {
    this.status = status;
    this.errCode = errCode;
    this.errMsg = errMsg;
    this.result = result;
  }

  public boolean isSuccess() {
    return getStatus().equals("ok");
  }

  public String getStatus() {
    return status;
  }

  public String getError() {
    return (errMsg!=null && errMsg.length() == 0) ? errCode : errMsg;
  }	

  public List<V> getResult() {
    return result;
  }

  @Override
  public String toString() {
    return String.format(
        "HuobiResult [%s, %s]", status, isSuccess() ? getResult().toString() : getError());
  }
}
