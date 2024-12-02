package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.Deal;





public interface DealDAO extends GenericDAO<Deal, Integer> {
  
	List<Deal> findAll();
	






}


