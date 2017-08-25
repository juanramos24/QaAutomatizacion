package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class MongoOverCabintypeblacklistDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8657773070390562164L;

	private int id;
	private String cabinTypeBlackList;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getCabinTypeBlackList() 
	{
		return cabinTypeBlackList;
	}
	public void setCabinTypeBlackList(String cabinTypeBlackList) 
	{
		this.cabinTypeBlackList = cabinTypeBlackList;
	}
	
}
