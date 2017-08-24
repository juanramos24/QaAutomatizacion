package com.vunic.qaselenium.datos;

import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.dto.MongoOverDTO;
import com.vunic.qaselenium.dto.MongoOverDestinationwhitelistDTO;

public interface IMongoOverDestinationwhitelistDAO 
{
	public List<MongoOverDestinationwhitelistDTO> insert(List<MongoOverDestinationwhitelistDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}
