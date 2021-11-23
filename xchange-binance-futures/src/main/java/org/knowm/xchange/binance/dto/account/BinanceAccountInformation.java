package org.knowm.xchange.binance.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.List;

public final class BinanceAccountInformation {
	private final Short feeTier;
	private final boolean canTrade;
	private final boolean canDeposit;
	private final boolean canWithdraw;
	private final long updateTime;
	private final BigDecimal totalInitialMargin;
	private final BigDecimal totalMaintMargin;
	private final BigDecimal totalWalletBalance;
	private final BigDecimal totalUnrealizedProfit;
	private final BigDecimal totalMarginBalance;
	private final BigDecimal totalPositionInitialMargin;
	private final BigDecimal totalOpenOrderInitialMargin;
	private final BigDecimal totalCrossWalletBalance;
	private final BigDecimal totalCrossUnPnl;
	private final BigDecimal availableBalance;
	private final BigDecimal maxWithdrawAmount;
	private final List<BinanceBalance> assets;
	private final List<BinancePosition> positions;

	public BinanceAccountInformation(
			@JsonProperty("feeTier") Short feeTier,
			@JsonProperty("canTrade") boolean canTrade,
			@JsonProperty("canDeposit") boolean canDeposit,
			@JsonProperty("canWithdraw") boolean canWithdraw,
			@JsonProperty("updateTime") long updateTime,
			@JsonProperty("totalInitialMargin") BigDecimal totalInitialMargin,
			@JsonProperty("totalMaintMargin") BigDecimal totalMaintMargin,
			@JsonProperty("totalWalletBalance") BigDecimal totalWalletBalance,
			@JsonProperty("totalUnrealizedProfit") BigDecimal totalUnrealizedProfit,
			@JsonProperty("totalMarginBalance") BigDecimal totalMarginBalance,
			@JsonProperty("totalPositionInitialMargin") BigDecimal totalPositionInitialMargin,
			@JsonProperty("totalOpenOrderInitialMargin") BigDecimal totalOpenOrderInitialMargin,
			@JsonProperty("totalCrossWalletBalance") BigDecimal totalCrossWalletBalance,
			@JsonProperty("totalCrossUnPnl") BigDecimal totalCrossUnPnl,
			@JsonProperty("availableBalance") BigDecimal availableBalance,
			@JsonProperty("maxWithdrawAmount") BigDecimal maxWithdrawAmount,
			@JsonProperty("assets") List<BinanceBalance> assets,
			@JsonProperty("positions") List<BinancePosition> positions) {
		this.feeTier = feeTier;
		this.canTrade = canTrade;
		this.canDeposit = canDeposit;
		this.canWithdraw = canWithdraw;
		this.updateTime = updateTime;
		this.totalInitialMargin = totalInitialMargin;
		this.totalMaintMargin = totalMaintMargin;
		this.totalWalletBalance = totalWalletBalance;
		this.totalUnrealizedProfit = totalUnrealizedProfit;
		this.totalMarginBalance = totalMarginBalance;
		this.totalPositionInitialMargin = totalPositionInitialMargin;
		this.totalOpenOrderInitialMargin = totalOpenOrderInitialMargin;
		this.totalCrossWalletBalance = totalCrossWalletBalance;
		this.totalCrossUnPnl = totalCrossUnPnl;
		this.availableBalance = availableBalance;
		this.maxWithdrawAmount = maxWithdrawAmount;
		this.assets = assets;
		this.positions = positions;
	}

	public Short getFeeTier() {
		return feeTier;
	}

	public boolean isCanTrade() {
		return canTrade;
	}

	public boolean isCanDeposit() {
		return canDeposit;
	}

	public boolean isCanWithdraw() {
		return canWithdraw;
	}

	public long getUpdateTime() {
		return updateTime;
	}

	public BigDecimal getTotalInitialMargin() {
		return totalInitialMargin;
	}

	public BigDecimal getTotalMaintMargin() {
		return totalMaintMargin;
	}

	public BigDecimal getTotalWalletBalance() {
		return totalWalletBalance;
	}

	public BigDecimal getTotalUnrealizedProfit() {
		return totalUnrealizedProfit;
	}

	public BigDecimal getTotalMarginBalance() {
		return totalMarginBalance;
	}

	public BigDecimal getTotalPositionInitialMargin() {
		return totalPositionInitialMargin;
	}

	public BigDecimal getTotalOpenOrderInitialMargin() {
		return totalOpenOrderInitialMargin;
	}

	public BigDecimal getTotalCrossWalletBalance() {
		return totalCrossWalletBalance;
	}

	public BigDecimal getTotalCrossUnPnl() {
		return totalCrossUnPnl;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public BigDecimal getMaxWithdrawAmount() {
		return maxWithdrawAmount;
	}

	public List<BinanceBalance> getAssets() {
		return assets;
	}

	public List<BinancePosition> getPositions() {
		return positions;
	}

	@Override
	public String toString() {
		return "BinanceAccountInformation [feeTier=" + feeTier + ", canTrade=" + canTrade + ", canDeposit=" + canDeposit
				+ ", canWithdraw=" + canWithdraw + ", updateTime=" + updateTime + ", totalInitialMargin="
				+ totalInitialMargin + ", totalMaintMargin=" + totalMaintMargin + ", totalWalletBalance="
				+ totalWalletBalance + ", totalUnrealizedProfit=" + totalUnrealizedProfit + ", totalMarginBalance="
				+ totalMarginBalance + ", totalPositionInitialMargin=" + totalPositionInitialMargin
				+ ", totalOpenOrderInitialMargin=" + totalOpenOrderInitialMargin + ", totalCrossWalletBalance="
				+ totalCrossWalletBalance + ", totalCrossUnPnl=" + totalCrossUnPnl + ", availableBalance="
				+ availableBalance + ", maxWithdrawAmount=" + maxWithdrawAmount + ", assets=" + assets + ", positions="
				+ positions + "]";
	}
}
