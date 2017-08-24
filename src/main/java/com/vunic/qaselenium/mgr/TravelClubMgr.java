package com.vunic.qaselenium.mgr;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.vunic.core.FactorySvc;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.datos.impl.*;
import com.vunic.qaselenium.dto.*;

public class TravelClubMgr 
{
	// Variables instancias DAO
	IMongoOverDAO mySqlOverDAO = new MongoOverDAO();
	
	IMongoOverDestinationwhitelistDAO mySqlDWhiteDAO = new MongoOverDestinationwhitelistDAO();
	IMongoOverDestinationblacklistDAO mySqlDBlackDAO = new MongoOverDestinationblacklistDAO();
	IMongoOverCabintypewhitelistDAO mySqlCWhiteDAO = new MongoOverCabintypewhitelistDAO();
	IMongoOverCabintypeblacklistDAO mySqlCBlackDAO = new MongoOverCabintypeblacklistDAO();
	IMongoOverPassengertypelistDAO mySqlPTypeDAO = new MongoOverPassengertypelistDAO();
	
	IMongoSearchsuggestionDAO mySqlSearchsuggestionDAO = new MongoSearchsuggestionDAO();
	IControlEjecucionCalculoDAO mySqlCEjecucionCalculoDAO = new ControlEjecucionCalculoDAO();
	IControlEjecucionCalculoSegmentadoDAO mySqlCEjecucionCalculoSegmentadoDAO = new ControlEjecucionCalculoSegmentadoDAO();
	
	IOverDAO overDAO = new OverDAO();
	ISuggestionDAO suggestionDAO = new SuggestionDAO();
	
	IControlEjecucionCalculoDAO mySqlCecDAO = new ControlEjecucionCalculoDAO();
	IControlEjecucionCalculoSegmentadoDAO mySqlCecsDAO = new ControlEjecucionCalculoSegmentadoDAO();
	
	///<summary>
	///Metodo que elimina un registro en la tabla Capacidad
	///</summary>
	///<param name="CapacidadDTO"></param>
	///<returns></returns>
	
	/********************************************************************************************************************/
	
	public boolean deleteFullBD()
	{
		boolean rstd = true;
		try
		{
			mySqlOverDAO.deleteFull();
			mySqlDWhiteDAO.deleteFull();
			mySqlDBlackDAO.deleteFull();
			mySqlCWhiteDAO.deleteFull();
			mySqlCBlackDAO.deleteFull();
			mySqlPTypeDAO.deleteFull();
			
			mySqlSearchsuggestionDAO.deleteFull();
			mySqlCEjecucionCalculoDAO.deleteFull();
			mySqlCEjecucionCalculoSegmentadoDAO.deleteFull();
			
		}
		catch (Exception ex)
		{
			rstd = false;
			FactorySvc.Log(this.getClass()).Error("Error al intentar borrar los registros de la BD MySql", ex);
		}
	
		return rstd;
	}

	/********************************************************************************************************************/
	
	public boolean insertOverBD(List<OverDTO> lstOver)
	{
		boolean rstd_insert = true;
		List<MongoOverDTO> lstMngOver = new ArrayList<MongoOverDTO>();
		List<MongoOverPassengertypelistDTO> lstMngOverPTL = new ArrayList<MongoOverPassengertypelistDTO>();
		List<MongoOverCabintypeblacklistDTO> lstMngOverCTBL = new ArrayList<MongoOverCabintypeblacklistDTO>();
		List<MongoOverCabintypewhitelistDTO> lstMngOverCTWL = new ArrayList<MongoOverCabintypewhitelistDTO>();
		List<MongoOverDestinationblacklistDTO> lstMngOverDBL = new ArrayList<MongoOverDestinationblacklistDTO>();
		List<MongoOverDestinationwhitelistDTO> lstMngOverDWL = new ArrayList<MongoOverDestinationwhitelistDTO>();
		
		MongoOverDTO mngOverDTO = null;
		MongoOverPassengertypelistDTO mngOverPTL = null;
		MongoOverCabintypeblacklistDTO mngOverCTBL = null;
		MongoOverCabintypewhitelistDTO mngOverCTWL = null;
		MongoOverDestinationblacklistDTO mngOverDBL = null;
		MongoOverDestinationwhitelistDTO mngOverDWL = null;
		
		
		try
		{
			for (OverDTO overDTO : lstOver) 
			{
				mngOverDTO = new MongoOverDTO();

				mngOverDTO.setId(overDTO.getId());
				mngOverDTO.setOrigin(overDTO.getOrigin());
				mngOverDTO.setAirline(overDTO.getAirline());
				mngOverDTO.setBuyingDateFrom(overDTO.getBuyingDateFrom());
				mngOverDTO.setBuyingDateTo(overDTO.getBuyingDateTo());
				mngOverDTO.setTravelDateFrom(overDTO.getTravelDateFrom());
				mngOverDTO.setTravelDateTo(overDTO.getTravelDateTo());
				mngOverDTO.setOverPercentage(overDTO.getOverPercentage());
				mngOverDTO.setPercentageToApply(overDTO.getPercentageToApply());
						
				lstMngOver.add(mngOverDTO);
				
				for (String item : overDTO.getPassengerTypeList())
				{
					mngOverPTL = new MongoOverPassengertypelistDTO();
					mngOverPTL.setId(overDTO.getId());
					mngOverPTL.setPassengerTypeList(item);
					
					lstMngOverPTL.add(mngOverPTL);
				}

				for (String item : overDTO.getCabinTypeBlackList())
				{
					mngOverCTBL = new MongoOverCabintypeblacklistDTO();
					mngOverCTBL.setId(overDTO.getId());
					mngOverCTBL.setCabinTypeBlackList(item);
					
					lstMngOverCTBL.add(mngOverCTBL);
				}
				
				for (String item : overDTO.getCabinTypeWhiteList())
				{
					mngOverCTWL = new MongoOverCabintypewhitelistDTO();
					mngOverCTWL.setId(overDTO.getId());
					mngOverCTWL.setCabinTypeWhiteList(item);
					
					lstMngOverCTWL.add(mngOverCTWL);
				}				

				for (String item : overDTO.getDestinationBlackList())
				{
					mngOverDBL = new MongoOverDestinationblacklistDTO();
					mngOverDBL.setId(overDTO.getId());
					mngOverDBL.setDestinationBlackList(item);
					
					lstMngOverDBL.add(mngOverDBL);
				}

				for (String item : overDTO.getDestinationWhiteList())
				{
					mngOverDWL = new MongoOverDestinationwhitelistDTO();
					mngOverDWL.setId(overDTO.getId());
					mngOverDWL.setDestinationWhiteList(item);
					
					lstMngOverDWL.add(mngOverDWL);
				}
				
			}

			mySqlOverDAO.insert(lstMngOver);
			mySqlPTypeDAO.insert(lstMngOverPTL);
			mySqlCBlackDAO.insert(lstMngOverCTBL);
			mySqlCWhiteDAO.insert(lstMngOverCTWL);
			mySqlDBlackDAO.insert(lstMngOverDBL);
			mySqlDWhiteDAO.insert(lstMngOverDWL);
			
		}
		catch (Exception ex)
		{
			rstd_insert = false;
			FactorySvc.Log(this.getClass()).Error("Error al insertar los registros de la BD", ex);
		}
	
		return rstd_insert;
	}
	
	
	/********************************************************************************************************************/
	
	public boolean insertSuggestionBD(List<SuggestionDTO> lstSuggestion)
	{
		boolean rstd_insert = true;
		
		List<MongoSearchsuggestionDTO> lstMngSuggestion = new ArrayList<MongoSearchsuggestionDTO>();
		MongoSearchsuggestionDTO mngSuggestionDTO = null;
		
		try
		{
			for (SuggestionDTO itemDTO : lstSuggestion)
			{
				mngSuggestionDTO = new MongoSearchsuggestionDTO();
				
				mngSuggestionDTO.setIdDestino(itemDTO.getIdDestino());
				mngSuggestionDTO.setId(itemDTO.getId());
				mngSuggestionDTO.setName(itemDTO.getName());
				mngSuggestionDTO.setRanking(itemDTO.getRanking());
				mngSuggestionDTO.setCountry(itemDTO.getCountry());
				mngSuggestionDTO.setCountryName(itemDTO.getCountryName());
				mngSuggestionDTO.setCity(itemDTO.getCity());
				mngSuggestionDTO.setState(itemDTO.getState());
				mngSuggestionDTO.setStateName(itemDTO.getStateName());
				mngSuggestionDTO.setIataCityCode(itemDTO.getIataCityCode());
				mngSuggestionDTO.setActive(itemDTO.getActive());
				
				lstMngSuggestion.add(mngSuggestionDTO);
				
			}
						
			mySqlSearchsuggestionDAO.insert(lstMngSuggestion);
		}
		catch (Exception ex)
		{
			rstd_insert = false;
			FactorySvc.Log(this.getClass()).Error("Error al insertar los registros de la BD", ex);
		}
	
		return rstd_insert;
	}
	
	/********************************************************************************************************************/
	
	public List<OverDTO> obtenerListaOver()
	{
		List<OverDTO> rstd = null;
		
		try
		{
			rstd = overDAO.findOverAll();

		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener Coleccion Over", ex);
		}
	
		return rstd;
	}
	
	/********************************************************************************************************************/
	
	public List<SuggestionDTO> obtenerListaSuggestion()
	{
		List<SuggestionDTO> rstd = null;
		
		try
		{
			rstd = suggestionDAO.findSuggestionAll();

		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener Coleccion Over", ex);
		}
	
		return rstd;
	}
	
	/********************************************************************************************************************/
	// SE CREA EL METODO EN MGR PARA OBTENER DATOS DE PRUEBA
	
	public List<ControlEjecucionCalculoDTO> obtenerDatosPrueba() 
	{
		List<ControlEjecucionCalculoDTO> rstd = null;
		
		try
		{
			rstd = mySqlCecDAO.obtenerDatosPrueba();

		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener Producto Cartesiano Datos Prueba", ex);
		}
	
		return rstd;
	}
	
	/********************************************************************************************************************/
	
	public boolean insertControlEjecucionCalculoBD(List<ControlEjecucionCalculoDTO> lstControlEjecucionCalculo)
	{
		boolean rstd_insert = true;
	
		//List<ControlEjecucionCalculoDTO> lstControlEjecucionCalculo = new ArrayList<ControlEjecucionCalculoDTO>();
		
		MongoOverDTO mngOverDTO = null;
				
		try
		{
			mySqlCecDAO.insert(lstControlEjecucionCalculo);

		}
		catch (Exception ex)
		{
			rstd_insert = false;
			FactorySvc.Log(this.getClass()).Error("Error al insertar los registros de la BD", ex);
		}
	
		return rstd_insert;
	}
	
	/********************************************************************************************************************/
	// SE CREA EL METODO EN MGR PARA OBTENER DATOS DE PRUEBA
	
	public List<ControlEjecucionCalculoDTO> obtenerGrupoIDPromocion() 
	{
		List<ControlEjecucionCalculoDTO> rstd = null;
		
		try
		{
			rstd = mySqlCecDAO.obtenerGrupoIDPromocion();

		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener ID Promocion", ex);
		}
	
		return rstd;
	}
	
	/********************************************************************************************************************/
	// SE CREA EL METODO EN MGR PARA OBTENER DATOS DE PRUEBA
	
	public List<ControlEjecucionCalculoDTO> obtenerPromocionSegmento(int id) 
	{
		List<ControlEjecucionCalculoDTO> rstd = null;
		
		try
		{
			rstd = mySqlCecDAO.obtenerPromocionSegmento(id);
		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener Segmento Promociones", ex);
		}
	
		return rstd;
	}
	
	/********************************************************************************************************************/
	
	public boolean insertControlEjecucionCalculoSegmentadoBD(List<ControlEjecucionCalculoDTO> lstControlEjecucionCalculo)
	{
		boolean rstd_insert = true;
		
		ControlEjecucionCalculoDTO ControlEjecucionCalculoDTO = null;
				
		try
		{
			mySqlCecsDAO.insert(lstControlEjecucionCalculo);

		}
		catch (Exception ex)
		{
			rstd_insert = false;
			FactorySvc.Log(this.getClass()).Error("Error al insertar los registros de la BD", ex);
		}
	
		return rstd_insert;
	}
	
	/********************************************************************************************************************/
	// SE CREA EL METODO EN MGR PARA OBTENER DATOS DE PRUEBA
	
	public List<ControlEjecucionCalculoSegmentadoDTO> obtenerDatosEjecutar()
	{
		List<ControlEjecucionCalculoSegmentadoDTO> rstd = null;
		
		try
		{
			
			rstd = mySqlCecsDAO.obtenerDatosEjecutar();

		}
		catch (Exception ex)
		{
			FactorySvc.Log(this.getClass()).Error("Error al obtener ID Promocion", ex);
		}
	
		return rstd;
	}

}
