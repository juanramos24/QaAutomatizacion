package com.vunic.qaselenium.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

import org.eclipse.jetty.websocket.common.SessionFactory;

@Entity
@Table(name = "Regla_Comercial")
public class ReglaComercialDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2145442721957269031L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Cdg_Identifier")
	private String cdgIdentifier;
	
	@Column(name="Destination_Black_List")
	private String destinationBlackList;
	
	@Column(name="Cabin_Type_Black_List")
	private String cabinTypeBlackList;
	
	@Column(name="Booking_Class_Black_List")
	private String bookingClassBlackList;
	
	@Column(name="Buying_Date_From")
	private Date buyingDateFrom;
	
	@Column(name="Buying_Date_To")
	private Date buyingDateTo;
	
	@Column(name="Blackout_Travel_From")
	private Date blackoutTravelFrom;
	
	@Column(name="Blackout_Travel_To")
	private Date blackoutTravelTo;
	
	@Column(name="Status")
	private String status;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCdgIdentifier() {
		return cdgIdentifier;
	}
	public void setCdgIdentifier(String cdgIdentifier) {
		this.cdgIdentifier = cdgIdentifier;
	}
	public String getDestinationBlackList() {
		return destinationBlackList;
	}
	public void setDestinationBlackList(String destinationBlackList) {
		this.destinationBlackList = destinationBlackList;
	}
	public String getCabinTypeBlackList() {
		return cabinTypeBlackList;
	}
	public void setCabinTypeBlackList(String cabinTypeBlackList) {
		this.cabinTypeBlackList = cabinTypeBlackList;
	}
	public String getBookingClassBlackList() {
		return bookingClassBlackList;
	}
	public void setBookingClassBlackList(String bookingClassBlackList) {
		this.bookingClassBlackList = bookingClassBlackList;
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
	public Date getBlackoutTravelFrom() {
		return blackoutTravelFrom;
	}
	public void setBlackoutTravelFrom(Date blackoutTravelFrom) {
		this.blackoutTravelFrom = blackoutTravelFrom;
	}
	public Date getBlackoutTravelTo() {
		return blackoutTravelTo;
	}
	public void setBlackoutTravelTo(Date blackoutTravelTo) {
		this.blackoutTravelTo = blackoutTravelTo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	public ReglaComercialDTO() {
	}
	
	public ReglaComercialDTO(int id, String cdgIdentifier, String destinationBlackList, String cabinTypeBlackList,
			String bookingClassBlackList, Date buyingDateFrom, Date buyingDateTo, Date blackoutTravelFrom,
			Date blackoutTravelTo, String status) {
		this.id = id;
		this.cdgIdentifier = cdgIdentifier;
		this.destinationBlackList = destinationBlackList;
		this.cabinTypeBlackList = cabinTypeBlackList;
		this.bookingClassBlackList = bookingClassBlackList;
		this.buyingDateFrom = buyingDateFrom;
		this.buyingDateTo = buyingDateTo;
		this.blackoutTravelFrom = blackoutTravelFrom;
		this.blackoutTravelTo = blackoutTravelTo;
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "ReglaComercialDTO [id=" + id + ", cdgIdentifier=" + cdgIdentifier + ", destinationBlackList="
				+ destinationBlackList + ", cabinTypeBlackList=" + cabinTypeBlackList + ", bookingClassBlackList="
				+ bookingClassBlackList + ", buyingDateFrom=" + buyingDateFrom + ", buyingDateTo=" + buyingDateTo
				+ ", blackoutTravelFrom=" + blackoutTravelFrom + ", blackoutTravelTo=" + blackoutTravelTo + ", status="
				+ status + "]";
	}
	
	
	
}
