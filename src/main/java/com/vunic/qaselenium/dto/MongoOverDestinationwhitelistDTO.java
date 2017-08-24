package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class MongoOverDestinationwhitelistDTO extends BaseDTO implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1057752239506124863L;
	
	private int id;
	private String destinationWhiteList;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getDestinationWhiteList() 
	{
		return destinationWhiteList;
	}
	public void setDestinationWhiteList(String destinationWhiteList) 
	{
		this.destinationWhiteList = destinationWhiteList;
	}
	
}
