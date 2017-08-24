package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class ControlEjecucionCalculoDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -3145966731890325985L;
	
	private int id;
	private String origin;
	private String airline;
	private String buyingdatefrom;
	private String buyingdateto;
	private String traveldatefrom;
	private String traveldateto;
	private String overpercentage;
	private String percentagetoapply;
	private String tipo_destino;
	private String destino;
	private String id_unico_destino;
	private String iatacitycode;
	private String fecha_desde_1;
	private String fecha_hasta_1;
	private String fecha_desde_2;
	private String fecha_hasta_2;
	private String fecha_desde_3;
	private String fecha_hasta_3;
	private String id_iatacitycode;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getOrigin() {
		return origin;
	}
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	public String getAirline() {
		return airline;
	}
	public void setAirline(String airline) {
		this.airline = airline;
	}
	public String getBuyingdatefrom() {
		return buyingdatefrom;
	}
	public void setBuyingdatefrom(String buyingdatefrom) {
		this.buyingdatefrom = buyingdatefrom;
	}
	public String getBuyingdateto() {
		return buyingdateto;
	}
	public void setBuyingdateto(String buyingdateto) {
		this.buyingdateto = buyingdateto;
	}
	public String getTraveldatefrom() {
		return traveldatefrom;
	}
	public void setTraveldatefrom(String traveldatefrom) {
		this.traveldatefrom = traveldatefrom;
	}
	public String getTraveldateto() {
		return traveldateto;
	}
	public void setTraveldateto(String traveldateto) {
		this.traveldateto = traveldateto;
	}
	public String getOverpercentage() {
		return overpercentage;
	}
	public void setOverpercentage(String overpercentage) {
		this.overpercentage = overpercentage;
	}
	public String getPercentagetoapply() {
		return percentagetoapply;
	}
	public void setPercentagetoapply(String percentagetoapply) {
		this.percentagetoapply = percentagetoapply;
	}
	public String getTipo_destino() {
		return tipo_destino;
	}
	public void setTipo_destino(String tipo_destino) {
		this.tipo_destino = tipo_destino;
	}
	public String getDestino() {
		return destino;
	}
	public void setDestino(String destino) {
		this.destino = destino;
	}
	public String getId_unico_destino() {
		return id_unico_destino;
	}
	public void setId_unico_destino(String id_unico_destino) {
		this.id_unico_destino = id_unico_destino;
	}
	public String getIatacitycode() {
		return iatacitycode;
	}
	public void setIatacitycode(String iatacitycode) {
		this.iatacitycode = iatacitycode;
	}
	public String getFecha_desde_1() {
		return fecha_desde_1;
	}
	public void setFecha_desde_1(String fecha_desde_1) {
		this.fecha_desde_1 = fecha_desde_1;
	}
	public String getFecha_hasta_1() {
		return fecha_hasta_1;
	}
	public void setFecha_hasta_1(String fecha_hasta_1) {
		this.fecha_hasta_1 = fecha_hasta_1;
	}
	public String getFecha_desde_2() {
		return fecha_desde_2;
	}
	public void setFecha_desde_2(String fecha_desde_2) {
		this.fecha_desde_2 = fecha_desde_2;
	}
	public String getFecha_hasta_2() {
		return fecha_hasta_2;
	}
	public void setFecha_hasta_2(String fecha_hasta_2) {
		this.fecha_hasta_2 = fecha_hasta_2;
	}
	public String getFecha_desde_3() {
		return fecha_desde_3;
	}
	public void setFecha_desde_3(String fecha_desde_3) {
		this.fecha_desde_3 = fecha_desde_3;
	}
	public String getFecha_hasta_3() {
		return fecha_hasta_3;
	}
	public void setFecha_hasta_3(String fecha_hasta_3) {
		this.fecha_hasta_3 = fecha_hasta_3;
	}
	public String getId_iatacitycode() {
		return id_iatacitycode;
	}
	public void setId_iatacitycode(String id_iatacitycode) {
		this.id_iatacitycode = id_iatacitycode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
