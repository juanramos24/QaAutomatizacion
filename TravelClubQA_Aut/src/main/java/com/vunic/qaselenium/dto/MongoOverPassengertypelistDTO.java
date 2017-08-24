package com.vunic.qaselenium.dto;

import java.io.*;

import com.vunic.core.base.BaseDTO;

public class MongoOverPassengertypelistDTO  extends BaseDTO  implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2551630459892833382L;
	
	private int id;
	private String passengerTypeList;
	
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPassengerTypeList() {
		return passengerTypeList;
	}
	public void setPassengerTypeList(String passengerTypeList) {
		this.passengerTypeList = passengerTypeList;
	}


}
