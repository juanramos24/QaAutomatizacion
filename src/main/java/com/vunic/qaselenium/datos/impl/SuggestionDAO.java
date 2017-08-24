package com.vunic.qaselenium.datos.impl;

import java.sql.SQLException;
import java.util.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.vunic.core.FactorySvc;
import com.vunic.core.datos.NoSqlService;
import com.vunic.qaselenium.datos.IMongoSearchsuggestionDAO;
import com.vunic.qaselenium.datos.IOverDAO;
import com.vunic.qaselenium.datos.ISuggestionDAO;
import com.vunic.qaselenium.dto.MongoSearchsuggestionDTO;
import com.vunic.qaselenium.dto.OverDTO;
import com.vunic.qaselenium.dto.*;

public class SuggestionDAO implements ISuggestionDAO
{
	// Constantes usadas en la clase 
	
	private static final String NAME_DB = "DBSUGGESTION";
	private static final String NAME_COLL = "SearchSuggestion";
	
	private static final String COL_ID= "id";
	private static final String COL_NAME = "name";
	private static final String COL_RANKING = "ranking";
	private static final String COL_COUNTRY = "country";
	private static final String COL_COUNTRYNAME = "countryName";
	private static final String COL_CITY= "city";
	private static final String COL_STATE = "state";
	private static final String COL_STATENAME = "stateName";
	private static final String COL_IATACITYCODE = "iataCityCode";
	private static final String COL_ACTIVE = "active";

	public List<SuggestionDTO> findSuggestionAll() throws SQLException
	{
		List<SuggestionDTO> lstSuggestion = new ArrayList<SuggestionDTO>();
		SuggestionDTO SuggestionDTO = null;
		int idx =1;

		MongoCursor<Document> cursor;

		NoSqlService bdMongo = FactorySvc.ServicioDatosNoSql(NAME_DB);

		FactorySvc.Log(getClass()).Info("CONEXION MONGO BASE 02");

		MongoCollection colSuggestion = bdMongo.getCollectionDB(NAME_DB, NAME_COLL);

		// Busco todos los documentos de la colección y los imprimo
		cursor = colSuggestion.find().iterator();

		while (cursor.hasNext())
		{
			Document obj = cursor.next(); 
			SuggestionDTO = new SuggestionDTO();

			
			SuggestionDTO.setIdDestino(idx++);
			SuggestionDTO.setId((String) obj.get(COL_ID));
			SuggestionDTO.setName((String) obj.get(COL_NAME));
			SuggestionDTO.setRanking((String) obj.get(COL_RANKING).toString());
			SuggestionDTO.setCountry((String) obj.get(COL_COUNTRY));
			SuggestionDTO.setCountryName((String) obj.get(COL_COUNTRYNAME));
			SuggestionDTO.setCity((String) obj.get(COL_CITY));
			SuggestionDTO.setState((String) obj.get(COL_STATE));
			SuggestionDTO.setStateName((String) obj.get(COL_STATENAME));
			SuggestionDTO.setIataCityCode((String) obj.get(COL_IATACITYCODE));
			SuggestionDTO.setActive((String) obj.get(COL_ACTIVE).toString());
			
			lstSuggestion.add(SuggestionDTO);
		}

		return lstSuggestion;
	}
}
