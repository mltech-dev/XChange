package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.currency.Currency;

public final class BinanceBalance {
	private final Currency currency;
	private final BigDecimal walletBalance;
	private final BigDecimal unrealizedProfit;
	private final BigDecimal marginBalance;
	private final BigDecimal maintMargin;
	private final BigDecimal initialMargin;
	private final BigDecimal positionInitialMargin;
	private final BigDecimal openOrderInitialMargin;
	private final BigDecimal maxWithdrawAmount;
	private final BigDecimal crossWalletBalance;
	private final BigDecimal crossUnPnl;
	private final BigDecimal availableBalance;
	private final boolean marginAvailable;
	private final long updateTime;
	public BinanceBalance(
			@JsonProperty("asset") String asset,
			@JsonProperty("walletBalance") BigDecimal walletBalance,
			@JsonProperty("unrealizedProfit") BigDecimal unrealizedProfit,
			@JsonProperty("marginBalance") BigDecimal marginBalance,
			@JsonProperty("maintMargin") BigDecimal maintMargin,
			@JsonProperty("initialMargin") BigDecimal initialMargin,
			@JsonProperty("positionInitialMargin") BigDecimal positionInitialMargin,
			@JsonProperty("openOrderInitialMargin") BigDecimal openOrderInitialMargin,
			@JsonProperty("maxWithdrawAmount") BigDecimal maxWithdrawAmount,
			@JsonProperty("crossWalletBalance") BigDecimal crossWalletBalance,
			@JsonProperty("crossUnPnl") BigDecimal crossUnPnl,
			@JsonProperty("availableBalance") BigDecimal availableBalance,
			@JsonProperty("marginAvailable") boolean marginAvailable,
			@JsonProperty("updateTime") long updateTime) {
		this.currency = Currency.getInstance(asset);
		this.walletBalance = walletBalance;
		this.unrealizedProfit = unrealizedProfit;
		this.marginBalance = marginBalance;
		this.maintMargin = maintMargin;
		this.initialMargin = initialMargin;
		this.positionInitialMargin = positionInitialMargin;
		this.openOrderInitialMargin = openOrderInitialMargin;
		this.maxWithdrawAmount = maxWithdrawAmount;
		this.crossWalletBalance = crossWalletBalance;
		this.crossUnPnl = crossUnPnl;
		this.availableBalance = availableBalance;
		this.marginAvailable = marginAvailable;
		this.updateTime = updateTime;
	}
	public Currency getCurrency() {
		return currency;
	}
	public BigDecimal getWalletBalance() {
		return walletBalance;
	}
	public BigDecimal getUnrealizedProfit() {
		return unrealizedProfit;
	}
	public BigDecimal getMarginBalance() {
		return marginBalance;
	}
	public BigDecimal getMaintMargin() {
		return maintMargin;
	}
	public BigDecimal getInitialMargin() {
		return initialMargin;
	}
	public BigDecimal getPositionInitialMargin() {
		return positionInitialMargin;
	}
	public BigDecimal getOpenOrderInitialMargin() {
		return openOrderInitialMargin;
	}
	public BigDecimal getMaxWithdrawAmount() {
		return maxWithdrawAmount;
	}
	public BigDecimal getCrossWalletBalance() {
		return crossWalletBalance;
	}
	public BigDecimal getCrossUnPnl() {
		return crossUnPnl;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public boolean isMarginAvailable() {
		return marginAvailable;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	@Override
	public String toString() {
		return "BinanceBalance [currency=" + currency + ", walletBalance=" + walletBalance + ", unrealizedProfit="
				+ unrealizedProfit + ", marginBalance=" + marginBalance + ", maintMargin=" + maintMargin
				+ ", initialMargin=" + initialMargin + ", positionInitialMargin=" + positionInitialMargin
				+ ", openOrderInitialMargin=" + openOrderInitialMargin + ", maxWithdrawAmount=" + maxWithdrawAmount
				+ ", crossWalletBalance=" + crossWalletBalance + ", crossUnPnl=" + crossUnPnl + ", availableBalance="
				+ availableBalance + ", marginAvailable=" + marginAvailable + ", updateTime=" + updateTime + "]";
	}
}
