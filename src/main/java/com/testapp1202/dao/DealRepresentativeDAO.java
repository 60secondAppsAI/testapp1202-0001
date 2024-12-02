package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.DealRepresentative;





public interface DealRepresentativeDAO extends GenericDAO<DealRepresentative, Integer> {
  
	List<DealRepresentative> findAll();
	






}


