package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.Customer;





public interface CustomerDAO extends GenericDAO<Customer, Integer> {
  
	List<Customer> findAll();
	






}


