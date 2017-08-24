package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class SuggestionDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093442252780630781L;
	
	private int idDestino;
	private String id;
	private String name;
	private String ranking;
	private String country;
	private String countryName;
	private String city;
	private String state;
	
	public int getIdDestino() {
		return idDestino;
	}
	public void setIdDestino(int idDestino) {
		this.idDestino = idDestino;
	}
	private String stateName;
	private String iataCityCode;
	private String active;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRanking() {
		return ranking;
	}
	public void setRanking(String ranking) {
		this.ranking = ranking;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCountryName() {
		return countryName;
	}
	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getStateName() {
		return stateName;
	}
	public void setStateName(String stateName) {
		this.stateName = stateName;
	}
	public String getIataCityCode() {
		return iataCityCode;
	}
	public void setIataCityCode(String iataCityCode) {
		this.iataCityCode = iataCityCode;
	}
	public String getActive() {
		return active;
	}
	public void setActive(String active) {
		this.active = active;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
