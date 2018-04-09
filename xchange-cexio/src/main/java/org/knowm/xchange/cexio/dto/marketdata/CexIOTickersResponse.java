package org.knowm.xchange.cexio.dto.marketdata;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.knowm.xchange.cexio.dto.CexIOApiResponse;

import java.util.List;

public class CexIOTickersResponse extends CexIOApiResponse<List<CexIOTicker>> {

  public CexIOTickersResponse(@JsonProperty("e") final String e, @JsonProperty("data") final List<CexIOTicker> data, @JsonProperty("ok") final String ok,
                              @JsonProperty("error") final String error) {
    super(e, data, ok, error);
  }
}
