package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Deal;
import com.testapp1202.dto.DealDTO;
import com.testapp1202.dto.DealSearchDTO;
import com.testapp1202.dto.DealPageDTO;
import com.testapp1202.dto.DealConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealService extends GenericService<Deal, Integer> {

	List<Deal> findAll();

	ResultDTO addDeal(DealDTO dealDTO, RequestDTO requestDTO);

	ResultDTO updateDeal(DealDTO dealDTO, RequestDTO requestDTO);

    Page<Deal> getAllDeals(Pageable pageable);

    Page<Deal> getAllDeals(Specification<Deal> spec, Pageable pageable);

	ResponseEntity<DealPageDTO> getDeals(DealSearchDTO dealSearchDTO);
	
	List<DealDTO> convertDealsToDealDTOs(List<Deal> deals, DealConvertCriteriaDTO convertCriteria);

	DealDTO getDealDTOById(Integer dealId);







}





