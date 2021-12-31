package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiCancelOrderRequest {

	@JsonProperty("client_order_id")
	private String clientOrderId;
	
	@JsonProperty("order_id")
	private String orderId;
	
	@JsonProperty("contract_code")
	private String contractCode;

	public HuobiCancelOrderRequest(String clientOrderId, String orderId, String contractCode) {
		super();
		this.clientOrderId = clientOrderId;
		this.orderId = orderId;
		this.contractCode = contractCode;
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

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	
}
