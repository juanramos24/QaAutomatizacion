package com.vunic.qaselenium.datos.impl;

import java.sql.SQLException;
import java.util.*;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.vunic.core.FactorySvc;
import com.vunic.core.datos.NoSqlService;
import com.vunic.qaselenium.datos.IOverDAO;
import com.vunic.qaselenium.dto.OverDTO;

public class OverDAO implements IOverDAO
{
	
	// Constantes usadas en la clase 
	private static final String NAME_DB = "DBOVER";
	private static final String NAME_COLL = "Over";
	
	private static final String COL_ORIGIN = "origin";
	private static final String COL_AIRLINE = "airline";
	private static final String COL_BUYING_DATE_FROM = "buyingDateFrom";
	private static final String COL_BUYING_DATE_TO = "buyingDateTo";
	private static final String COL_TRAVEL_DATE_FROM = "travelDateFrom";
	private static final String COL_TRAVEL_DATE_TO = "travelDateTo";
	private static final String COL_OVER_PERCENTAGE = "overPercentage";
	private static final String COL_PERCENTAGE_TO_APPLY = "percentageToApply";
	private static final String COL_DESTINATION_WHITE_LIST = "destinationWhiteList";
	private static final String COL_DESTINATION_BLACK_LIST = "destinationBlackList";
	private static final String COL_CABIN_TYPE_WHITE_LIST = "cabinTypeWhiteList";
	private static final String COL_CABIN_TYPE_BLACK_LIST = "cabinTypeBlackList";
	private static final String COL_PASSENGER_TYPE_LIST = "passengerTypeList";

	
	public List<OverDTO> findOverAll() throws SQLException
	{
		List<OverDTO> lstOver = new ArrayList<OverDTO>();
		OverDTO overDTO = null;
		int idx = 1;

		MongoCursor<Document> cursor;

		NoSqlService bdMongo = FactorySvc.ServicioDatosNoSql(NAME_DB);

		FactorySvc.Log(getClass()).Info("CONEXION MONGO BASE 01");

		MongoCollection colOver = bdMongo.getCollectionDB(NAME_DB, NAME_COLL);

		// Busco todos los documentos de la colección y los imprimo
		cursor = colOver.find().iterator();

		while (cursor.hasNext())
		{
			Document obj = cursor.next(); 
			overDTO = new OverDTO();

			overDTO.setId(idx++);
			overDTO.setOrigin((String) obj.get(COL_ORIGIN));
			overDTO.setAirline((String) obj.get(COL_AIRLINE));
			overDTO.setBuyingDateFrom((Date) obj.get(COL_BUYING_DATE_FROM));
			overDTO.setBuyingDateTo((Date) obj.get(COL_BUYING_DATE_TO));
			overDTO.setTravelDateFrom((Date) obj.get(COL_TRAVEL_DATE_FROM));
			overDTO.setTravelDateTo((Date) obj.get(COL_TRAVEL_DATE_TO));
			overDTO.setOverPercentage((String) obj.get(COL_OVER_PERCENTAGE));
			overDTO.setPercentageToApply((String) obj.get(COL_PERCENTAGE_TO_APPLY));

			overDTO.setDestinationWhiteList((ArrayList<String>) obj.get(COL_DESTINATION_WHITE_LIST));
			overDTO.setDestinationBlackList((ArrayList<String>) obj.get(COL_DESTINATION_BLACK_LIST));
			overDTO.setCabinTypeWhiteList((ArrayList<String>) obj.get(COL_CABIN_TYPE_WHITE_LIST));
			overDTO.setCabinTypeBlackList((ArrayList<String>) obj.get(COL_CABIN_TYPE_BLACK_LIST));
			overDTO.setPassengerTypeList((ArrayList<String>) obj.get(COL_PASSENGER_TYPE_LIST));
			
			lstOver.add(overDTO);
		}

		return lstOver;
	}
}
