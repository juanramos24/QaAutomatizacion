package com.vunic.qaselenium.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.MongoOverCabintypeblacklistDTO;
import com.vunic.qaselenium.dto.MongoOverCabintypewhitelistDTO;

public interface IMongoOverCabintypeblacklistDAO 
{
	public List<MongoOverCabintypeblacklistDTO> insert(List<MongoOverCabintypeblacklistDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}