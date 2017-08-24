package com.vunic.qaselenium.datos;

import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.dto.OverDTO;

public interface IOverDAO {

	public List<OverDTO> findOverAll() throws SQLException;
}
