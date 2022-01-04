package org.knowm.xchange.huobi.dto.trade;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrder {
	    
	String symbol;
	String contractCode;
	String contractType;
	long volume;
	BigDecimal price;
	String orderPriceType;
	int orderType;
	String direction;
	String offset;
	int leverRate;
	long orderId;
	String clientOrderId;
	long createdAt;
	BigDecimal tradeVolume;
	BigDecimal tradeTurnover;
	BigDecimal fee;
	BigDecimal tradeAvgPrice;
	BigDecimal marginFrozen;
	BigDecimal profit;
	int status;
	String orderSource;
	String orderIdStr;
	BigDecimal realProfit;
	String feeAsset;
	String liquidationType;
	Long canceledAt;
	int isTpsl;

	public HuobiOrder(
			@JsonProperty("symbol") String symbol, 
			@JsonProperty("contract_code") String contractCode, 
			@JsonProperty("contract_type") String contractType, 
			@JsonProperty("volume") long volume, 
			@JsonProperty("price") BigDecimal price,
			@JsonProperty("order_price_type") String orderPriceType, 
			@JsonProperty("order_type") int orderType, 
			@JsonProperty("direction") String direction, 
			@JsonProperty("offset") String offset, 
			@JsonProperty("lever_rate") int leverRate, 
			@JsonProperty("order_id") long orderId,
			@JsonProperty("client_order_id") String clientOrderId, 
			@JsonProperty("created_at") long createdAt, 
			@JsonProperty("trade_volume") BigDecimal tradeVolume, 
			@JsonProperty("trade_turnover") BigDecimal tradeTurnover, 
			@JsonProperty("fee") BigDecimal fee,
			@JsonProperty("trade_avg_price") BigDecimal tradeAvgPrice, 
			@JsonProperty("margin_frozen") BigDecimal marginFrozen, 
			@JsonProperty("profit") BigDecimal profit, 
			@JsonProperty("status") int status, 
			@JsonProperty("order_source") String orderSource,
			@JsonProperty("order_id_str") String orderIdStr, 
			@JsonProperty("real_profit") BigDecimal realProfit, 
			@JsonProperty("fee_asset") String feeAsset, 
			@JsonProperty("liquidation_type") String liquidationType, 
			@JsonProperty("canceled_at") Long canceledAt,
			@JsonProperty("is_tpsl") int isTpsl) {
		super();
		this.symbol = symbol;
		this.contractCode = contractCode;
		this.contractType = contractType;
		this.volume = volume;
		this.price = price;
		this.orderPriceType = orderPriceType;
		this.orderType = orderType;
		this.direction = direction;
		this.offset = offset;
		this.leverRate = leverRate;
		this.orderId = orderId;
		this.clientOrderId = clientOrderId;
		this.createdAt = createdAt;
		this.tradeVolume = tradeVolume;
		this.tradeTurnover = tradeTurnover;
		this.fee = fee;
		this.tradeAvgPrice = tradeAvgPrice;
		this.marginFrozen = marginFrozen;
		this.profit = profit;
		this.status = status;
		this.orderSource = orderSource;
		this.orderIdStr = orderIdStr;
		this.realProfit = realProfit;
		this.feeAsset = feeAsset;
		this.liquidationType = liquidationType;
		this.canceledAt = canceledAt;
		this.isTpsl = isTpsl;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public String getContractCode() {
		return contractCode;
	}
	public void setContractCode(String contractCode) {
		this.contractCode = contractCode;
	}
	public String getContractType() {
		return contractType;
	}
	public void setContractType(String contractType) {
		this.contractType = contractType;
	}
	public long getVolume() {
		return volume;
	}
	public void setVolume(long volume) {
		this.volume = volume;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public String getOrderPriceType() {
		return orderPriceType;
	}
	public void setOrderPriceType(String orderPriceType) {
		this.orderPriceType = orderPriceType;
	}
	public int getOrderType() {
		return orderType;
	}
	public void setOrderType(int orderType) {
		this.orderType = orderType;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getOffset() {
		return offset;
	}
	public void setOffset(String offset) {
		this.offset = offset;
	}
	public int getLeverRate() {
		return leverRate;
	}
	public void setLeverRate(int leverRate) {
		this.leverRate = leverRate;
	}
	public long getOrderId() {
		return orderId;
	}
	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}
	public String getClientOrderId() {
		return clientOrderId;
	}
	public void setClientOrderId(String clientOrderId) {
		this.clientOrderId = clientOrderId;
	}
	public long getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(long createdAt) {
		this.createdAt = createdAt;
	}
	public BigDecimal getTradeVolume() {
		return tradeVolume;
	}
	public void setTradeVolume(BigDecimal tradeVolume) {
		this.tradeVolume = tradeVolume;
	}
	public BigDecimal getTradeTurnover() {
		return tradeTurnover;
	}
	public void setTradeTurnover(BigDecimal tradeTurnover) {
		this.tradeTurnover = tradeTurnover;
	}
	public BigDecimal getFee() {
		return fee;
	}
	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}
	public BigDecimal getTradeAvgPrice() {
		return tradeAvgPrice;
	}
	public void setTradeAvgPrice(BigDecimal tradeAvgPrice) {
		this.tradeAvgPrice = tradeAvgPrice;
	}
	public BigDecimal getMarginFrozen() {
		return marginFrozen;
	}
	public void setMarginFrozen(BigDecimal marginFrozen) {
		this.marginFrozen = marginFrozen;
	}
	public BigDecimal getProfit() {
		return profit;
	}
	public void setProfit(BigDecimal profit) {
		this.profit = profit;
	}
	public String getOrderSource() {
		return orderSource;
	}
	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	public String getOrderIdStr() {
		return orderIdStr;
	}
	public void setOrderIdStr(String orderIdStr) {
		this.orderIdStr = orderIdStr;
	}
	public BigDecimal getRealProfit() {
		return realProfit;
	}
	public void setRealProfit(BigDecimal realProfit) {
		this.realProfit = realProfit;
	}
	public String getFeeAsset() {
		return feeAsset;
	}
	public void setFeeAsset(String feeAsset) {
		this.feeAsset = feeAsset;
	}
	public String getLiquidationType() {
		return liquidationType;
	}
	public void setLiquidationType(String liquidationType) {
		this.liquidationType = liquidationType;
	}
	public Long getCanceledAt() {
		return canceledAt;
	}
	public void setCanceledAt(Long canceledAt) {
		this.canceledAt = canceledAt;
	}
	public int getIsTpsl() {
		return isTpsl;
	}
	public void setIsTpsl(int isTpsl) {
		this.isTpsl = isTpsl;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
