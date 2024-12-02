package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.ItemHandling;
import com.testapp1202.dto.ItemHandlingDTO;
import com.testapp1202.dto.ItemHandlingSearchDTO;
import com.testapp1202.dto.ItemHandlingPageDTO;
import com.testapp1202.dto.ItemHandlingConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ItemHandlingService extends GenericService<ItemHandling, Integer> {

	List<ItemHandling> findAll();

	ResultDTO addItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO);

	ResultDTO updateItemHandling(ItemHandlingDTO itemHandlingDTO, RequestDTO requestDTO);

    Page<ItemHandling> getAllItemHandlings(Pageable pageable);

    Page<ItemHandling> getAllItemHandlings(Specification<ItemHandling> spec, Pageable pageable);

	ResponseEntity<ItemHandlingPageDTO> getItemHandlings(ItemHandlingSearchDTO itemHandlingSearchDTO);
	
	List<ItemHandlingDTO> convertItemHandlingsToItemHandlingDTOs(List<ItemHandling> itemHandlings, ItemHandlingConvertCriteriaDTO convertCriteria);

	ItemHandlingDTO getItemHandlingDTOById(Integer itemHandlingId);







}





