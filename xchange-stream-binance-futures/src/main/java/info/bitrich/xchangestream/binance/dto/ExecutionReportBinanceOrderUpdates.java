package info.bitrich.xchangestream.binance.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExecutionReportBinanceOrderUpdates {
    String symbol;
    String clientOrderId;
    String side;
    String orderType;
    String timeInForce;
    BigDecimal quantity;
    BigDecimal price;
    BigDecimal stopPrice;
    BigDecimal avgPrice;
    String currentExecutionType;
    String currentOrderStatus;
    String orderRejectReason;
    long orderId;
    BigDecimal lastFilledQuantity;
    BigDecimal cumulativeFilledQuantity;
    BigDecimal lastFilledPrice;
    BigDecimal commissionAmount;
    String commissionAsset;
    long orderTradeTime;
    long tradeId;
    BigDecimal bidNotional;
    BigDecimal askNotional;
    boolean isMaker;
    boolean isReduceOnly;
    String stopPriceWorkingType;
    String originalOrderType;
    String positionSide;
    boolean isCloseAll;
    BigDecimal activationPrice;
    BigDecimal callbackRate;
    BigDecimal realizedProfit;
    
    public ExecutionReportBinanceOrderUpdates(
    	    @JsonProperty("s") String symbol,
    	    @JsonProperty("c") String clientOrderId,
    	    @JsonProperty("S") String side,
    	    @JsonProperty("o") String orderType,
    	    @JsonProperty("f") String timeInForce,
    	    @JsonProperty("q") BigDecimal quantity,
    	    @JsonProperty("p") BigDecimal price,
    	    @JsonProperty("sp") BigDecimal stopPrice,
    	    @JsonProperty("ap") BigDecimal avgPrice,
    	    @JsonProperty("x") String currentExecutionType,
    	    @JsonProperty("X") String currentOrderStatus,
    	    @JsonProperty("i") long orderId,
    	    @JsonProperty("l") BigDecimal lastFilledQuantity,
    	    @JsonProperty("z") BigDecimal cumulativeFilledQuantity,
    	    @JsonProperty("L") BigDecimal lastFilledPrice,
    	    @JsonProperty("r") String orderRejectReason,
    	    @JsonProperty("N") String commissionAsset,
    	    @JsonProperty("n") BigDecimal commissionAmount,
    	    @JsonProperty("T") long orderTradeTime,
    	    @JsonProperty("t") long tradeId,
    	    @JsonProperty("b") BigDecimal bidNotional,
    	    @JsonProperty("a") BigDecimal askNotional,
    	    @JsonProperty("m") boolean isMaker,
    	    @JsonProperty("R") boolean isReduceOnly,
    	    @JsonProperty("wt") String stopPriceWorkingType,
    	    @JsonProperty("ot") String originalOrderType,
    	    @JsonProperty("ps") String positionSide,
    	    @JsonProperty("cp") boolean isCloseAll,
    	    @JsonProperty("AP") BigDecimal activationPrice,
    	    @JsonProperty("cr") BigDecimal callbackRate,
    	    @JsonProperty("rp") BigDecimal realizedProfit
    		) {
    	this.symbol = symbol;
    	this.clientOrderId = clientOrderId;
    	this.side = side;
    	this.orderType = orderType;
    	this.timeInForce = timeInForce;
    	this.quantity = quantity;
    	this.price = price;
    	this.stopPrice = stopPrice;
    	this.avgPrice = avgPrice;
    	this.currentExecutionType = currentExecutionType;
    	this.currentOrderStatus = currentOrderStatus;
    	this.orderRejectReason = orderRejectReason;
    	this.orderId = orderId;
    	this.lastFilledQuantity = lastFilledQuantity;
    	this.cumulativeFilledQuantity = cumulativeFilledQuantity;
    	this.lastFilledPrice = lastFilledPrice;
    	this.commissionAmount = commissionAmount;
    	this.commissionAsset = commissionAsset;
    	this.orderTradeTime = orderTradeTime;
    	this.tradeId = tradeId;
    	this.bidNotional = bidNotional;
    	this.askNotional = askNotional;
    	this.isMaker = isMaker;
    	this.isReduceOnly = isReduceOnly;
    	this.stopPriceWorkingType = stopPriceWorkingType;
    	this.originalOrderType = originalOrderType;
    	this.positionSide = positionSide;
    	this.isCloseAll = isCloseAll;
    	this.activationPrice = activationPrice;
    	this.callbackRate = callbackRate;
    	this.realizedProfit = realizedProfit;
    }

}
