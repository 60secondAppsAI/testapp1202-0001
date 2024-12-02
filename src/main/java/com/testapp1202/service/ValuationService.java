package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Valuation;
import com.testapp1202.dto.ValuationDTO;
import com.testapp1202.dto.ValuationSearchDTO;
import com.testapp1202.dto.ValuationPageDTO;
import com.testapp1202.dto.ValuationConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ValuationService extends GenericService<Valuation, Integer> {

	List<Valuation> findAll();

	ResultDTO addValuation(ValuationDTO valuationDTO, RequestDTO requestDTO);

	ResultDTO updateValuation(ValuationDTO valuationDTO, RequestDTO requestDTO);

    Page<Valuation> getAllValuations(Pageable pageable);

    Page<Valuation> getAllValuations(Specification<Valuation> spec, Pageable pageable);

	ResponseEntity<ValuationPageDTO> getValuations(ValuationSearchDTO valuationSearchDTO);
	
	List<ValuationDTO> convertValuationsToValuationDTOs(List<Valuation> valuations, ValuationConvertCriteriaDTO convertCriteria);

	ValuationDTO getValuationDTOById(Integer valuationId);







}





