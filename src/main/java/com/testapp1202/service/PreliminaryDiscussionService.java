package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.PreliminaryDiscussion;
import com.testapp1202.dto.PreliminaryDiscussionDTO;
import com.testapp1202.dto.PreliminaryDiscussionSearchDTO;
import com.testapp1202.dto.PreliminaryDiscussionPageDTO;
import com.testapp1202.dto.PreliminaryDiscussionConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface PreliminaryDiscussionService extends GenericService<PreliminaryDiscussion, Integer> {

	List<PreliminaryDiscussion> findAll();

	ResultDTO addPreliminaryDiscussion(PreliminaryDiscussionDTO preliminaryDiscussionDTO, RequestDTO requestDTO);

	ResultDTO updatePreliminaryDiscussion(PreliminaryDiscussionDTO preliminaryDiscussionDTO, RequestDTO requestDTO);

    Page<PreliminaryDiscussion> getAllPreliminaryDiscussions(Pageable pageable);

    Page<PreliminaryDiscussion> getAllPreliminaryDiscussions(Specification<PreliminaryDiscussion> spec, Pageable pageable);

	ResponseEntity<PreliminaryDiscussionPageDTO> getPreliminaryDiscussions(PreliminaryDiscussionSearchDTO preliminaryDiscussionSearchDTO);
	
	List<PreliminaryDiscussionDTO> convertPreliminaryDiscussionsToPreliminaryDiscussionDTOs(List<PreliminaryDiscussion> preliminaryDiscussions, PreliminaryDiscussionConvertCriteriaDTO convertCriteria);

	PreliminaryDiscussionDTO getPreliminaryDiscussionDTOById(Integer preliminaryDiscussionId);







}





