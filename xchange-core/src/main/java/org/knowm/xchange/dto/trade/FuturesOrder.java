package org.knowm.xchange.dto.trade;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

import org.knowm.xchange.currency.CurrencyPair;
import org.knowm.xchange.dto.Order;
import org.knowm.xchange.instrument.Instrument;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = FuturesOrder.Builder.class)
public class FuturesOrder extends Order implements Comparable<FuturesOrder> {

	private static final long serialVersionUID = 6278344998667513613L;

	/** The price */
	protected final BigDecimal price;

	/** Contract Code e.g. ada201225 **/
	protected final String contractCode;
	
	/** Contract type e.g. quarter **/
	protected final String contractType;
	
	/** lever rate **/
	protected final int leverRate;
	
	/** offset **/
	protected final String offset;
	
	/** order price type **/
	protected final String orderPriceType;
	
	public FuturesOrder(OrderType type, BigDecimal originalAmount, Instrument instrument, String id, Date timestamp, BigDecimal price, String contractCode, String contractType, int leverRate, String offset, String orderPriceType, String userReference) {
		super(type, originalAmount, instrument, id, timestamp,null,null,null,null,userReference);
		this.price = price;
		this.contractCode = contractCode;
		this.contractType = contractType;
		this.leverRate = leverRate;
		this.offset = offset;
		this.orderPriceType = orderPriceType;
	}

	@Override
	public int compareTo(FuturesOrder futureOrder) {
	    final int ret;

	    if (this.getType() == futureOrder.getType()) {
	      // Same side
	      ret =
	          this.getPrice().compareTo(futureOrder.getPrice())
	              * (getType() == OrderType.BID ? -1 : 1);
	    } else {
	      // Keep bid side be less than ask side
	      ret = this.getType() == OrderType.BID ? -1 : 1;
	    }

	    return ret;

	}

	public BigDecimal getPrice() {
		return price;
	}
	
	public String getContractCode() {
		return contractCode;
	}

	public String getContractType() {
		return contractType;
	}

	public int getLeverRate() {
		return leverRate;
	}

	public String getOffset() {
		return offset;
	}

	public String getOrderPriceType() {
		return orderPriceType;
	}



	@JsonPOJOBuilder(withPrefix = "")
	  public static class Builder extends Order.Builder {

		/** The price */
		protected BigDecimal price;

		/** Contract Code e.g. ada201225 **/
		protected String contractCode;
		
		/** Contract type e.g. quarter **/
		protected String contractType;
		
		/** lever rate **/
		protected int leverRate;
		
		/** offset **/
		protected String offset;
		
		/** order price type **/
		protected String orderPriceType;

	    @JsonCreator
	    public Builder(
	        @JsonProperty("orderType") OrderType orderType,
	        @JsonProperty("instrument") Instrument instrument) {

	      super(orderType, instrument);
	    }

	    public static Builder from(Order order) {

	      Builder builder =
	          new Builder(order.getType(), order.getInstrument())
	              .originalAmount(order.getOriginalAmount())
	              .cumulativeAmount(order.getCumulativeAmount())
	              .timestamp(order.getTimestamp())
	              .id(order.getId())
	              .flags(order.getOrderFlags())
	              .orderStatus(order.getStatus())
	              .fee(order.getFee())
	              .averagePrice(order.getAveragePrice())
	              .userReference(order.getUserReference());
	      if (order instanceof FuturesOrder) {
	        FuturesOrder futureOrder = (FuturesOrder) order;
	        builder.price(futureOrder.getPrice());
	      }
	      return builder;
	    }

	    @Override
	    public Builder orderType(OrderType orderType) {

	      return (Builder) super.orderType(orderType);
	    }

	    @Override
	    public Builder originalAmount(BigDecimal originalAmount) {

	      return (Builder) super.originalAmount(originalAmount);
	    }

	    @Override
	    public Builder cumulativeAmount(BigDecimal originalAmount) {

	      return (Builder) super.cumulativeAmount(originalAmount);
	    }

	    @Override
	    public Builder remainingAmount(BigDecimal remainingAmount) {

	      return (Builder) super.remainingAmount(remainingAmount);
	    }

	    @Override
	    @Deprecated
	    public Builder currencyPair(CurrencyPair currencyPair) {

	      return (Builder) super.currencyPair(currencyPair);
	    }

	    @Override
	    public Builder instrument(Instrument instrument) {

	      return (Builder) super.instrument(instrument);
	    }

	    @Override
	    public Builder id(String id) {

	      return (Builder) super.id(id);
	    }

	    @Override
	    public Builder userReference(String userReference) {

	      return (Builder) super.userReference(userReference);
	    }

	    @Override
	    public Builder timestamp(Date timestamp) {

	      return (Builder) super.timestamp(timestamp);
	    }

	    @Override
	    public Builder orderStatus(Order.OrderStatus status) {

	      return (Builder) super.orderStatus(status);
	    }

	    @Override
	    public Builder averagePrice(BigDecimal averagePrice) {

	      return (Builder) super.averagePrice(averagePrice);
	    }

	    @Override
	    public Builder flag(IOrderFlags flag) {

	      return (Builder) super.flag(flag);
	    }

	    @Override
	    public Builder flags(Set<IOrderFlags> flags) {

	      return (Builder) super.flags(flags);
	    }

	    @Override
	    public Builder fee(BigDecimal fee) {
	      return (Builder) super.fee(fee);
	    }

	    public Builder price(BigDecimal price) {

	      this.price = price;
	      return this;
	    }
	    
	    public Builder contractCode(String contractCode) {
	    	this.contractCode = contractCode;
	    	return this;
	    }
	    
	    public Builder contractType(String contractType) {
	    	this.contractType = contractType;
	    	return this;
	    }
	    
	    public Builder leverRate(int leverRate) {
	    	this.leverRate = leverRate;
	    	return this;
	    }
	    
	    public Builder offset(String offset) {
	    	this.offset = offset;
	    	return this;
	    }
	    
	    public Builder orderPriceType(String orderPriceType) {
	    	this.orderPriceType = orderPriceType;
	    	return this;
	    }

	    @Override
	    public FuturesOrder build() {

	      FuturesOrder order =
	          new FuturesOrder(
	              orderType,
	              originalAmount,
	              instrument,
	              id,
	              timestamp,
	              price,
	              contractCode,
	              contractType,
	              leverRate,
	              offset,
	              orderPriceType,
	              userReference);
	      order.setOrderFlags(flags);
	      order.setOrderStatus(status);
	      order.setAveragePrice(price);
	      order.setCumulativeAmount(cumulativeAmount);
	      order.setFee(fee);
	      order.setOrderFlags(flags);
	      order.setLeverage(leverage);
	      return order;
	    }
	  }

}
