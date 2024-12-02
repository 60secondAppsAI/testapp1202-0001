package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.EmailNotification;





public interface EmailNotificationDAO extends GenericDAO<EmailNotification, Integer> {
  
	List<EmailNotification> findAll();
	






}


