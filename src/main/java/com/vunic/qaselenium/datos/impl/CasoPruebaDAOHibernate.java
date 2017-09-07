package com.vunic.qaselenium.datos.impl;

import com.vunic.core.base.hibernate.GenericDAOHibernate;
import com.vunic.qaselenium.datos.*;
import com.vunic.qaselenium.dto.CategoriaDTO;

public class CasoPruebaDAOHibernate extends GenericDAOHibernate<CategoriaDTO,Integer> implements  ICategoriaDAO {

	public CasoPruebaDAOHibernate() {
		
		setDataBase("TRAVEL_MYSQL");
	}

	
}
