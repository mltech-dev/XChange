package info.bitrich.xchangestream.binance.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import org.knowm.xchange.binance.BinanceAdapters;
import org.knowm.xchange.binance.dto.trade.BinanceOrder;
import org.knowm.xchange.binance.dto.trade.OrderSide;
import org.knowm.xchange.binance.dto.trade.OrderStatus;
import org.knowm.xchange.binance.dto.trade.OrderType;
import org.knowm.xchange.binance.dto.trade.TimeInForce;
import org.knowm.xchange.currency.Currency;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;

public class ExecutionReportBinanceUserTransaction extends ProductBinanceWebSocketTransaction {

	public enum ExecutionType {
		NEW,
		CANCELED,
		REPLACED,
		REJECTED,
		TRADE,
		EXPIRED
	}

	private final String clientOrderId;
	private final OrderSide side;
	private final OrderType orderType;
	private final TimeInForce timeInForce;
	private final BigDecimal orderQuantity;
	private final BigDecimal orderPrice;
	private final BigDecimal stopPrice;
	private final ExecutionType executionType;
	private final OrderStatus currentOrderStatus;
	private final String orderRejectReason;
	private final long orderId;
	private final BigDecimal lastExecutedQuantity;
	private final BigDecimal cumulativeFilledQuantity;
	private final BigDecimal lastExecutedPrice;
	private final BigDecimal commissionAmount;
	private final String commissionAsset;
	private final long timestamp;
	private final long tradeId;
	private final BigDecimal bidNotional;
	private final BigDecimal askNotional;
	private final boolean isMaker;
	private final boolean isReduceOnly;
	private final String stopPriceWorkingType;
	private final String originalOrderType;
	private final String positionSide;
	private final boolean isCloseAll;
	private final BigDecimal activationPrice;
	private final BigDecimal callbackRate;
	private final BigDecimal realizedProfit;

	public ExecutionReportBinanceUserTransaction(
			@JsonProperty("e") String eventType,
			@JsonProperty("T") long timestamp,
			@JsonProperty("E") String eventTime,
			@JsonProperty("o") ExecutionReportBinanceOrderUpdates orderUpdates
			) {
		super(eventType, eventTime, orderUpdates.symbol);
		this.clientOrderId = orderUpdates.clientOrderId;
		this.side = OrderSide.valueOf(orderUpdates.side);
		this.orderType = OrderType.valueOf(orderUpdates.orderType);
		this.timeInForce = TimeInForce.valueOf(orderUpdates.timeInForce);
		this.orderQuantity = orderUpdates.quantity;
		this.orderPrice = orderUpdates.price;
		this.stopPrice = orderUpdates.stopPrice;
		this.executionType = ExecutionType.valueOf(orderUpdates.currentExecutionType);
		this.currentOrderStatus = OrderStatus.valueOf(orderUpdates.currentOrderStatus);
		this.orderRejectReason = orderUpdates.orderRejectReason;
		this.orderId = orderUpdates.orderId;
		this.lastExecutedQuantity = orderUpdates.lastFilledQuantity;
		this.cumulativeFilledQuantity = orderUpdates.cumulativeFilledQuantity;
		this.lastExecutedPrice = orderUpdates.lastFilledPrice;
		this.commissionAmount = orderUpdates.commissionAmount;
		this.commissionAsset = orderUpdates.commissionAsset;
		this.timestamp = timestamp;
		this.tradeId = orderUpdates.tradeId;
		this.bidNotional = orderUpdates.bidNotional;
		this.askNotional = orderUpdates.askNotional;
		this.isMaker = orderUpdates.isMaker;
		this.isReduceOnly = orderUpdates.isReduceOnly;
		this.stopPriceWorkingType = orderUpdates.stopPriceWorkingType;
		this.originalOrderType = orderUpdates.originalOrderType;
		this.positionSide = orderUpdates.positionSide;
		this.isCloseAll = orderUpdates.isCloseAll;
		this.activationPrice = orderUpdates.activationPrice;
		this.callbackRate = orderUpdates.callbackRate;
		this.realizedProfit = orderUpdates.realizedProfit;
	}

	public String getClientOrderId() {
		return clientOrderId;
	}

	public OrderSide getSide() {
		return side;
	}

	public OrderType getOrderType() {
		return orderType;
	}

	public TimeInForce getTimeInForce() {
		return timeInForce;
	}

	public BigDecimal getOrderQuantity() {
		return orderQuantity;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public BigDecimal getStopPrice() {
		return stopPrice;
	}

	public ExecutionType getExecutionType() {
		return executionType;
	}

	public OrderStatus getCurrentOrderStatus() {
		return currentOrderStatus;
	}

	public String getOrderRejectReason() {
		return orderRejectReason;
	}

	public long getOrderId() {
		return orderId;
	}

	public BigDecimal getLastExecutedQuantity() {
		return lastExecutedQuantity;
	}

	public BigDecimal getCumulativeFilledQuantity() {
		return cumulativeFilledQuantity;
	}

	public BigDecimal getLastExecutedPrice() {
		return lastExecutedPrice;
	}

	public BigDecimal getCommissionAmount() {
		return commissionAmount;
	}

	public String getCommissionAsset() {
		return commissionAsset;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public long getTradeId() {
		return tradeId;
	}


	public BigDecimal getBidNotional() {
		return bidNotional;
	}

	public BigDecimal getAskNotional() {
		return askNotional;
	}

	public boolean isMaker() {
		return isMaker;
	}

	public boolean isReduceOnly() {
		return isReduceOnly;
	}

	public String getStopPriceWorkingType() {
		return stopPriceWorkingType;
	}

	public String getOriginalOrderType() {
		return originalOrderType;
	}

	public String getPositionSide() {
		return positionSide;
	}

	public boolean isCloseAll() {
		return isCloseAll;
	}

	public BigDecimal getActivationPrice() {
		return activationPrice;
	}

	public BigDecimal getCallbackRate() {
		return callbackRate;
	}

	public BigDecimal getRealizedProfit() {
		return realizedProfit;
	}

	public UserTrade toUserTrade() {
		if (executionType != ExecutionType.TRADE) throw new IllegalStateException("Not a trade");
		return new UserTrade.Builder()
				.type(BinanceAdapters.convert(side))
				.originalAmount(lastExecutedQuantity)
				.currencyPair(currencyPair)
				.price(lastExecutedPrice)
				.timestamp(getEventTime())
				.id(Long.toString(tradeId))
				.orderId(Long.toString(orderId))
				.feeAmount(commissionAmount)
				.feeCurrency(Currency.getInstance(commissionAsset))
		        .orderUserReference(clientOrderId)
				.build();
	}

	public Order toOrder() {
		return BinanceAdapters.adaptOrder(
				new BinanceOrder(
						BinanceAdapters.toSymbol(getCurrencyPair()),
						orderId,
						clientOrderId,
						orderPrice,
						orderQuantity,
						lastExecutedQuantity,
						cumulativeFilledQuantity,
						currentOrderStatus,
						timeInForce,
						orderType,
						side,
						stopPrice,
						BigDecimal.ZERO,
						getEventTime().getTime()));
	}

	@Override
	public String toString() {
		return "ExecutionReportBinanceUserTransaction [eventTime="
				+ getEventTime()
				+ ", currencyPair="
				+ getCurrencyPair()
				+ ", clientOrderId="
				+ clientOrderId
				+ ", side="
				+ side
				+ ", orderType="
				+ orderType
				+ ", timeInForce="
				+ timeInForce
				+ ", quantity="
				+ orderQuantity
				+ ", price="
				+ orderPrice
				+ ", stopPrice="
				+ stopPrice
				+ ", executionType="
				+ executionType
				+ ", currentOrderStatus="
				+ currentOrderStatus
				+ ", orderRejectReason="
				+ orderRejectReason
				+ ", orderId="
				+ orderId
				+ ", lastExecutedQuantity="
				+ lastExecutedQuantity
				+ ", cumulativeFilledQuantity="
				+ cumulativeFilledQuantity
				+ ", lastExecutedPrice="
				+ lastExecutedPrice
				+ ", commissionAmount="
				+ commissionAmount
				+ ", commissionAsset="
				+ commissionAsset
				+ ", timestamp="
				+ timestamp
				+ ", tradeId="
				+ tradeId
				+ "]";
	}
}
