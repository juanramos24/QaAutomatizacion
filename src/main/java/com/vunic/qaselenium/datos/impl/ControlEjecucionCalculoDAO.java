package com.vunic.qaselenium.datos.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;
import com.vunic.core.*;
import com.vunic.core.datos.*;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoDTO;
import com.vunic.qaselenium.dto.MongoOverCabintypeblacklistDTO;

public class ControlEjecucionCalculoDAO implements IControlEjecucionCalculoDAO
{
	///<summary>
	///Metodo que elimina un registro en la tabla 
	///</summary>
	///<param name="CapacidadDTO"></param>
	///<returns></returns>
	
	/********************************************************************************************************************/
	// CONSULTA DE QUERY
	
	public List<ControlEjecucionCalculoDTO> obtenerDatosPrueba() throws  SQLException
	{
		
		ControlEjecucionCalculoDTO cecDTO = null;
		List<ControlEjecucionCalculoDTO> lstCecDTO = new ArrayList<ControlEjecucionCalculoDTO>();
		
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		//Query se le agregan validaciones reglas de negocio: Fecha vuelo hasta se acota.
		
		String sql=" SELECT A.ID AS ID, ORIGIN, AIRLINE, BUYINGDATEFROM, BUYINGDATETO, TRAVELDATEFROM, TRAVELDATETO, OVERPERCENTAGE, PERCENTAGETOAPPLY,"
		+" B.DESTINATIONWHITELIST AS TIPO_DESTINO, C.NAME AS DESTINO, C.ID AS ID_UNICO_DESTINO,C.IATACITYCODE,"
		//+" CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY) AS FECHA_DESDE_1,"
		+ "DATE_ADD(CURDATE(), INTERVAL ((1 + ROUND((RAND()))*1)*37) DAY) AS FECHA_DESDE_1,"
		+" IF (DATE_ADD(CURDATE(), INTERVAL 30 DAY) >  TRAVELDATETO, TRAVELDATETO,DATE_ADD(CURDATE(), INTERVAL 90 DAY)) AS FECHA_HASTA_1,"
		+" DATE_ADD(CURDATE(), INTERVAL 30 DAY) AS FECHA_DESDE_2,"
		+" IF (DATE_ADD(CURDATE(), INTERVAL 30 DAY) >  TRAVELDATETO, TRAVELDATETO,DATE_ADD(CURDATE(), INTERVAL 97 DAY)) AS FECHA_HASTA_2,"
		+" DATE_ADD(CURDATE(), INTERVAL 30 DAY) AS FECHA_DESDE_3,"
		+" IF (DATE_ADD(CURDATE(), INTERVAL 30 DAY) >  TRAVELDATETO, TRAVELDATETO,DATE_ADD(CURDATE(), INTERVAL 104 DAY)) AS FECHA_HASTA_3, C.id as ID_IATACITYCODE,"
		+" (SELECT CURDATE() BETWEEN (BUYINGDATEFROM) AND (BUYINGDATETO)) AS OK"
		+" FROM MONGO_OVER AS A ,MONGO_OVER_DESTINATIONWHITELIST AS B ,MONGO_SEARCHSUGGESTION AS C"
		+" WHERE A.ID = B.ID AND ((B.DESTINATIONWHITELIST = C.COUNTRY) OR (B.DESTINATIONWHITELIST = C.IATACITYCODE)) ORDER BY ID  ASC";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		ResultSet rs = pst_1.executeQuery(sql);
		
		for(int i=0; rs.next (); i++) 
		{
			cecDTO = new ControlEjecucionCalculoDTO();

			cecDTO.setId(rs.getInt("ID"));
			cecDTO.setOrigin(rs.getString("ORIGIN"));
			cecDTO.setAirline(rs.getString("AIRLINE"));
			cecDTO.setBuyingdatefrom(rs.getString("BUYINGDATEFROM"));
			cecDTO.setBuyingdateto(rs.getString("BUYINGDATETO"));
			cecDTO.setTraveldatefrom(rs.getString("TRAVELDATEFROM"));
			cecDTO.setTraveldateto(rs.getString("TRAVELDATETO"));
			cecDTO.setOverpercentage(rs.getString("OVERPERCENTAGE"));
			cecDTO.setPercentagetoapply(rs.getString("PERCENTAGETOAPPLY"));
			cecDTO.setTipo_destino(rs.getString("TIPO_DESTINO"));
			cecDTO.setDestino(rs.getString("DESTINO"));
			cecDTO.setId_unico_destino(rs.getString("ID_UNICO_DESTINO"));
			cecDTO.setIatacitycode(rs.getString("iataCityCode"));
			cecDTO.setFecha_desde_1(rs.getString("FECHA_DESDE_1"));
			cecDTO.setFecha_hasta_1(rs.getString("FECHA_HASTA_1"));
			cecDTO.setFecha_desde_2(rs.getString("FECHA_DESDE_2"));
			cecDTO.setFecha_hasta_2(rs.getString("FECHA_HASTA_2"));
			cecDTO.setFecha_desde_3(rs.getString("FECHA_DESDE_3"));
			cecDTO.setFecha_hasta_3(rs.getString("FECHA_HASTA_3"));
			cecDTO.setId_iatacitycode(rs.getString("ID_IATACITYCODE"));
			
			lstCecDTO.add(cecDTO);
			
		}
		return lstCecDTO;
	}
	
	/********************************************************************************************************************/
	
	public List<ControlEjecucionCalculoDTO> insert(List<ControlEjecucionCalculoDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
		
		String sql_insert_calculo = "INSERT INTO CONTROL_EJECUCION_CALCULO (ID,origin,airline,buyingDateFrom,"
				+ " buyingDateTo,travelDateFrom,travelDateTo,overPercentage,percentageToApply,TIPO_DESTINO,"
				+ " DESTINO,ID_UNICO_DESTINO,IATACITYCODE,FECHA_DESDE_1,FECHA_HASTA_1,FECHA_DESDE_2,FECHA_HASTA_2,"
				+ " FECHA_DESDE_3,FECHA_HASTA_3,ID_IATACITYCODE) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql_insert_calculo);
		
		for (ControlEjecucionCalculoDTO dto : dtos)
		{
			
			pst_1.setInt(1,dto.getId());
			pst_1.setString(2,dto.getOrigin());
			pst_1.setString(3,dto.getAirline());
			pst_1.setString(4,dto.getBuyingdatefrom());
			pst_1.setString(5,dto.getBuyingdateto());
			pst_1.setString(6,dto.getTraveldatefrom());
			pst_1.setString(7,dto.getTraveldateto());
			pst_1.setString(8,dto.getOverpercentage());
			pst_1.setString(9,dto.getPercentagetoapply());
			pst_1.setString(10,dto.getTipo_destino());
			pst_1.setString(11,dto.getDestino());
			pst_1.setString(12,dto.getId_unico_destino());
			pst_1.setString(13,dto.getIatacitycode());
			pst_1.setString(14,dto.getFecha_desde_1());
			pst_1.setString(15,dto.getFecha_hasta_1());
			pst_1.setString(16,dto.getFecha_desde_2());
			pst_1.setString(17,dto.getFecha_hasta_2());
			pst_1.setString(18,dto.getFecha_desde_3());
			pst_1.setString(19,dto.getFecha_hasta_3());
			pst_1.setString(20,dto.getId_iatacitycode());

			int rstdo = servicio.ExecuteNonQuery(pst_1);

			if (rstdo == 0)
				dto.setId(0);
		}
		
		return dtos;
		
	}
	
	/********************************************************************************************************************/
	
	public void deleteFull() throws  SQLException
	{
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "DELETE FROM control_ejecucion_calculo";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}
	
	/********************************************************************************************************************/
	
	/********************************************************************************************************************/
	// CONSULTA DE QUERY
	public List<ControlEjecucionCalculoDTO> obtenerGrupoIDPromocion() throws  SQLException
	{
		
		ControlEjecucionCalculoDTO cecDTO = null;
		
		List<ControlEjecucionCalculoDTO> lstCecDTO = new ArrayList<ControlEjecucionCalculoDTO>();
		
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
			
		String sql ="SELECT ID FROM CONTROL_EJECUCION_CALCULO GROUP BY ID";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		ResultSet rs = pst_1.executeQuery(sql);
		
		for(int i=0; rs.next (); i++) 
		{
			cecDTO = new ControlEjecucionCalculoDTO();
			
			cecDTO.setId(rs.getInt("ID"));
				
			lstCecDTO.add(cecDTO);
			
		}
		return lstCecDTO;
	}
	/********************************************************************************************************************/
	// CONSULTA DE QUERY
	public List<ControlEjecucionCalculoDTO> obtenerPromocionSegmento(int id) throws SQLException
	{
		
		ControlEjecucionCalculoDTO cecDTO = null;
		
		List<ControlEjecucionCalculoDTO> lstCecDTO = new ArrayList<ControlEjecucionCalculoDTO>();
		
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
			
		String sql="SELECT * FROM CONTROL_EJECUCION_CALCULO AS CEC WHERE ID = '"+id+"' AND"
				+" CEC.ID_UNICO_DESTINO NOT IN (SELECT DESTINATIONBLACKLIST FROM MONGO_OVER_DESTINATIONBLACKLIST AS DB WHERE "
				+" CEC.ID = DB.ID) ORDER BY RAND() LIMIT 5";	
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		ResultSet rs = pst_1.executeQuery(sql);
		
		for(int i=0; rs.next (); i++) 
		{
			cecDTO = new ControlEjecucionCalculoDTO();

			cecDTO.setId(rs.getInt("ID"));
			cecDTO.setOrigin(rs.getString("ORIGIN"));
			cecDTO.setAirline(rs.getString("AIRLINE"));
			cecDTO.setBuyingdatefrom(rs.getString("BUYINGDATEFROM"));
			cecDTO.setBuyingdateto(rs.getString("BUYINGDATETO"));
			cecDTO.setTraveldatefrom(rs.getString("TRAVELDATEFROM"));
			cecDTO.setTraveldateto(rs.getString("TRAVELDATETO"));
			cecDTO.setOverpercentage(rs.getString("OVERPERCENTAGE"));
			cecDTO.setPercentagetoapply(rs.getString("PERCENTAGETOAPPLY"));
			cecDTO.setTipo_destino(rs.getString("TIPO_DESTINO"));
			cecDTO.setDestino(rs.getString("DESTINO"));
			cecDTO.setId_unico_destino(rs.getString("ID_UNICO_DESTINO"));
			cecDTO.setIatacitycode(rs.getString("iataCityCode"));
			cecDTO.setFecha_desde_1(rs.getString("FECHA_DESDE_1"));
			cecDTO.setFecha_hasta_1(rs.getString("FECHA_HASTA_1"));
			cecDTO.setFecha_desde_2(rs.getString("FECHA_DESDE_2"));
			cecDTO.setFecha_hasta_2(rs.getString("FECHA_HASTA_2"));
			cecDTO.setFecha_desde_3(rs.getString("FECHA_DESDE_3"));
			cecDTO.setFecha_hasta_3(rs.getString("FECHA_HASTA_3"));
			cecDTO.setId_iatacitycode(rs.getString("ID_IATACITYCODE"));

				
			lstCecDTO.add(cecDTO);
			
		}
		return lstCecDTO;
	}
	/********************************************************************************************************************/

}
