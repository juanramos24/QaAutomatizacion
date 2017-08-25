package com.vunic.qaselenium.datos.impl;

import java.sql.*;
import java.util.List;

import com.vunic.core.*;
import com.vunic.core.datos.*;
import com.vunic.qaselenium.dto.*;
import com.vunic.qaselenium.datos.*;

public class MongoOverDAO implements IMongoOverDAO {
	

	/**
	 * Metodo que ingresa un registro en la tabla Mongo_Over
	 * @param dtos, arreglo de objetos MongoOver que se van a insertar
	 * @return arreglos de objetos insertados
	 * @throws SQLException
	 */
	
	public List<MongoOverDTO> insert(List<MongoOverDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "INSERT INTO Mongo_Over (ID,origin,airline,buyingDateFrom,buyingDateTo,travelDateFrom,travelDateTo,overPercentage,percentageToApply) "
				     + "VALUES (?,?,?,?,?,?,?,?,?)";

		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		
		for (MongoOverDTO dto : dtos)
		{
			
			pst_1.setInt(1,dto.getId());
			pst_1.setString(2,dto.getOrigin());
			pst_1.setString(3,dto.getAirline());
			pst_1.setString(4,dto.getBuyingDateFrom_ToString());
			pst_1.setString(5,dto.getBuyingDateTo_ToString());
			pst_1.setString(6,dto.getTravelDateTo_ToString());
			pst_1.setString(7,dto.getTravelDateTo_ToString());
			pst_1.setString(8,dto.getOverPercentage());
			pst_1.setString(9,dto.getPercentageToApply());			

			int rstdo = servicio.ExecuteNonQuery(pst_1);

			if (rstdo == 0)
				dto.setId(0);
			
		}
		return dtos;

	}
	
	///<summary>
	///Metodo que elimina un registro en la tabla Capacidad
	///</summary>
	///<param name="CapacidadDTO"></param>
	///<returns></returns>
	
	public void deleteFull() throws  SQLException
	{
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "DELETE FROM Mongo_Over ";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}

}
