package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOpenOrdersRequest {
	@JsonProperty("symbol") 
	String symbol;

	public HuobiOpenOrdersRequest(String symbol) {
		super();
		this.symbol = symbol;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
}
