package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.DocumentStore;





public interface DocumentStoreDAO extends GenericDAO<DocumentStore, Integer> {
  
	List<DocumentStore> findAll();
	






}


