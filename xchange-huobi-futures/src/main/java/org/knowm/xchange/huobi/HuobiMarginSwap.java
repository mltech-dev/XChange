package org.knowm.xchange.huobi;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.knowm.xchange.huobi.dto.trade.HuobiCancelOrderRequest;
import org.knowm.xchange.huobi.dto.trade.HuobiCreateOrderRequest;
import org.knowm.xchange.huobi.dto.trade.results.HuobiCancelOrderResult;
import org.knowm.xchange.huobi.dto.trade.results.HuobiOrderResult;

import si.mazi.rescu.ParamsDigest;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public interface HuobiMarginSwap extends Huobi {

	@POST
	@Path("swap-api/v1/swap_order")
	@Consumes(MediaType.APPLICATION_JSON)
	HuobiOrderResult placeSwapOrder(HuobiCreateOrderRequest body, @QueryParam("AccessKeyId") String apiKey,
			@QueryParam("SignatureMethod") String signatureMethod, @QueryParam("SignatureVersion") int signatureVersion,
			@QueryParam("Timestamp") String nonce, @QueryParam("Signature") ParamsDigest signature) throws IOException;

	@POST
	@Path("swap-api/v1/swap_cancel")
	HuobiCancelOrderResult cancelSwapOrder(HuobiCancelOrderRequest huobiCancelOrderRequest,
			@QueryParam("AccessKeyId") String apiKey, @QueryParam("SignatureMethod") String signatureMethod,
			@QueryParam("SignatureVersion") int signatureVersion, @QueryParam("Timestamp") String nonce,
			@QueryParam("Signature") ParamsDigest signature) throws IOException;
}
