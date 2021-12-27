package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiCancelClientOrderRequest {

	@JsonProperty("client-order-id")
	private String clientOrderId;

	public HuobiCancelClientOrderRequest(String clientOrderId) {
		super();
		this.clientOrderId = clientOrderId;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	
	
}
