package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.Repayment;





public interface RepaymentDAO extends GenericDAO<Repayment, Integer> {
  
	List<Repayment> findAll();
	






}


