package com.vunic.qaselenium.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Periodo_Viaje_Posible")
public class PeriodoViajePosibleDTO implements Serializable{



	/**
	 * 
	 */
	private static final long serialVersionUID = 34468908300965817L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Travel_Date_From")
	private Date travelDateFrom;
	
	@Column(name="Travel_Date_To")
	private Date travelDateTo;


	public int getId() 
	{
		return id;
	}

	public void setId(int id) 
	{
		this.id = id;
	}

	public Date getTravelDateFrom() 
	{
		return travelDateFrom;
	}

	public void setTravelDateFrom(Date travelDateFrom) 
	{
		this.travelDateFrom = travelDateFrom;
	}
	public Date getTravelDateTo() 
	{
		return travelDateTo;
	}
	
	public void setTravelDateTo(Date travelDateTo) 
	{
		this.travelDateTo = travelDateTo;
	}
	

	
	
	public PeriodoViajePosibleDTO() {
	}

	public PeriodoViajePosibleDTO(int id, Date travelDateFrom, Date travelDateTo) 
	{
		this.id = id;
		this.travelDateFrom = travelDateFrom;
		this.travelDateTo = travelDateTo;
	}

	@Override
	public String toString() 
	{
		return "PeriodoViajePosible [id=" + id + ", travelDateFrom=" + travelDateFrom + ", travelDateTo=" + travelDateTo
				+ "]";
	}



}
