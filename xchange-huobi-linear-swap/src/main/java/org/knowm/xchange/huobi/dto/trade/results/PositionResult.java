package org.knowm.xchange.huobi.dto.trade.results;

import java.util.List;

import org.knowm.xchange.huobi.dto.HuobiListResult;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PositionResult extends HuobiListResult<PositionResultData>  {

	public PositionResult(@JsonProperty("status") String status, 
			@JsonProperty("err_code") String errCode, 
			@JsonProperty("err_msg")String errMsg, 
			@JsonProperty("data") List<PositionResultData> result) {
		super(status, errCode, errMsg, result);
	}

}
