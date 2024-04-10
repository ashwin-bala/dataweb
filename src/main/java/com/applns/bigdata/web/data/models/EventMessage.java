package com.applns.bigdata.web.data.models;

public class EventMessage {
	private String additionalFilters;
	private String toDate;
	private String fromDate;
	public String getAdditionalFilters() {
		return additionalFilters;
	}
	public void setAdditionalFilters(String additionalFilters) {
		this.additionalFilters = additionalFilters;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	
}
