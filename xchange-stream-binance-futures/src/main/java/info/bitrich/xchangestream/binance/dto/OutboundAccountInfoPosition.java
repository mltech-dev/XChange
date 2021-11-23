package info.bitrich.xchangestream.binance.dto;

import java.math.BigDecimal;

import org.knowm.xchange.binance.dto.trade.PositionSide;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutboundAccountInfoPosition {

	private final String symbol;
	private final BigDecimal positionAmount;
	private final BigDecimal entryPrice;
	private final BigDecimal accumulatedRealized;
	private final BigDecimal unrealizedPnl;
	private final String marginType;
	private final BigDecimal isolatedWallet;
	private final PositionSide positionSide;

		public OutboundAccountInfoPosition(
				@JsonProperty("s") String symbol,
				@JsonProperty("pa") BigDecimal positionAmount,
				@JsonProperty("ep") BigDecimal entryPrice,
				@JsonProperty("cr") BigDecimal accumulatedRealized,
				@JsonProperty("up") BigDecimal unrealizedPnl,
				@JsonProperty("mt") String marginType,
				@JsonProperty("iw") BigDecimal isolatedWallet,
				@JsonProperty("ps") PositionSide positionSide
				) {
			this.symbol = symbol;
			this.positionAmount = positionAmount;
			this.entryPrice = entryPrice;
			this.accumulatedRealized = accumulatedRealized;
			this.unrealizedPnl = unrealizedPnl;
			this.marginType = marginType;
			this.isolatedWallet = isolatedWallet;
			this.positionSide = positionSide;
		}

		public String getSymbol() {
			return symbol;
		}

		public BigDecimal getPositionAmount() {
			return positionAmount;
		}

		public BigDecimal getEntryPrice() {
			return entryPrice;
		}

		public BigDecimal getAccumulatedRealized() {
			return accumulatedRealized;
		}

		public BigDecimal getUnrealizedPnl() {
			return unrealizedPnl;
		}

		public String getMarginType() {
			return marginType;
		}

		public BigDecimal getIsolatedWallet() {
			return isolatedWallet;
		}

		public PositionSide getPositionSide() {
			return positionSide;
		}

		@Override
		public String toString() {
			return "OutboundAccountInfoPosition [symbol=" + symbol + ", positionAmount=" + positionAmount
					+ ", entryPrice=" + entryPrice + ", accumulatedRealized=" + accumulatedRealized + ", unrealizedPnl="
					+ unrealizedPnl + ", marginType=" + marginType + ", isolatedWallet=" + isolatedWallet
					+ ", positionSide=" + positionSide + "]";
		}
}
