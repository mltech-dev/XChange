package org.knowm.xchange.huobi.dto.trade.results;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class HuobiCancelOrderData {
	
	private final List<HuobiCancelOrderErrorData> error;
	private final String successes;
	
	public HuobiCancelOrderData(@JsonProperty("errors") List<HuobiCancelOrderErrorData> error, @JsonProperty("successes") String successes) {
		super();
		this.error = error;
		this.successes = successes;
	}

	public List<HuobiCancelOrderErrorData> getError() {
		return error;
	}

	public String getSuccesses() {
		return successes;
	}
	
}
