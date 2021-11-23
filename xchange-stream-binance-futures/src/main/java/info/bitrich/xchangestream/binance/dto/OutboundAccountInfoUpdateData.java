package info.bitrich.xchangestream.binance.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OutboundAccountInfoUpdateData {

	private final List<OutboundAccountInfoBalance> balances;
	private final List<OutboundAccountInfoPosition> positions;
	
	public OutboundAccountInfoUpdateData(
			@JsonProperty("B") List<OutboundAccountInfoBalance> balances,
			@JsonProperty("P") List<OutboundAccountInfoPosition> positions
			) {
		this.balances = balances;
		this.positions = positions;
	}

	public List<OutboundAccountInfoBalance> getBalances() {
		return balances;
	}

	public List<OutboundAccountInfoPosition> getPositions() {
		return positions;
	}

	@Override
	public String toString() {
		return "OutboundAccountInfoUpdateData [balances=" + balances + ", positions=" + positions + "]";
	}
}
