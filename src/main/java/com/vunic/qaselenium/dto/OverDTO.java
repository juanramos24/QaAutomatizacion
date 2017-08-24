package com.vunic.qaselenium.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import com.vunic.core.base.BaseDTO;

public class OverDTO extends BaseDTO  implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8489335284170677119L;
	
	private int id;
	private String origin;
	private String airline;
	private Date buyingDateFrom;
	private Date buyingDateTo;
	private Date travelDateFrom;
	private Date travelDateTo;
	private String overPercentage;
	private String percentageToApply;
	private ArrayList<String> destinationWhiteList;
	private ArrayList<String> destinationBlackList;
	private ArrayList<String> cabinTypeWhiteList;
	private ArrayList<String> cabinTypeBlackList;
	private ArrayList<String> passengerTypeList;

	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public Date getBuyingDateFrom() {
		return buyingDateFrom;
	}
	public void setBuyingDateFrom(Date buyingDateFrom) {
		this.buyingDateFrom = buyingDateFrom;
	}
	public Date getBuyingDateTo() {
		return buyingDateTo;
	}
	public void setBuyingDateTo(Date buyingDateTo) {
		this.buyingDateTo = buyingDateTo;
	}
	public Date getTravelDateFrom() {
		return travelDateFrom;
	}
	public void setTravelDateFrom(Date travelDateFrom) {
		this.travelDateFrom = travelDateFrom;
	}
	public Date getTravelDateTo() {
		return travelDateTo;
	}
	public void setTravelDateTo(Date travelDateTo) {
		this.travelDateTo = travelDateTo;
	}
	public String getOverPercentage() {
		return overPercentage;
	}
	public void setOverPercentage(String overPercentage) {
		this.overPercentage = overPercentage;
	}
	public String getPercentageToApply() {
		return percentageToApply;
	}
	public void setPercentageToApply(String percentageToApply) {
		this.percentageToApply = percentageToApply;
	}
	public ArrayList<String> getDestinationWhiteList() {
		return destinationWhiteList;
	}
	public void setDestinationWhiteList(ArrayList<String> destinationWhiteList) {
		this.destinationWhiteList = destinationWhiteList;
	}
	public ArrayList<String> getDestinationBlackList() {
		return destinationBlackList;
	}
	public void setDestinationBlackList(ArrayList<String> destinationBlackList) {
		this.destinationBlackList = destinationBlackList;
	}
	public ArrayList<String> getCabinTypeWhiteList() {
		return cabinTypeWhiteList;
	}
	public void setCabinTypeWhiteList(ArrayList<String> cabinTypeWhiteList) {
		this.cabinTypeWhiteList = cabinTypeWhiteList;
	}
	public ArrayList<String> getCabinTypeBlackList() {
		return cabinTypeBlackList;
	}
	public void setCabinTypeBlackList(ArrayList<String> cabinTypeBlackList) {
		this.cabinTypeBlackList = cabinTypeBlackList;
	}
	public ArrayList<String> getPassengerTypeList() {
		return passengerTypeList;
	}
	public void setPassengerTypeList(ArrayList<String> passengerTypeList) {
		this.passengerTypeList = passengerTypeList;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}



	
	



}
