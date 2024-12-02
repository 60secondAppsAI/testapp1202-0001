package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.DealDashboard;





public interface DealDashboardDAO extends GenericDAO<DealDashboard, Integer> {
  
	List<DealDashboard> findAll();
	






}


