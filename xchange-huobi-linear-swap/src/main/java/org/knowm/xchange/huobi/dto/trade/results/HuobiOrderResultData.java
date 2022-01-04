package org.knowm.xchange.huobi.dto.trade.results;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrderResultData {
	private Long orderId;
	private String orderIdStr;
	
	public HuobiOrderResultData(
			@JsonProperty("order_id") Long orderId, 
			@JsonProperty("order_id_str") String orderIdStr) {
		super();
		this.orderId = orderId;
		this.orderIdStr = orderIdStr;
	}
	
	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getOrderIdStr() {
		return orderIdStr;
	}
	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}
	
}
