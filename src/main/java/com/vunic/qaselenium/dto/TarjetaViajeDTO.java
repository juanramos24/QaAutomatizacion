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
@Table(name = "Tarjeta_Viaje")
public class TarjetaViajeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7956295416279047537L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Tipo_Resultado")
	private String tipoResultado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_TarjetaPosible")
	private TarjetaPosibleDTO tarjetaPosible;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Viaje_Prueba")
	private ViajePruebaFlujoDTO viajePrueba;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTipoResultado() {
		return tipoResultado;
	}
	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}
	public TarjetaPosibleDTO getTarjetaPosible() {
		return tarjetaPosible;
	}
	public void setTarjetaPosible(TarjetaPosibleDTO tarjetaPosible) {
		this.tarjetaPosible = tarjetaPosible;
	}
	public ViajePruebaFlujoDTO getViajePruebaFlujo() {
		return viajePrueba;
	}
	public void setViajePruebaFlujo(ViajePruebaFlujoDTO viajePruebaFlujo) {
		this.viajePrueba = viajePruebaFlujo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public TarjetaViajeDTO() {
	}
	
	public TarjetaViajeDTO(int id, String tipoResultado, TarjetaPosibleDTO tarjetaPosible,
			ViajePruebaFlujoDTO viajePruebaFlujo) {
		this.id = id;
		this.tipoResultado = tipoResultado;
		this.tarjetaPosible = tarjetaPosible;
		this.viajePrueba = viajePruebaFlujo;
	}
	
	@Override
	public String toString() {
		return "TarjetaViajeDTO [id=" + id + ", tipoResultado=" + tipoResultado + ", tarjetaPosible=" + tarjetaPosible
				+ ", viajePruebaFlujo=" + viajePrueba + "]";
	}
	
	
	
}
