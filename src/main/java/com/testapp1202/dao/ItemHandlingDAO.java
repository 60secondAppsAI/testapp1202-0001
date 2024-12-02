package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.ItemHandling;





public interface ItemHandlingDAO extends GenericDAO<ItemHandling, Integer> {
  
	List<ItemHandling> findAll();
	






}


