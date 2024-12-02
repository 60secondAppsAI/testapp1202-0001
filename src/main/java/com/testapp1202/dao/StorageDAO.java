package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.Storage;





public interface StorageDAO extends GenericDAO<Storage, Integer> {
  
	List<Storage> findAll();
	






}


