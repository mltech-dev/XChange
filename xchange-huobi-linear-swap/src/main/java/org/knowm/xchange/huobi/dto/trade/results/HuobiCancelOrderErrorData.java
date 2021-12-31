package org.knowm.xchange.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HuobiCancelOrderErrorData {
	
	private final String orderId;
	private final String errCode;
	private final String errMessage;
	
	public HuobiCancelOrderErrorData(@JsonProperty("order_id") String orderId, 
			@JsonProperty("err_code") String errCode, 
			@JsonProperty("err_msg")	String errMessage) {
		super();
		this.orderId = orderId;
		this.errCode = errCode;
		this.errMessage = errMessage;
	}

	public String getErrCode() {
		return errCode;
	}

	public String getErrMessage() {
		return errMessage;
	}

	public String getOrderId() {
		return orderId;
	}
	

}
