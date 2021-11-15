package org.knowm.xchange.binance.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.util.Date;

public final class BinanceTrade {
//    "realizedPnl": "0",
//    "marginAsset": "USDT",
//    "quoteQty": "64.94004",
//    "commission": "0.02597601",
//    "commissionAsset": "USDT",
//    "time": 1636636986415,
//    "positionSide": "BOTH",
//    "maker": false,
//    "buyer": true
	
	
  public final long id;
  public final long orderId;
  public final String symbol;
  public final OrderSide side;
  public final BigDecimal price;
  public final BigDecimal qty;
  public final BigDecimal quoteQty;
  public final BigDecimal realizedPnl;
  public final String marginAsset;
  public final BigDecimal commission;
  public final String commissionAsset;
  public final PositionSide positionSide;
  public final long time;
  public final boolean isBuyerMaker;
  public final boolean maker;
  public final boolean buyer;

  public BinanceTrade(
      @JsonProperty("id") long id,
      @JsonProperty("orderId") long orderId,
      @JsonProperty("symbol") String symbol,
      @JsonProperty("side") OrderSide side,
      @JsonProperty("price") BigDecimal price,
      @JsonProperty("qty") BigDecimal qty,
      @JsonProperty("quoteQty") BigDecimal quoteQty,
      @JsonProperty("realizedPnl") BigDecimal realizedPnl,
      @JsonProperty("marginAsset") String marginAsset,
      @JsonProperty("commission") BigDecimal commission,
      @JsonProperty("commissionAsset") String commissionAsset,
      @JsonProperty("positionSide") PositionSide positionSide,
      @JsonProperty("isBuyerMaker") boolean isBuyerMaker,
      @JsonProperty("maker") boolean maker,
      @JsonProperty("buyer") boolean buyer,
      @JsonProperty("time") long time
      ) {
    this.id = id;
    this.orderId = orderId;
    this.symbol = symbol;
    this.side = side;
    this.price = price;
    this.qty = qty;
    this.quoteQty = quoteQty;
    this.realizedPnl = realizedPnl;
    this.marginAsset = marginAsset;
    this.commission = commission;
    this.commissionAsset = commissionAsset;
    this.positionSide = positionSide;
    this.isBuyerMaker = isBuyerMaker;
    this.buyer = buyer;
    this.maker = maker;
    this.time = time;
  }

  public Date getTime() {
    return new Date(time);
  }
}
