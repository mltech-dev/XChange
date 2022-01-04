package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrderByOrderIdRequest {
	@JsonProperty("symbol")
	private String symbol;
	
	@JsonProperty("order_id")
	private String orderId;

	public HuobiOrderByOrderIdRequest(String symbol, String orderId) {
		super();
		this.symbol = symbol;
		this.orderId = orderId;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
}
