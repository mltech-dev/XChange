package info.bitrich.xchangestream.binance.dto;

import java.math.BigDecimal;

import org.knowm.xchange.currency.Currency;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutboundAccountInfoBalance {
	
	 private final Currency currency;
	private final BigDecimal walletBalance;
	private final BigDecimal crossWalletBalance;
	private final BigDecimal balanceChange;
	
	public OutboundAccountInfoBalance(
			@JsonProperty("a") String asset,
			@JsonProperty("wb") BigDecimal walletBalance,
			@JsonProperty("cw") BigDecimal crossWalletBalance,
			@JsonProperty("bc") BigDecimal balanceChange
			) {
		this.currency = Currency.getInstance(asset);
		this.walletBalance = walletBalance;
		this.crossWalletBalance = crossWalletBalance;
		this.balanceChange = balanceChange;
	}

	public Currency getCurrency() {
		return currency;
	}

	public BigDecimal getWalletBalance() {
		return walletBalance;
	}

	public BigDecimal getCrossWalletBalance() {
		return crossWalletBalance;
	}

	public BigDecimal getBalanceChange() {
		return balanceChange;
	}

	@Override
	public String toString() {
		return "OutboundAccountInfoBalance [currency=" + currency + ", walletBalanc=" + walletBalance
				+ ", crossWalletBalance=" + crossWalletBalance + ", balanceChange=" + balanceChange + "]";
	}
}
