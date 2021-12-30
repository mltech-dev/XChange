package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOpenOrdersRequest {
	@JsonProperty("contract_code")
	String contractCode;

	public HuobiOpenOrdersRequest(String contractCode) {
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
