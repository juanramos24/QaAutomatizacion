package com.vunic.qaselenium.datos.impl;

import com.vunic.core.base.hibernate.GenericDAOHibernate;
import com.vunic.qaselenium.datos.ICategoriaDAO;
import com.vunic.qaselenium.dto.CategoriaDTO;

public class CategoriaDAOHibernate extends GenericDAOHibernate<CategoriaDTO,Integer> implements  ICategoriaDAO {

	public CategoriaDAOHibernate() {
		
		setDataBase("TRAVEL_MYSQL");
	}

	
}
