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
@Table(name = "Categoria")
public class CategoriaDTO implements Serializable{


	private static final long serialVersionUID = -5472347512529817883L;
	
	
	@Id
	@Column(name="Id")
	private int id;
	
	@Column(name="Descripcion")
	private String descripcion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
	
	public CategoriaDTO() {
	}

	public CategoriaDTO(int id, String descripcion) {
		super();
		this.id = id;
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "CategoriaDTO [id=" + id + ", descripcion=" + descripcion + "]";
	}
	
	
}