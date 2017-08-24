package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class ControlEjecucionCalculoSegmentadoDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5223004250979890767L;
	
	private int Id;
	private String Origin;
	private String Airline;
	private String Buyingdatefrom;
	private String Buyingdateto;
	private String Traveldatefrom;
	private String Traveldateto;
	private String Overpercentage;
	private String Percentagetoapply;
	private String Tipo_destino;
	private String Destino;
	private String Id_unico_destino;
	private String Iatacitycode;
	private String Fecha_desde_1;
	private String Fecha_hasta_1;
	private String Fecha_desde_2;
	private String Fecha_hasta_2;
	private String Fecha_desde_3;
	private String Fecha_hasta_3;
	private String Id_iatacitycode;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getOrigin() {
		return Origin;
	}
	public void setOrigin(String origin) {
		Origin = origin;
	}
	public String getAirline() {
		return Airline;
	}
	public void setAirline(String airline) {
		Airline = airline;
	}
	public String getBuyingdatefrom() {
		return Buyingdatefrom;
	}
	public void setBuyingdatefrom(String buyingdatefrom) {
		Buyingdatefrom = buyingdatefrom;
	}
	public String getBuyingdateto() {
		return Buyingdateto;
	}
	public void setBuyingdateto(String buyingdateto) {
		Buyingdateto = buyingdateto;
	}
	public String getTraveldatefrom() {
		return Traveldatefrom;
	}
	public void setTraveldatefrom(String traveldatefrom) {
		Traveldatefrom = traveldatefrom;
	}
	public String getTraveldateto() {
		return Traveldateto;
	}
	public void setTraveldateto(String traveldateto) {
		Traveldateto = traveldateto;
	}
	public String getOverpercentage() {
		return Overpercentage;
	}
	public void setOverpercentage(String overpercentage) {
		Overpercentage = overpercentage;
	}
	public String getPercentagetoapply() {
		return Percentagetoapply;
	}
	public void setPercentagetoapply(String percentagetoapply) {
		Percentagetoapply = percentagetoapply;
	}
	public String getTipo_destino() {
		return Tipo_destino;
	}
	public void setTipo_destino(String tipo_destino) {
		Tipo_destino = tipo_destino;
	}
	public String getDestino() {
		return Destino;
	}
	public void setDestino(String destino) {
		Destino = destino;
	}
	public String getId_unico_destino() {
		return Id_unico_destino;
	}
	public void setId_unico_destino(String id_unico_destino) {
		Id_unico_destino = id_unico_destino;
	}
	public String getIatacitycode() {
		return Iatacitycode;
	}
	public void setIatacitycode(String iatacitycode) {
		Iatacitycode = iatacitycode;
	}
	public String getFecha_desde_1() {
		return Fecha_desde_1;
	}
	public void setFecha_desde_1(String fecha_desde_1) {
		Fecha_desde_1 = fecha_desde_1;
	}
	public String getFecha_hasta_1() {
		return Fecha_hasta_1;
	}
	public void setFecha_hasta_1(String fecha_hasta_1) {
		Fecha_hasta_1 = fecha_hasta_1;
	}
	public String getFecha_desde_2() {
		return Fecha_desde_2;
	}
	public void setFecha_desde_2(String fecha_desde_2) {
		Fecha_desde_2 = fecha_desde_2;
	}
	public String getFecha_hasta_2() {
		return Fecha_hasta_2;
	}
	public void setFecha_hasta_2(String fecha_hasta_2) {
		Fecha_hasta_2 = fecha_hasta_2;
	}
	public String getFecha_desde_3() {
		return Fecha_desde_3;
	}
	public void setFecha_desde_3(String fecha_desde_3) {
		Fecha_desde_3 = fecha_desde_3;
	}
	public String getFecha_hasta_3() {
		return Fecha_hasta_3;
	}
	public void setFecha_hasta_3(String fecha_hasta_3) {
		Fecha_hasta_3 = fecha_hasta_3;
	}
	public String getId_iatacitycode() {
		return Id_iatacitycode;
	}
	public void setId_iatacitycode(String id_iatacitycode) {
		Id_iatacitycode = id_iatacitycode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
