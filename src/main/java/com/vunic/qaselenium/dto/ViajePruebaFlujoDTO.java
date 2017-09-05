package com.vunic.qaselenium.dto;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Viaje_Prueba_Flujo")
public class ViajePruebaFlujoDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 568644730311400342L;

	
	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Origin")
	private String origin;
	
	@Column(name="Cdg_Destination")
	private String cdgDestination;
	
	@Column(name="Name_Destination")
	private String nameDestination;
	
	@Column(name="Airline")
	private String airline;
	
	@Column(name="Cabin_Type")
	private String cabinType;
	
	@Column(name="Booking_Type")
	private String bookingType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Periodo_Viaje")
	private PeriodoViajePosibleDTO periodoViaje;
	
	@Column(name="Fare_Basis")
	private String fareBasis;
	
	@Column(name="Over_Percentage")
	private String overPercentage;
	
	@Column(name="Percentage_To_Apply")
	private String percentageToApply;
	
	@Column(name="Commission")
	private double commission;
	
	@Column(name="Kp_Over")
	private boolean kpOver;
	
	@Column(name="Num_ADT")
	private short numADT;
	
	@Column(name="Num_CHD")
	private short numCHD;
	
	@Column(name="Num_INF")
	private short numINF;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getCdgDestination() {
		return cdgDestination;
	}
	public void setCdgDestination(String cdgDestination) {
		this.cdgDestination = cdgDestination;
	}
	public String getNameDestination() {
		return nameDestination;
	}
	public void setNameDestination(String nameDestination) {
		this.nameDestination = nameDestination;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getCabinType() {
		return cabinType;
	}
	public void setCabinType(String cabinType) {
		this.cabinType = cabinType;
	}
	public String getBookingType() {
		return bookingType;
	}
	public void setBookingType(String bookingType) {
		this.bookingType = bookingType;
	}
	public PeriodoViajePosibleDTO getPeriodoViaje() {
		return periodoViaje;
	}
	public void setPeriodoViaje(PeriodoViajePosibleDTO periodoViaje) {
		this.periodoViaje = periodoViaje;
	}
	public String getFareBasis() {
		return fareBasis;
	}
	public void setFareBasis(String fareBasis) {
		this.fareBasis = fareBasis;
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
	public double getCommission() {
		return commission;
	}
	public void setCommission(double commission) {
		this.commission = commission;
	}
	public boolean isKpOver() {
		return kpOver;
	}
	public void setKpOver(boolean kpOver) {
		this.kpOver = kpOver;
	}
	public short getNumADT() {
		return numADT;
	}
	public void setNumADT(short numADT) {
		this.numADT = numADT;
	}
	public short getNumCHD() {
		return numCHD;
	}
	public void setNumCHD(short numCHD) {
		this.numCHD = numCHD;
	}
	public short getNumINF() {
		return numINF;
	}
	public void setNumINF(short numINF) {
		this.numINF = numINF;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public ViajePruebaFlujoDTO() {
	}
	
	public ViajePruebaFlujoDTO(int id, String origin, String cdgDestination, String nameDestination, String airline,
			String cabinType, String bookingType, PeriodoViajePosibleDTO periodoViaje, String fareBasis,
			String overPercentage, String percentageToApply, double commission, boolean kpOver, short numADT,
			short numCHD, short numINF) {
		this.id = id;
		this.origin = origin;
		this.cdgDestination = cdgDestination;
		this.nameDestination = nameDestination;
		this.airline = airline;
		this.cabinType = cabinType;
		this.bookingType = bookingType;
		this.periodoViaje = periodoViaje;
		this.fareBasis = fareBasis;
		this.overPercentage = overPercentage;
		this.percentageToApply = percentageToApply;
		this.commission = commission;
		this.kpOver = kpOver;
		this.numADT = numADT;
		this.numCHD = numCHD;
		this.numINF = numINF;
	}
	
	
	@Override
	public String toString() {
		return "ViajePruebaFlujoDTO [id=" + id + ", origin=" + origin + ", cdgDestination=" + cdgDestination
				+ ", nameDestination=" + nameDestination + ", airline=" + airline + ", cabinType=" + cabinType
				+ ", bookingType=" + bookingType + ", periodoViaje=" + periodoViaje + ", fareBasis=" + fareBasis
				+ ", overPercentage=" + overPercentage + ", percentageToApply=" + percentageToApply + ", commission="
				+ commission + ", kpOver=" + kpOver + ", numADT=" + numADT + ", numCHD=" + numCHD + ", numINF=" + numINF
				+ "]";
	}
	
	
	
	
}
