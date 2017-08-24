package com.vunic.qaselenium.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoDTO;

public interface IControlEjecucionCalculoDAO 
{
	public List<ControlEjecucionCalculoDTO> obtenerDatosPrueba() throws  SQLException;
	public List<ControlEjecucionCalculoDTO> insert(List<ControlEjecucionCalculoDTO> dtos) throws SQLException;
	
	public List<ControlEjecucionCalculoDTO> obtenerGrupoIDPromocion() throws SQLException;
	public List<ControlEjecucionCalculoDTO> obtenerPromocionSegmento(int id) throws SQLException;
	public void deleteFull() throws  SQLException;
}