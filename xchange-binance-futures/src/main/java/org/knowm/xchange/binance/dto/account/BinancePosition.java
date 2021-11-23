package org.knowm.xchange.binance.dto.account;

import java.math.BigDecimal;

import org.knowm.xchange.binance.dto.trade.PositionSide;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BinancePosition {
	private final String symbol;
	private final BigDecimal initialMargin;
	private final BigDecimal maintMargin;
	private final BigDecimal unrealizedProfit;
	private final BigDecimal positionInitialMargin;
	private final BigDecimal openOrderInitialMargin;
	private final BigDecimal leverage;
	private final boolean isolated;
	private final BigDecimal entryPrice;
	private final BigDecimal maxNotional;
	private final PositionSide positionSide;
	private final BigDecimal positionAmt;
	private final BigDecimal notional;
	private final int isolatedWallet;
	private final long updateTime;
	
	public BinancePosition(
			@JsonProperty("symbol") String symbol,
			@JsonProperty("initialMargin") BigDecimal initialMargin,
			@JsonProperty("maintMargin") BigDecimal maintMargin,
			@JsonProperty("unrealizedProfit") BigDecimal unrealizedProfit,
			@JsonProperty("positionInitialMargin") BigDecimal positionInitialMargin,
			@JsonProperty("openOrderInitialMargin") BigDecimal openOrderInitialMargin,
			@JsonProperty("leverage") BigDecimal leverage,
			@JsonProperty("isolated") boolean isolated,
			@JsonProperty("entryPrice") BigDecimal entryPrice,
			@JsonProperty("maxNotional") BigDecimal maxNotional,
			@JsonProperty("positionSide") PositionSide positionSide,
			@JsonProperty("positionAmt") BigDecimal positionAmt,
			@JsonProperty("notional") BigDecimal notional,
			@JsonProperty("isolatedWallet") int isolatedWallet,
			@JsonProperty("updateTime") long updateTime
			) {
		this.symbol = symbol;
		this.initialMargin = initialMargin;
		this.maintMargin = maintMargin;
		this.unrealizedProfit = unrealizedProfit;
		this.positionInitialMargin = positionInitialMargin;
		this.openOrderInitialMargin = openOrderInitialMargin;
		this.leverage = leverage;
		this.isolated = isolated;
		this.entryPrice = entryPrice;
		this.maxNotional = maxNotional;
		this.positionSide = positionSide;
		this.positionAmt = positionAmt;
		this.notional = notional;
		this.isolatedWallet = isolatedWallet;
		this.updateTime = updateTime;
	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getInitialMargin() {
		return initialMargin;
	}

	public BigDecimal getMaintMargin() {
		return maintMargin;
	}

	public BigDecimal getUnrealizedProfit() {
		return unrealizedProfit;
	}

	public BigDecimal getPositionInitialMargin() {
		return positionInitialMargin;
	}

	public BigDecimal getOpenOrderInitialMargin() {
		return openOrderInitialMargin;
	}

	public BigDecimal getLeverage() {
		return leverage;
	}

	public boolean isIsolated() {
		return isolated;
	}

	public BigDecimal getEntryPrice() {
		return entryPrice;
	}

	public BigDecimal getMaxNotional() {
		return maxNotional;
	}

	public PositionSide getPositionSide() {
		return positionSide;
	}

	public BigDecimal getPositionAmt() {
		return positionAmt;
	}

	public BigDecimal getNotional() {
		return notional;
	}

	public int getIsolatedWallet() {
		return isolatedWallet;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	@Override
	public String toString() {
		return "BinancePosition [symbol=" + symbol + ", initialMargin=" + initialMargin + ", maintMargin=" + maintMargin
				+ ", unrealizedProfit=" + unrealizedProfit + ", positionInitialMargin=" + positionInitialMargin
				+ ", openOrderInitialMargin=" + openOrderInitialMargin + ", leverage=" + leverage + ", isolated="
				+ isolated + ", entryPrice=" + entryPrice + ", maxNotional=" + maxNotional + ", positionSide="
				+ positionSide + ", positionAmt=" + positionAmt + ", notional=" + notional + ", isolatedWallet="
				+ isolatedWallet + ", updateTime=" + updateTime + "]";
	}
}
