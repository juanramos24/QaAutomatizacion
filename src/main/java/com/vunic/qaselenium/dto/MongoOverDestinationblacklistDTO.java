package com.vunic.qaselenium.dto;

import java.io.Serializable;

import com.vunic.core.base.BaseDTO;

public class MongoOverDestinationblacklistDTO extends BaseDTO  implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5513559651194805149L;
	
	private int id;
	private String destinationBlackList;
	
	public int getId() 
	{
		return id;
	}
	public void setId(int id) 
	{
		this.id = id;
	}
	public String getDestinationBlackList() 
	{
		return destinationBlackList;
	}
	public void setDestinationBlackList(String destinationBlackList) 
	{
		this.destinationBlackList = destinationBlackList;
	}

}
