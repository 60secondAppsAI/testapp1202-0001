package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.SeoStrategy;





public interface SeoStrategyDAO extends GenericDAO<SeoStrategy, Integer> {
  
	List<SeoStrategy> findAll();
	






}


