package com.vunic.qaselenium.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.MongoOverPassengertypelistDTO;
import com.vunic.qaselenium.dto.MongoSearchsuggestionDTO;

public interface IMongoSearchsuggestionDAO 
{
	public List<MongoSearchsuggestionDTO> insert(List<MongoSearchsuggestionDTO> dtos) throws SQLException;
	public void deleteFull() throws  SQLException;
}
