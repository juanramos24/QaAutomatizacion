package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class MongoOverCabintypewhitelistDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7557665793190912398L;

	private int id;
	private String cabinTypeWhiteList;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getCabinTypeWhiteList() 
	{
		return cabinTypeWhiteList;
	}
	public void setCabinTypeWhiteList(String cabinTypeWhiteList) 
	{
		this.cabinTypeWhiteList = cabinTypeWhiteList;
	}
	
}
