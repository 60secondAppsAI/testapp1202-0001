package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.Agreement;





public interface AgreementDAO extends GenericDAO<Agreement, Integer> {
  
	List<Agreement> findAll();
	






}


