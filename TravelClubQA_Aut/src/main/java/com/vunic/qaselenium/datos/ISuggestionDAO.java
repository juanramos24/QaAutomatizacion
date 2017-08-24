package com.vunic.qaselenium.datos;

import java.sql.SQLException;
import java.util.List;

import com.vunic.qaselenium.dto.MongoSearchsuggestionDTO;
import com.vunic.qaselenium.dto.OverDTO;
import com.vunic.qaselenium.dto.SuggestionDTO;

public interface ISuggestionDAO 
{
	public List<SuggestionDTO> findSuggestionAll() throws SQLException;
}
