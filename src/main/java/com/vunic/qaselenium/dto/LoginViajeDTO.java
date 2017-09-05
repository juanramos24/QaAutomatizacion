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
@Table(name = "Login_Viaje")
public class LoginViajeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3239356192346456203L;

	
	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Tipo_Resultado")
	private String tipoResultado;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Login_Posible")
	private LoginPosibleDTO loginPosible;
	
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
	public LoginPosibleDTO getLoginPosible() {
		return loginPosible;
	}
	public void setLoginPosible(LoginPosibleDTO loginPosible) {
		this.loginPosible = loginPosible;
	}
	public ViajePruebaFlujoDTO getViajePrueba() {
		return viajePrueba;
	}
	public void setViajePrueba(ViajePruebaFlujoDTO viajePrueba) {
		this.viajePrueba = viajePrueba;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public LoginViajeDTO() {
	}
	
	public LoginViajeDTO(int id, String tipoResultado, LoginPosibleDTO loginPosible, ViajePruebaFlujoDTO viajePrueba) {
		this.id = id;
		this.tipoResultado = tipoResultado;
		this.loginPosible = loginPosible;
		this.viajePrueba = viajePrueba;
	}
	
	@Override
	public String toString() {
		return "LoginViajeDTO [id=" + id + ", tipoResultado=" + tipoResultado + ", loginPosible=" + loginPosible
				+ ", viajePrueba=" + viajePrueba + "]";
	}
	
	
	
}
