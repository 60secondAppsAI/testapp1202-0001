package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.ShippingDetails;





public interface ShippingDetailsDAO extends GenericDAO<ShippingDetails, Integer> {
  
	List<ShippingDetails> findAll();
	






}


