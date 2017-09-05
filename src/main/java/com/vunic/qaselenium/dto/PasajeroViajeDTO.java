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
@Table(name = "Pasajero_Viaje")
public class PasajeroViajeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2977242732010272992L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Passenger_Type")
	private String passengerType;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Viaje_Prueba")
	private ViajePruebaFlujoDTO viajePrueba;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Pasajero_Posible")
	private PasajeroPosibleDTO pasajeroPosible;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public ViajePruebaFlujoDTO getViajePrueba() {
		return viajePrueba;
	}
	public void setViajePrueba(ViajePruebaFlujoDTO viajePrueba) {
		this.viajePrueba = viajePrueba;
	}
	public PasajeroPosibleDTO getPasajeroPosible() {
		return pasajeroPosible;
	}
	public void setPasajeroPosible(PasajeroPosibleDTO pasajeroPosible) {
		this.pasajeroPosible = pasajeroPosible;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public PasajeroViajeDTO() {
	}
	
	public PasajeroViajeDTO(int id, String passengerType, ViajePruebaFlujoDTO viajePrueba,
			PasajeroPosibleDTO pasajeroPosible) {
		this.id = id;
		this.passengerType = passengerType;
		this.viajePrueba = viajePrueba;
		this.pasajeroPosible = pasajeroPosible;
	}
	
	@Override
	public String toString() {
		return "PasajeroViajeDTO [id=" + id + ", passengerType=" + passengerType + ", pasajeroPosible="
				+ pasajeroPosible + "]";
	}
	
	
	
	
	
}
