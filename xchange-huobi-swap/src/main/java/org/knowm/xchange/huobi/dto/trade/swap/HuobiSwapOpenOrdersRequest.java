package org.knowm.xchange.huobi.dto.trade.swap;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiSwapOpenOrdersRequest {
	@JsonProperty("contract_code")
	String contractCode;

	public HuobiSwapOpenOrdersRequest(String contractCode) {
		super();
		this.contractCode = contractCode;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

}
