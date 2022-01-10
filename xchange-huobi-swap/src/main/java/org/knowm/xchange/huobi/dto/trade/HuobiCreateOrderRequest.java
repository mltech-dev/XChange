package org.knowm.xchange.huobi.dto.trade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiCreateOrderRequest {
	@JsonProperty("contract_code")
	String contractCode;

	@JsonProperty("direction")
	String direction;

	@JsonProperty("lever_rate")
	int leverRate;

	@JsonProperty("offset")
	String offset;

	@JsonProperty("order_price_type")
	String orderPriceType;

	@JsonProperty("price")
	BigDecimal price;

	@JsonProperty("volume")
	Long volume;

	@JsonProperty("client_order_id")
	long clientOrderId;

	public HuobiCreateOrderRequest(String contractCode, String direction, int leverRate, String offset,
			String orderPriceType, BigDecimal price, Long volume, long clientOrderId) {
		super();
		this.contractCode = contractCode;
		this.direction = direction;
		this.leverRate = leverRate;
		this.offset = offset;
		this.orderPriceType = orderPriceType;
		this.price = price;
		this.volume = volume;
		this.clientOrderId = clientOrderId;
	}

	public String getContractCode() {
		return contractCode;
	}

	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public int getLeverRate() {
		return leverRate;
	}

	public void setLeverRate(int leverRate) {
		this.leverRate = leverRate;
	}

	public String getOffset() {
		return offset;
	}

	public void setOffset(String offset) {
		this.offset = offset;
	}

	public String getOrderPriceType() {
		return orderPriceType;
	}

	public void setOrderPriceType(String orderPriceType) {
		this.orderPriceType = orderPriceType;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public long getClientOrderId() {
		return clientOrderId;
	}

	public void setClientOrderId(long clientOrderId) {
		this.clientOrderId = clientOrderId;
	}

}
