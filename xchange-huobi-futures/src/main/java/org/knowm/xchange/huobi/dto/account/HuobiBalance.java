package org.knowm.xchange.huobi.dto.account;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class HuobiBalance {

	private final String symbol;
	private final BigDecimal marginBalance;
	private final BigDecimal marginPosition;
	private final BigDecimal marginFrozen;
	private final BigDecimal marginAvailable;
	private final BigDecimal profitReal;
	private final BigDecimal profitUnreal;
	private final BigDecimal riskRate;
	private final BigDecimal withdrawAvailable;
	private final BigDecimal liquidationPrice;
	private final BigDecimal leverRate;
	private final BigDecimal adjustFactor;
	private final BigDecimal marginStatic;
	private final BigDecimal isDebit;
	private final BigDecimal transferProfitRatio;
	// private final HuobiBalanceRecord[] list;

	public HuobiBalance(@JsonProperty("symbol") String symbol, @JsonProperty("margin_balance") BigDecimal marginBalance,
			@JsonProperty("margin_position") BigDecimal marginPosition,
			@JsonProperty("margin_frozen") BigDecimal marginFrozen,
			@JsonProperty("margin_available") BigDecimal marginAvailable,
			@JsonProperty("profit_real") BigDecimal profitReal, @JsonProperty("profit_unreal") BigDecimal profitUnreal,
			@JsonProperty("risk_rate") BigDecimal riskRate,
			@JsonProperty("withdraw_available") BigDecimal withdrawAvailable,
			@JsonProperty("liquidation_price") BigDecimal liquidationPrice,
			@JsonProperty("lever_rate") BigDecimal leverRate, @JsonProperty("adjust_factor") BigDecimal adjustFactor,
			@JsonProperty("margin_static") BigDecimal marginStatic, @JsonProperty("is_debit") BigDecimal isDebit,
			@JsonProperty("transfer_profit_ratio") BigDecimal transferProfitRatio) {

		this.symbol = symbol;
		this.marginBalance = marginBalance;
		this.marginPosition = marginPosition;
		this.marginFrozen = marginFrozen;
		this.marginAvailable = marginAvailable;
		this.profitReal = profitReal;
		this.profitUnreal = profitUnreal;
		this.riskRate = riskRate;
		this.withdrawAvailable = withdrawAvailable;
		this.liquidationPrice = liquidationPrice;
		this.leverRate = leverRate;
		this.adjustFactor = adjustFactor;
		this.marginStatic = marginStatic;
		this.isDebit = isDebit;
		this.transferProfitRatio = transferProfitRatio;

	}

	public String getSymbol() {
		return symbol;
	}

	public BigDecimal getMarginBalance() {
		return marginBalance;
	}

	public BigDecimal getMarginPosition() {
		return marginPosition;
	}

	public BigDecimal getMarginFrozen() {
		return marginFrozen;
	}

	public BigDecimal getMarginAvailable() {
		return marginAvailable;
	}

	public BigDecimal getProfitReal() {
		return profitReal;
	}

	public BigDecimal getProfitUnreal() {
		return profitUnreal;
	}

	public BigDecimal getRiskRate() {
		return riskRate;
	}

	public BigDecimal getWithdrawAvailable() {
		return withdrawAvailable;
	}

	public BigDecimal getLiquidationPrice() {
		return liquidationPrice;
	}

	public BigDecimal getLeverRate() {
		return leverRate;
	}

	public BigDecimal getAdjustFactor() {
		return adjustFactor;
	}

	public BigDecimal getMarginStatic() {
		return marginStatic;
	}

	public BigDecimal getIsDebit() {
		return isDebit;
	}

	public BigDecimal getTransferProfitRatio() {
		return transferProfitRatio;
	}

}
