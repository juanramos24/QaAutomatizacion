package com.vunic.qaselenium.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Resultado_CP")
public class ResultadoCPDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8582143028700938212L;

	
	@Id
	@Column(name="Id")
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Viaje_Prueba_Flujo")
	private ViajePruebaFlujoDTO viajePruebaFlujo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="Id_Caso_Prueba")
	private CasoPruebaDTO casoPrueba;
	
	@Column(name="Resultado_Obtenido")
	private String resultadoObtenido;
	
	@Column(name="Estado")
	private String estado;
	
	@Column(name="Imagen_Evidencia")
	private String imagenEvidencia;
	
	@Column(name="Fch_Ejecucion")
	private Date fchEjecucion;
	
	@Column(name="Nombre_Ciclo")
	private String nombreCiclo;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public ViajePruebaFlujoDTO getViajePruebaFlujo() {
		return viajePruebaFlujo;
	}
	public void setViajePruebaFlujo(ViajePruebaFlujoDTO viajePruebaFlujo) {
		this.viajePruebaFlujo = viajePruebaFlujo;
	}
	public CasoPruebaDTO getCasoPrueba() {
		return casoPrueba;
	}
	public void setCasoPrueba(CasoPruebaDTO casoPrueba) {
		this.casoPrueba = casoPrueba;
	}
	public String getResultadoObtenido() {
		return resultadoObtenido;
	}
	public void setResultadoObtenido(String resultadoObtenido) {
		this.resultadoObtenido = resultadoObtenido;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}
	public String getImagenEvidencia() {
		return imagenEvidencia;
	}
	public void setImagenEvidencia(String imagenEvidencia) {
		this.imagenEvidencia = imagenEvidencia;
	}
	public Date getFchEjecucion() {
		return fchEjecucion;
	}
	public void setFchEjecucion(Date fchEjecucion) {
		this.fchEjecucion = fchEjecucion;
	}
	public String getNombreCiclo() {
		return nombreCiclo;
	}
	public void setNombreCiclo(String nombreCiclo) {
		this.nombreCiclo = nombreCiclo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	

	
	public ResultadoCPDTO() {
	}
	
	public ResultadoCPDTO(int id, ViajePruebaFlujoDTO viajePruebaFlujo, CasoPruebaDTO casoPrueba,
			String resultadoObtenido, String estado, String imagenEvidencia, Date fchEjecucion, String nombreCiclo) {
		this.id = id;
		this.viajePruebaFlujo = viajePruebaFlujo;
		this.casoPrueba = casoPrueba;
		this.resultadoObtenido = resultadoObtenido;
		this.estado = estado;
		this.imagenEvidencia = imagenEvidencia;
		this.fchEjecucion = fchEjecucion;
		this.nombreCiclo = nombreCiclo;
	}
	
	@Override
	public String toString() {
		return "ResultadoCPDTO [id=" + id + ", viajePruebaFlujo=" + viajePruebaFlujo + ", casoPrueba=" + casoPrueba
				+ ", resultadoObtenido=" + resultadoObtenido + ", estado=" + estado + ", imagenEvidencia="
				+ imagenEvidencia + ", fchEjecucion=" + fchEjecucion + ", nombreCiclo=" + nombreCiclo + "]";
	}	
	
}
