package com.vunic.qaselenium.datos;

import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.dto.MongoOverDestinationblacklistDTO;
import com.vunic.qaselenium.dto.MongoOverDestinationwhitelistDTO;

public interface IMongoOverDestinationblacklistDAO 
{
	public List<MongoOverDestinationblacklistDTO> insert(List<MongoOverDestinationblacklistDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}
