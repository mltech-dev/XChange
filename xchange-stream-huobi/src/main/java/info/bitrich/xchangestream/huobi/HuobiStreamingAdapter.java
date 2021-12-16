package info.bitrich.xchangestream.huobi;

import java.math.BigDecimal;
import java.util.Date;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.Order.OrderType;
import org.knowm.xchange.dto.trade.LimitOrder;
import org.knowm.xchange.dto.trade.MarketOrder;
import org.knowm.xchange.dto.trade.StopOrder;
import org.knowm.xchange.dto.trade.UserTrade;
import org.knowm.xchange.huobi.HuobiAdapters;

import com.huobi.model.trade.OrderUpdateV2;

public class HuobiStreamingAdapter extends HuobiAdapters {

	private static boolean isLimit(String type) { // startswith to support -fok and -ioc
		return type.startsWith("buy-limit") || type.startsWith("sell-limit");
	}

	private static boolean isMarket(String type) {
		return type.equals("buy-market") || type.equals("sell-market");
	}

	private static boolean isStop(String type) {
		return type.startsWith("buy-stop") || type.startsWith("sell-stop");
	}

	public static UserTrade adaptUserTrades(OrderUpdateV2 orderUpdate) {
		return new UserTrade.Builder()
        .type(adaptOrderType(orderUpdate.getType()))
        .originalAmount(orderUpdate.getTradeVolume())
        .currencyPair(adaptCurrencyPair(orderUpdate.getSymbol()))
        .price(orderUpdate.getTradePrice())
        .timestamp(new Date(orderUpdate.getTradeTime()))
        .id(String.valueOf(orderUpdate.getTradeId())) // Trade id
        .orderId(String.valueOf(orderUpdate.getOrderId())) // Original order id
        .feeAmount(BigDecimal.ZERO)
        .feeCurrency(adaptCurrencyPair(orderUpdate.getSymbol()).counter)
        .build();
	}

	public static Order adaptOrderUpdates(OrderUpdateV2 orderUpdate) {
		Order order = null;
		OrderType orderType = adaptOrderType(orderUpdate.getType());
		CurrencyPair currencyPair = adaptCurrencyPair(orderUpdate.getSymbol());
		BigDecimal openOrderAvgPrice = BigDecimal.ZERO;
		if (isMarket(orderUpdate.getType())) {
			order =
					new MarketOrder(
							orderType,
							orderUpdate.getOrderSize(),
							currencyPair,
							String.valueOf(orderUpdate.getOrderId()),
							new Date(orderUpdate.getOrderCreateTime()),
							orderUpdate.getTradePrice(),
							orderUpdate.getTradeVolume(),
							null,
							adaptOrderStatus(orderUpdate.getOrderStatus()),
							orderUpdate.getClientOrderId());
		}
		if (isLimit(orderUpdate.getType())) {
			BigDecimal cumAmount = orderUpdate.getOrderSize().subtract(orderUpdate.getRemainAmt());
			order =
					new LimitOrder(
							orderType,
							orderUpdate.getOrderSize(),
							currencyPair,
							String.valueOf(orderUpdate.getOrderId()),
							new Date(orderUpdate.getOrderCreateTime()),
							orderUpdate.getOrderPrice(),
							orderUpdate.getTradePrice(),
							cumAmount,
							null,
							adaptOrderStatus(orderUpdate.getOrderStatus()),
							orderUpdate.getClientOrderId());
		}
		if (isStop(orderUpdate.getType())) {
			order =
					new StopOrder(
							orderType,
							orderUpdate.getOrderSize(),
							currencyPair,
							String.valueOf(orderUpdate.getOrderId()),
							new Date(orderUpdate.getOrderCreateTime()),
							orderUpdate.getOrderPrice(),
							orderUpdate.getTradePrice(),
							openOrderAvgPrice,
							orderUpdate.getTradePrice(),
							null,
							adaptOrderStatus(orderUpdate.getOrderStatus()),
							orderUpdate.getClientOrderId(),
							null);
		}

		order.setAveragePrice(openOrderAvgPrice);
		return order;
	}
}
