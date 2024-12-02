package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.UserAccount;





public interface UserAccountDAO extends GenericDAO<UserAccount, Integer> {
  
	List<UserAccount> findAll();
	






}


