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
import com.vunic.qaselenium.dto.ControlEjecucionCalculoSegmentadoDTO;

public class ControlEjecucionCalculoSegmentadoDAO implements IControlEjecucionCalculoSegmentadoDAO
{
	///<summary>
	///Metodo que elimina un registro en la tabla 
	///</summary>
	///<param name="CapacidadDTO"></param>
	///<returns></returns>
	
	public void deleteFull() throws  SQLException
	{
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");

		String sql = "DELETE FROM control_ejecucion_calculo_segmentado";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);

		servicio.ExecuteNonQuery(pst_1);
	}
	
	/********************************************************************************************************************/
	
	public List<ControlEjecucionCalculoDTO> insert(List<ControlEjecucionCalculoDTO> dtos) throws SQLException
	{	
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
		
		String sql_insert_calculo_segmentado = "INSERT INTO control_ejecucion_calculo_segmentado (ID_PIVOTE,ID,origin,airline,"
				+ " buyingDateFrom,buyingDateTo,travelDateFrom,travelDateTo,overPercentage,percentageToApply,"
				+ " TIPO_DESTINO,DESTINO,ID_UNICO_DESTINO,IATACITYCODE,FECHA_DESDE_1,FECHA_HASTA_1,"
				+ " FECHA_DESDE_2,FECHA_HASTA_2,FECHA_DESDE_3,FECHA_HASTA_3,ID_IATACITYCODE) "
				+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql_insert_calculo_segmentado);
		
		for (ControlEjecucionCalculoDTO dto : dtos)
		{
			pst_1.setInt(1,1);
			pst_1.setInt(2,dto.getId());
			pst_1.setString(3,dto.getOrigin());
			pst_1.setString(4,dto.getAirline());
			pst_1.setString(5,dto.getBuyingdatefrom());
			pst_1.setString(6,dto.getBuyingdateto());
			pst_1.setString(7,dto.getTraveldatefrom());
			pst_1.setString(8,dto.getTraveldateto());
			pst_1.setString(9,dto.getOverpercentage());
			pst_1.setString(10,dto.getPercentagetoapply());
			pst_1.setString(11,dto.getTipo_destino());
			pst_1.setString(12,dto.getDestino());
			pst_1.setString(13,dto.getId_unico_destino());
			pst_1.setString(14,dto.getIatacitycode());
			pst_1.setString(15,dto.getFecha_desde_1());
			pst_1.setString(16,dto.getFecha_hasta_1());
			pst_1.setString(17,dto.getFecha_desde_2());
			pst_1.setString(18,dto.getFecha_hasta_2());
			pst_1.setString(19,dto.getFecha_desde_3());
			pst_1.setString(20,dto.getFecha_hasta_3());
			pst_1.setString(21,dto.getId_iatacitycode());

			int rstdo = servicio.ExecuteNonQuery(pst_1);

			if (rstdo == 0)
				dto.setId(0);
		}
		
		return dtos;
		
	}
	/********************************************************************************************************************/
	// CONSULTA DE QUERY
	public List<ControlEjecucionCalculoSegmentadoDTO> obtenerDatosEjecutar() throws SQLException
	{
		
		ControlEjecucionCalculoSegmentadoDTO cecsDTO = null;
		
		List<ControlEjecucionCalculoSegmentadoDTO> lstCecsDTO = new ArrayList<ControlEjecucionCalculoSegmentadoDTO>();
		
		DatosService servicio= FactorySvc.ServicioDatos("TRAVEL_MYSQL");
			
		 
	    String sql="SELECT B.RUT,B.PASS,A.*,C.* FROM CONTROL_EJECUCION_CALCULO_SEGMENTADO AS A,"
		+" DATOS_LOGIN AS B,DATOS_PASAJEROS AS C WHERE A.ID_PIVOTE = B.ID AND A.ID_PIVOTE = C.ID_PASAJERO";
		
		PreparedStatement pst_1 = servicio.getPreparedStatement(sql);
		ResultSet rs = pst_1.executeQuery(sql);
		
		for(int i=0; rs.next (); i++)
		{
			cecsDTO = new ControlEjecucionCalculoSegmentadoDTO();

			cecsDTO.setId(rs.getInt("ID"));
			cecsDTO.setOrigin(rs.getString("ORIGIN"));
			cecsDTO.setAirline(rs.getString("AIRLINE"));
			cecsDTO.setBuyingdatefrom(rs.getString("BUYINGDATEFROM"));
			cecsDTO.setBuyingdateto(rs.getString("BUYINGDATETO"));
			cecsDTO.setTraveldatefrom(rs.getString("TRAVELDATEFROM"));
			cecsDTO.setTraveldateto(rs.getString("TRAVELDATETO"));
			cecsDTO.setOverpercentage(rs.getString("OVERPERCENTAGE"));
			cecsDTO.setPercentagetoapply(rs.getString("PERCENTAGETOAPPLY"));
			cecsDTO.setTipo_destino(rs.getString("TIPO_DESTINO"));
			cecsDTO.setDestino(rs.getString("DESTINO"));
			cecsDTO.setId_unico_destino(rs.getString("ID_UNICO_DESTINO"));
			cecsDTO.setIatacitycode(rs.getString("iataCityCode"));
			cecsDTO.setFecha_desde_1(rs.getString("FECHA_DESDE_1"));
			cecsDTO.setFecha_hasta_1(rs.getString("FECHA_HASTA_1"));
			cecsDTO.setFecha_desde_2(rs.getString("FECHA_DESDE_2"));
			cecsDTO.setFecha_hasta_2(rs.getString("FECHA_HASTA_2"));
			cecsDTO.setFecha_desde_3(rs.getString("FECHA_DESDE_3"));
			cecsDTO.setFecha_hasta_3(rs.getString("FECHA_HASTA_3"));
			cecsDTO.setId_iatacitycode(rs.getString("ID_IATACITYCODE"));

				
			lstCecsDTO.add(cecsDTO);
			
		}
		return lstCecsDTO;
	}
	/********************************************************************************************************************/
	
}