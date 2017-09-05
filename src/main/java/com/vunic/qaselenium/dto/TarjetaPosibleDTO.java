package com.vunic.qaselenium.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Tarjeta_Posible")
public class TarjetaPosibleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8217524123696532946L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Cdg_Bin")
	private String cdgBin;
	
	@Column(name="Cant_DP")
	private String cantDP;
	
	@Column(name="Mes_Expiracion")
	private String mesExpiracion;
	
	@Column(name="Anio_Expiracion")
	private String anioExpiracion;
	
	@Column(name="Cdg_Seguridad")
	private String cdgSeguridad;
	
	@Column(name="Num_Cuotas")
	private String numCuotas;
	
	@Column(name="Dato_OK")
	private boolean datoOK;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBines() {
		return cdgBin;
	}
	public void setBines(String bines) {
		this.cdgBin = bines;
	}
	public String getCantDP() {
		return cantDP;
	}
	public void setCantDP(String cantDP) {
		this.cantDP = cantDP;
	}
	public String getMesExpiracion() {
		return mesExpiracion;
	}
	public void setMesExpiracion(String mesExpiracion) {
		this.mesExpiracion = mesExpiracion;
	}
	public String getAnioExpiracion() {
		return anioExpiracion;
	}
	public void setAnioExpiracion(String anioExpiracion) {
		this.anioExpiracion = anioExpiracion;
	}
	public String getCdgSeguridad() {
		return cdgSeguridad;
	}
	public void setCdgSeguridad(String cdgSeguridad) {
		this.cdgSeguridad = cdgSeguridad;
	}
	public String getNumCuotas() {
		return numCuotas;
	}
	public void setNumCuotas(String numCuotas) {
		this.numCuotas = numCuotas;
	}
	public boolean isDatoOK() {
		return datoOK;
	}
	public void setDatoOK(boolean datoOK) {
		this.datoOK = datoOK;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public TarjetaPosibleDTO() {
	}
	
	
	public TarjetaPosibleDTO(int id, String bines, String cantDP, String mesExpiracion, String anioExpiracion,
			String cdgSeguridad, String numCuotas, boolean datoOK) {
		this.id = id;
		this.cdgBin = bines;
		this.cantDP = cantDP;
		this.mesExpiracion = mesExpiracion;
		this.anioExpiracion = anioExpiracion;
		this.cdgSeguridad = cdgSeguridad;
		this.numCuotas = numCuotas;
		this.datoOK = datoOK;
	}
	
	@Override
	public String toString() {
		return "TarjetaPosibleDTO [id=" + id + ", bines=" + cdgBin + ", cantDP=" + cantDP + ", mesExpiracion="
				+ mesExpiracion + ", anioExpiracion=" + anioExpiracion + ", cdgSeguridad=" + cdgSeguridad
				+ ", numCuotas=" + numCuotas + ", datoOK=" + datoOK + "]";
	}
	
	
	
}
