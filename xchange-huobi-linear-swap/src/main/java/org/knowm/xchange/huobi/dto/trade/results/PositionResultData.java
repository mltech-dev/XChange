package org.knowm.xchange.huobi.dto.trade.results;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionResultData {

	private final String symbol;
	private final String contractCode;
	private final String contractType;
	private final BigDecimal volume;
	private final BigDecimal available;
	private final BigDecimal frozen;
	private final BigDecimal costOpen;
	private final BigDecimal costHold;
	private final BigDecimal profitUnreal;
	private final BigDecimal profitRate;
	private final Long leverRate;
	private final BigDecimal positionMargin;
	private final String direction;
	private final BigDecimal profit;
	private final BigDecimal lastPrice;
	
	public PositionResultData(
			@JsonProperty("symbol")String symbol, 
			@JsonProperty("contract_code")String contractCode, 
			@JsonProperty("contract_type")String contractType, 
			@JsonProperty("volume")BigDecimal volume,
			@JsonProperty("available")BigDecimal available, 
			@JsonProperty("frozen")BigDecimal frozen, 
			@JsonProperty("cost_open")BigDecimal costOpen, 
			@JsonProperty("cost_hold")BigDecimal costHold,
			@JsonProperty("profit_unreal")BigDecimal profitUnreal, 
			@JsonProperty("profit_rate")BigDecimal profitRate, 
			@JsonProperty("lever_rate")Long leverRate, 
			@JsonProperty("position_margin")BigDecimal positionMargin,
			@JsonProperty("direction")String direction, 
			@JsonProperty("profit")BigDecimal profit, 
			@JsonProperty("last_price")BigDecimal lastPrice) {
		super();
		this.symbol = symbol;
		this.contractCode = contractCode;
		this.contractType = contractType;
		this.volume = volume;
		this.available = available;
		this.frozen = frozen;
		this.costOpen = costOpen;
		this.costHold = costHold;
		this.profitUnreal = profitUnreal;
		this.profitRate = profitRate;
		this.leverRate = leverRate;
		this.positionMargin = positionMargin;
		this.direction = direction;
		this.profit = profit;
		this.lastPrice = lastPrice;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getContractCode() {
		return contractCode;
	}

	public String getContractType() {
		return contractType;
	}

	public BigDecimal getVolume() {
		return volume;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public BigDecimal getFrozen() {
		return frozen;
	}

	public BigDecimal getCostOpen() {
		return costOpen;
	}

	public BigDecimal getCostHold() {
		return costHold;
	}

	public BigDecimal getProfitUnreal() {
		return profitUnreal;
	}

	public BigDecimal getProfitRate() {
		return profitRate;
	}

	public Long getLeverRate() {
		return leverRate;
	}

	public BigDecimal getPositionMargin() {
		return positionMargin;
	}

	public String getDirection() {
		return direction;
	}

	public BigDecimal getProfit() {
		return profit;
	}

	public BigDecimal getLastPrice() {
		return lastPrice;
	}
	
	
}
