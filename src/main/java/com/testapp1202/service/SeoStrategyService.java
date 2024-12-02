package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.SeoStrategy;
import com.testapp1202.dto.SeoStrategyDTO;
import com.testapp1202.dto.SeoStrategySearchDTO;
import com.testapp1202.dto.SeoStrategyPageDTO;
import com.testapp1202.dto.SeoStrategyConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SeoStrategyService extends GenericService<SeoStrategy, Integer> {

	List<SeoStrategy> findAll();

	ResultDTO addSeoStrategy(SeoStrategyDTO seoStrategyDTO, RequestDTO requestDTO);

	ResultDTO updateSeoStrategy(SeoStrategyDTO seoStrategyDTO, RequestDTO requestDTO);

    Page<SeoStrategy> getAllSeoStrategys(Pageable pageable);

    Page<SeoStrategy> getAllSeoStrategys(Specification<SeoStrategy> spec, Pageable pageable);

	ResponseEntity<SeoStrategyPageDTO> getSeoStrategys(SeoStrategySearchDTO seoStrategySearchDTO);
	
	List<SeoStrategyDTO> convertSeoStrategysToSeoStrategyDTOs(List<SeoStrategy> seoStrategys, SeoStrategyConvertCriteriaDTO convertCriteria);

	SeoStrategyDTO getSeoStrategyDTOById(Integer seoStrategyId);







}





