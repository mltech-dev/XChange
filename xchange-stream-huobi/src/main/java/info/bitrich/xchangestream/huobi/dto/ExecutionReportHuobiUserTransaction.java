package info.bitrich.xchangestream.huobi.dto;

import org.knowm.xchange.dto.Order;
import org.knowm.xchange.dto.trade.UserTrade;

import com.huobi.model.trade.OrderUpdateV2Event;

import info.bitrich.xchangestream.huobi.HuobiStreamingAdapter;

public class ExecutionReportHuobiUserTransaction {
	private OrderUpdateV2Event event;
	
	public ExecutionReportHuobiUserTransaction(OrderUpdateV2Event event) {
		this.event = event;
	}

	public OrderUpdateV2Event getEvent() {
		return event;
	}
	
	public static Order toOrder(ExecutionReportHuobiUserTransaction transaction) {
		return HuobiStreamingAdapter.adaptOrderUpdates(transaction.getEvent().getOrderUpdate());
	}
	
	public static UserTrade toUserTrade(ExecutionReportHuobiUserTransaction transaction) {
		return null;
	}
}
