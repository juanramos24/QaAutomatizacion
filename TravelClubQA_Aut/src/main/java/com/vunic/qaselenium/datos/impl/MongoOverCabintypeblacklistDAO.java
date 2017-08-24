package com.vunic.qaselenium.datos.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.core.*;
import com.vunic.core.datos.*;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.dto.MongoOverCabintypeblacklistDTO;
import com.vunic.qaselenium.dto.MongoOverCabintypewhitelistDTO;

public class MongoOverCabintypeblacklistDAO implements IMongoOverCabintypeblacklistDAO
{
	
	/**
	* Metodo que ingresa un registro en la tabla Mongo_Over
	* @param dtos, arreglo de objetos MongoOver que se van a insertar
	* @return arreglos de objetos insertados
	* @throws SQLException
	*/
	
	public List<MongoOverCabintypeblacklistDTO> insert(List<MongoOverCabintypeblacklistDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
		
		String sql = "INSERT INTO mongo_over_cabintypeblacklist (ID,cabinTypeBlackList) VALUES (?,?)";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		
		for (MongoOverCabintypeblacklistDTO dto : dtos)
		{
			
			pst_1.setInt(1,dto.getId());
			pst_1.setString(2,dto.getCabinTypeBlackList());

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

		String sql = "DELETE FROM mongo_over_cabintypeblacklist";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}	
	
}
