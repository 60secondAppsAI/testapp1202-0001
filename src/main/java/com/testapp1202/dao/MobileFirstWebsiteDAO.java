package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.MobileFirstWebsite;





public interface MobileFirstWebsiteDAO extends GenericDAO<MobileFirstWebsite, Integer> {
  
	List<MobileFirstWebsite> findAll();
	






}


