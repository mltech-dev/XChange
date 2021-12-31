package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiCancelOrderRequest {

	@JsonProperty("client_order_id")
	private String clientOrderId;
	
	@JsonProperty("order_id")
	private String orderId;
	
	@JsonProperty("contract_code")
	private String symbol;

	public HuobiCancelOrderRequest(String clientOrderId, String orderId, String symbol) {
		this.clientOrderId = clientOrderId;
		this.orderId = orderId;
		this.symbol = symbol;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	

	
	
}
