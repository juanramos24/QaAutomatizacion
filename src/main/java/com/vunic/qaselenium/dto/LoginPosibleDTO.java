package com.vunic.qaselenium.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Login_Posible")
public class LoginPosibleDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7839832261788135299L;

	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Rut")
	private String rut;
	
	@Column(name="Password")
	private String password;
	
	@Column(name="Dato_OK")
	private boolean datoOK;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRut() {
		return rut;
	}
	public void setRut(String rut) {
		this.rut = rut;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	
	public LoginPosibleDTO() {
	}
	
	public LoginPosibleDTO(int id, String rut, String password, boolean datoOK) {
		this.id = id;
		this.rut = rut;
		this.password = password;
		this.datoOK = datoOK;
	}
	
	@Override
	public String toString() {
		return "LoginPosibleDTO [id=" + id + ", rut=" + rut + ", password=" + password + ", datoOK=" + datoOK + "]";
	}
	
	
	
}
