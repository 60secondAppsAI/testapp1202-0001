package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.SmsNotification;





public interface SmsNotificationDAO extends GenericDAO<SmsNotification, Integer> {
  
	List<SmsNotification> findAll();
	






}


