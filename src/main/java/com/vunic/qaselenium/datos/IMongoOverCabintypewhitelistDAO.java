package com.vunic.qaselenium.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.MongoOverCabintypewhitelistDTO;
import com.vunic.qaselenium.dto.MongoOverDestinationwhitelistDTO;

public interface IMongoOverCabintypewhitelistDAO 
{
	public List<MongoOverCabintypewhitelistDTO> insert(List<MongoOverCabintypewhitelistDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}