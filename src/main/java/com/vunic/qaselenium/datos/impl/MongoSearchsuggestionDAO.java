package com.vunic.qaselenium.datos.impl;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.core.*;
import com.vunic.core.datos.*;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.dto.MongoOverDTO;
import com.vunic.qaselenium.dto.MongoSearchsuggestionDTO;

public class MongoSearchsuggestionDAO implements IMongoSearchsuggestionDAO
{
	/**
	 * Metodo que ingresa un registro en la tabla Mongo_Search_Suggestion
	 * @param dtos, arreglo de objetos MongoOver que se van a insertar
	 * @return arreglos de objetos insertados
	 * @throws SQLException
	 */
	
	public List<MongoSearchsuggestionDTO> insert(List<MongoSearchsuggestionDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "INSERT INTO mongo_searchsuggestion (ID_Destino,id,name,ranking,country,countryName,"
				+ " city,state,stateName,iataCityCode,active) VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		
		for (MongoSearchsuggestionDTO dto : dtos)
		{
			
			pst_1.setInt(1,dto.getIdDestino());
			pst_1.setString(2,dto.getId());
			pst_1.setString(3,dto.getName());
			pst_1.setString(4,dto.getRanking());
			pst_1.setString(5,dto.getCountry());
			pst_1.setString(6,dto.getCountryName());
			pst_1.setString(7,dto.getCity());
			pst_1.setString(8,dto.getState());
			pst_1.setString(9,dto.getStateName());
			pst_1.setString(10,dto.getIataCityCode());
			pst_1.setString(11,dto.getActive());

			int rstdo = servicio.ExecuteNonQuery(pst_1);

			if (rstdo == 0)
				dto.setIdDestino(0);
			
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

		String sql = "DELETE FROM mongo_searchsuggestion";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}
	
}
