package com.vunic.qaselenium.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Caso_Prueba")
public class CasoPruebaDTO implements Serializable{

	/**
	 * kkkk
	 */
	private static final long serialVersionUID = -5472347512529817883L;
	
	
	@Id
	@Column(name="Id")
	private int id;
	
	
	@Column(name="Categoria")
	private String categoria;
	
	@Column(name="Clasificacion")
	private String clasificacion;
	
	@Column(name="Resultado_Esperado")
	private String resultadoEsperado;
	
	@Column(name="Tipo_Resultado")
	private String tipoResultado;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public String getResultadoEsperado() {
		return resultadoEsperado;
	}
	public void setResultadoEsperado(String resultadoEsperado) {
		this.resultadoEsperado = resultadoEsperado;
	}
	public String getTipoResultado() {
		return tipoResultado;
	}
	public void setTipoResultado(String tipoResultado) {
		this.tipoResultado = tipoResultado;
	}

	
	public CasoPruebaDTO() {
	}
	
	public CasoPruebaDTO(int id, String categoria, String clasificacion, String resultadoEsperado,
			String tipoResultado) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.clasificacion = clasificacion;
		this.resultadoEsperado = resultadoEsperado;
		this.tipoResultado = tipoResultado;
	}
	
	
	@Override
	public String toString() {
		return "CasoPruebaDTO [id=" + id + ", categoria=" + categoria + ", clasificacion=" + clasificacion
				+ ", resultadoEsperado=" + resultadoEsperado + ", tipoResultado=" + tipoResultado + "]";
	}
	
	
	
	
	
}
