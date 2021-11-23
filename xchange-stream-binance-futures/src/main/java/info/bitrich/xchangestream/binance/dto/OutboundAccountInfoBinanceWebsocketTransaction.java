package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import org.knowm.xchange.dto.account.Balance;

public class OutboundAccountInfoBinanceWebsocketTransaction
extends BaseBinanceWebSocketTransaction {

	private final long updateTime;
	private final OutboundAccountInfoUpdateData updateData;
	
	public OutboundAccountInfoBinanceWebsocketTransaction(
			@JsonProperty("e") String eventType,
			@JsonProperty("E") String eventTime,
			@JsonProperty("T") long updateTime,
			@JsonProperty("a") OutboundAccountInfoUpdateData updateData) {
		super(eventType, eventTime);
		this.updateTime = updateTime;
		this.updateData = updateData;
	}

	public List<OutboundAccountInfoBalance> getBalances() {
		return updateData.getBalances();
	}

	public List<Balance> toBalanceList() {
		return updateData.getBalances().stream()
				.map(
						b ->
						new Balance(
								b.getCurrency(),
								b.getWalletBalance(),
								b.getWalletBalance(),
								BigDecimal.ZERO,
								BigDecimal.ZERO,
								BigDecimal.ZERO,
								BigDecimal.ZERO,
								BigDecimal.ZERO,
								new Date(this.updateTime)))
				.collect(Collectors.toList());
	}

}
