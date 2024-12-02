package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.DealDashboard;
import com.testapp1202.dto.DealDashboardDTO;
import com.testapp1202.dto.DealDashboardSearchDTO;
import com.testapp1202.dto.DealDashboardPageDTO;
import com.testapp1202.dto.DealDashboardConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealDashboardService extends GenericService<DealDashboard, Integer> {

	List<DealDashboard> findAll();

	ResultDTO addDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO);

	ResultDTO updateDealDashboard(DealDashboardDTO dealDashboardDTO, RequestDTO requestDTO);

    Page<DealDashboard> getAllDealDashboards(Pageable pageable);

    Page<DealDashboard> getAllDealDashboards(Specification<DealDashboard> spec, Pageable pageable);

	ResponseEntity<DealDashboardPageDTO> getDealDashboards(DealDashboardSearchDTO dealDashboardSearchDTO);
	
	List<DealDashboardDTO> convertDealDashboardsToDealDashboardDTOs(List<DealDashboard> dealDashboards, DealDashboardConvertCriteriaDTO convertCriteria);

	DealDashboardDTO getDealDashboardDTOById(Integer dealDashboardId);







}





