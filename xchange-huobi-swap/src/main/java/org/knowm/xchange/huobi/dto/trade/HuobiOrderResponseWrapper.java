package org.knowm.xchange.huobi.dto.trade;

import com.fasterxml.jackson.annotation.JsonProperty;

public class HuobiOrderResponseWrapper {
	HuobiOrder[] orders;
	
	int totalPages;
	
	int currentPage;
	
	int totalSize;

	public HuobiOrderResponseWrapper(
			@JsonProperty("orders") HuobiOrder[] orders, 
			@JsonProperty("total_page") int totalPages, 
			@JsonProperty("current_page") int currentPage, 
			@JsonProperty("total_size") int totalSize) {
		super();
		this.orders = orders;
		this.totalPages = totalPages;
		this.currentPage = currentPage;
		this.totalSize = totalSize;
	}

	public HuobiOrder[] getOrders() {
		return orders;
	}

	public void setOrders(HuobiOrder[] orders) {
		this.orders = orders;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalSize() {
		return totalSize;
	}

	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	
	
}
