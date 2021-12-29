package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrderByOrderIdRequest {
	@JsonProperty("contract_code")
	private String contractCode;
	
	@JsonProperty("order_id")
	private String orderId;

	public HuobiOrderByOrderIdRequest(String contractCode, String orderId) {
		super();
		this.contractCode = contractCode;
		this.orderId = orderId;
	}
	
	public String getContractCode() {
		return contractCode;
	}


	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}


	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
