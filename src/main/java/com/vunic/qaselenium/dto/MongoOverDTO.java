package com.vunic.qaselenium.dto;

import java.io.*;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import com.vunic.core.base.BaseDTO;

public class MongoOverDTO extends BaseDTO implements Serializable{
	
	private static final long serialVersionUID = 2145430430684248907L;

	private int id;
	private String origin;
	private String airline;
	private Date buyingDateFrom;
	private Date buyingDateTo;
	private Date travelDateFrom;
	
	public Date getTravelDateFrom() {
		return travelDateFrom;
	}

	public void setTravelDateFrom(Date travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}

	private Date travelDateTo;
	private String overPercentage;
	private String percentageToApply;
	
	
	public String getOverPercentage() {
		return overPercentage;
	}

	public String getPercentageToApply() {
		return percentageToApply;
	}

	public void setPercentageToApply(String percentageToApply) {
		this.percentageToApply = percentageToApply;
	}

	public void setOverPercentage(String overPercentage) {
		this.overPercentage = overPercentage;
	}

	public Date getBuyingDateTo() {
		return buyingDateTo;
	}

	public String getBuyingDateTo_ToString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return sdf.format(buyingDateTo);
	}
	
	public Date getTravelDateTo() {
		return travelDateTo;
	}

	public String getTravelDateTo_ToString() {
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		return sdf.format(travelDateTo);
	}

	public void setTravelDateTo(Date travelDateTo) {
		this.travelDateTo = travelDateTo;
	}
	
	public void setBuyingDateTo(Date buyingDateTo) {
		this.buyingDateTo = buyingDateTo;
	}

	public Date getBuyingDateFrom() {
		return buyingDateFrom;
	}
	
	public String getBuyingDateFrom_ToString() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		return sdf.format(buyingDateFrom);
	}	

	public void setBuyingDateFrom(Date buyingDateFrom) {
		this.buyingDateFrom = buyingDateFrom;
	}

	public String getAirline() {
		return airline;
	}

	public void setAirline(String airline) {
		this.airline = airline;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


		
}
