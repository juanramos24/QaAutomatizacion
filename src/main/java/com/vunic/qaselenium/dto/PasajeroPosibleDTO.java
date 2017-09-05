package com.vunic.qaselenium.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Pasajero_Posible")
public class PasajeroPosibleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6646374955145105148L;

	
	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Sexo")
	private String sexo;
	
	@Column(name="Primer_Nombre")
	private String primerNombre;
	
	@Column(name="Segundo_Nombre")
	private String segundoNombre;
	
	@Column(name="Primer_Apellido")
	private String primerApellido;
	
	@Column(name="Segundo_Apellido")
	private String segundoApellido;
	
	@Column(name="Rut")
	private String rut;
	
	@Column(name="Passenger_Type")
	private String passengerType;
	
	@Column(name="Tipo_Doc")
	private String tipoDoc;
	
	@Column(name="Num_Doc")
	private String numDoc;
	
	@Column(name="Fch_Nacimiento")
	private Date fchNacimiento;
	
	@Column(name="Nacionalidad")
	private String nacionalidad;

	@Column(name="Fch_Vencimiento")
	private Date fchVencimiento;

	@Column(name="Nac_Emision_Doc")
	private String nacEmisionDoc;
	
	@Column(name="Email")
	private String email;
	
	@Column(name="Telefono")
	private String telefono;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getPrimerNombre() {
		return primerNombre;
	}
	public void setPrimerNombre(String primerNombre) {
		this.primerNombre = primerNombre;
	}
	public String getSegundoNombre() {
		return segundoNombre;
	}
	public void setSegundoNombre(String segundoNombre) {
		this.segundoNombre = segundoNombre;
	}
	public String getPrimerApellido() {
		return primerApellido;
	}
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	public String getSegundoApellido() {
		return segundoApellido;
	}
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getPassengerType() {
		return passengerType;
	}
	public void setPassengerType(String passengerType) {
		this.passengerType = passengerType;
	}
	public String getTipoDoc() {
		return tipoDoc;
	}
	public void setTipoDoc(String tipoDoc) {
		this.tipoDoc = tipoDoc;
	}
	public String getNumDoc() {
		return numDoc;
	}
	public void setNumDoc(String numDoc) {
		this.numDoc = numDoc;
	}
	public Date getFchNacimiento() {
		return fchNacimiento;
	}
	public void setFchNacimiento(Date fchNacimiento) {
		this.fchNacimiento = fchNacimiento;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}

	public Date getFchVencimiento() {
		return fchVencimiento;
	}
	public void setFchVencimiento(Date fchVencimiento) {
		this.fchVencimiento = fchVencimiento;
	}

	public String getNacEmisionDoc() {
		return nacEmisionDoc;
	}
	public void setNacEmisionDoc(String nacEmisionDoc) {
		this.nacEmisionDoc = nacEmisionDoc;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public PasajeroPosibleDTO() {
	}
	public PasajeroPosibleDTO(int id, String sexo, String primerNombre, String segundoNombre, String primerApellido,
			String segundoApellido, String rut, String passengerType, String tipoDoc, String numDoc, Date fchNacimiento,
			String nacionalidad, Date fchVencimiento, String nacEmisionDoc, String email, String telefono) {
		super();
		this.id = id;
		this.sexo = sexo;
		this.primerNombre = primerNombre;
		this.segundoNombre = segundoNombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.rut = rut;
		this.passengerType = passengerType;
		this.tipoDoc = tipoDoc;
		this.numDoc = numDoc;
		this.fchNacimiento = fchNacimiento;
		this.nacionalidad = nacionalidad;
		this.fchVencimiento = fchVencimiento;
		this.nacEmisionDoc = nacEmisionDoc;
		this.email = email;
		this.telefono = telefono;
	}
	
	@Override
	public String toString() {
		return "PasajeroPosibleDTO [id=" + id + ", sexo=" + sexo + ", primerNombre=" + primerNombre + ", segundoNombre="
				+ segundoNombre + ", primerApellido=" + primerApellido + ", segundoApellido=" + segundoApellido
				+ ", rut=" + rut + ", passengerType=" + passengerType + ", tipoDoc=" + tipoDoc + ", numDoc=" + numDoc
				+ ", fchNacimiento=" + fchNacimiento + ", nacionalidad=" + nacionalidad + ", fchVencimiento="
				+ fchVencimiento + ", nacEmisionDoc=" + nacEmisionDoc + ", email=" + email + ", telefono=" + telefono
				+ "]";
	}
	
	
	
	


}
