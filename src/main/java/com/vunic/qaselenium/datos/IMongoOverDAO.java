package com.vunic.qaselenium.datos;

import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.dto.MongoOverDTO;

public interface IMongoOverDAO 
{
	public List<MongoOverDTO> insert(List<MongoOverDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}
