package com.testapp1202.dao;

import java.util.List;

import com.testapp1202.dao.GenericDAO;
import com.testapp1202.domain.PreliminaryDiscussion;





public interface PreliminaryDiscussionDAO extends GenericDAO<PreliminaryDiscussion, Integer> {
  
	List<PreliminaryDiscussion> findAll();
	






}


