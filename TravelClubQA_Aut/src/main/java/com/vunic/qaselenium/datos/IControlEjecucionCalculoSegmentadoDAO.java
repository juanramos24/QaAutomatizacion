package com.vunic.qaselenium.datos;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoDTO;
import com.vunic.qaselenium.dto.ControlEjecucionCalculoSegmentadoDTO;

public interface IControlEjecucionCalculoSegmentadoDAO 
{
	public List<ControlEjecucionCalculoDTO> insert(List<ControlEjecucionCalculoDTO> dtos) throws SQLException;
	public List<ControlEjecucionCalculoSegmentadoDTO> obtenerDatosEjecutar() throws SQLException;
	public void deleteFull() throws  SQLException;
}
