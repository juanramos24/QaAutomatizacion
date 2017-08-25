package com.vunic.qaselenium.datos.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.core.*;
import com.vunic.core.datos.*;
import com.vunic.core.FactorySvc;
import com.vunic.core.datos.DatosService;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.dto.MongoOverDTO;
import com.vunic.qaselenium.dto.MongoOverDestinationwhitelistDTO;

public class MongoOverDestinationwhitelistDAO implements IMongoOverDestinationwhitelistDAO
{
	
	/**
	* Metodo que ingresa un registro en la tabla Mongo_Over
	* @param dtos, arreglo de objetos MongoOver que se van a insertar
	* @return arreglos de objetos insertados
	* @throws SQLException
	*/
	
	public List<MongoOverDestinationwhitelistDTO> insert(List<MongoOverDestinationwhitelistDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
		String sql = "INSERT INTO mongo_over_destinationwhitelist (ID,destinationWhiteList) VALUES (?,?)";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		
		for (MongoOverDestinationwhitelistDTO dto : dtos)
		{
			
			pst_1.setInt(1,dto.getId());
			pst_1.setString(2,dto.getDestinationWhiteList());

			int rstdo = servicio.ExecuteNonQuery(pst_1);

			if (rstdo == 0)
				dto.setId(0);
		}
		
		return dtos;
		
	}
	
	
	///<summary>
	///Metodo que elimina un registro en la tabla 
	///</summary>
	///<param name="CapacidadDTO"></param>
	///<returns></returns>
	
	public void deleteFull() throws  SQLException
	{
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "DELETE FROM mongo_over_destinationwhitelist ";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}
	
}
