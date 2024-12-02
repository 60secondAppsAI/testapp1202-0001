package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.DealRepresentative;
import com.testapp1202.dto.DealRepresentativeDTO;
import com.testapp1202.dto.DealRepresentativeSearchDTO;
import com.testapp1202.dto.DealRepresentativePageDTO;
import com.testapp1202.dto.DealRepresentativeConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface DealRepresentativeService extends GenericService<DealRepresentative, Integer> {

	List<DealRepresentative> findAll();

	ResultDTO addDealRepresentative(DealRepresentativeDTO dealRepresentativeDTO, RequestDTO requestDTO);

	ResultDTO updateDealRepresentative(DealRepresentativeDTO dealRepresentativeDTO, RequestDTO requestDTO);

    Page<DealRepresentative> getAllDealRepresentatives(Pageable pageable);

    Page<DealRepresentative> getAllDealRepresentatives(Specification<DealRepresentative> spec, Pageable pageable);

	ResponseEntity<DealRepresentativePageDTO> getDealRepresentatives(DealRepresentativeSearchDTO dealRepresentativeSearchDTO);
	
	List<DealRepresentativeDTO> convertDealRepresentativesToDealRepresentativeDTOs(List<DealRepresentative> dealRepresentatives, DealRepresentativeConvertCriteriaDTO convertCriteria);

	DealRepresentativeDTO getDealRepresentativeDTOById(Integer dealRepresentativeId);







}





