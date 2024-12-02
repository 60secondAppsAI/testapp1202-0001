package com.testapp1202.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.testapp1202.dao.GenericDAO;
import com.testapp1202.service.GenericService;
import com.testapp1202.service.impl.GenericServiceImpl;
import com.testapp1202.dao.PreliminaryDiscussionDAO;
import com.testapp1202.domain.PreliminaryDiscussion;
import com.testapp1202.dto.PreliminaryDiscussionDTO;
import com.testapp1202.dto.PreliminaryDiscussionSearchDTO;
import com.testapp1202.dto.PreliminaryDiscussionPageDTO;
import com.testapp1202.dto.PreliminaryDiscussionConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.PreliminaryDiscussionService;
import com.testapp1202.util.ControllerUtils;





@Service
public class PreliminaryDiscussionServiceImpl extends GenericServiceImpl<PreliminaryDiscussion, Integer> implements PreliminaryDiscussionService {

    private final static Logger logger = LoggerFactory.getLogger(PreliminaryDiscussionServiceImpl.class);

	@Autowired
	PreliminaryDiscussionDAO preliminaryDiscussionDao;

	


	@Override
	public GenericDAO<PreliminaryDiscussion, Integer> getDAO() {
		return (GenericDAO<PreliminaryDiscussion, Integer>) preliminaryDiscussionDao;
	}
	
	public List<PreliminaryDiscussion> findAll () {
		List<PreliminaryDiscussion> preliminaryDiscussions = preliminaryDiscussionDao.findAll();
		
		return preliminaryDiscussions;	
		
	}

	public ResultDTO addPreliminaryDiscussion(PreliminaryDiscussionDTO preliminaryDiscussionDTO, RequestDTO requestDTO) {

		PreliminaryDiscussion preliminaryDiscussion = new PreliminaryDiscussion();

		preliminaryDiscussion.setPreliminaryDiscussionId(preliminaryDiscussionDTO.getPreliminaryDiscussionId());


		preliminaryDiscussion.setOfferRange(preliminaryDiscussionDTO.getOfferRange());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		preliminaryDiscussion = preliminaryDiscussionDao.save(preliminaryDiscussion);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<PreliminaryDiscussion> getAllPreliminaryDiscussions(Pageable pageable) {
		return preliminaryDiscussionDao.findAll(pageable);
	}

	public Page<PreliminaryDiscussion> getAllPreliminaryDiscussions(Specification<PreliminaryDiscussion> spec, Pageable pageable) {
		return preliminaryDiscussionDao.findAll(spec, pageable);
	}

	public ResponseEntity<PreliminaryDiscussionPageDTO> getPreliminaryDiscussions(PreliminaryDiscussionSearchDTO preliminaryDiscussionSearchDTO) {
	
			Integer preliminaryDiscussionId = preliminaryDiscussionSearchDTO.getPreliminaryDiscussionId(); 
  			String sortBy = preliminaryDiscussionSearchDTO.getSortBy();
			String sortOrder = preliminaryDiscussionSearchDTO.getSortOrder();
			String searchQuery = preliminaryDiscussionSearchDTO.getSearchQuery();
			Integer page = preliminaryDiscussionSearchDTO.getPage();
			Integer size = preliminaryDiscussionSearchDTO.getSize();

	        Specification<PreliminaryDiscussion> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, preliminaryDiscussionId, "preliminaryDiscussionId"); 
			
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<PreliminaryDiscussion> preliminaryDiscussions = this.getAllPreliminaryDiscussions(spec, pageable);
		
		//System.out.println(String.valueOf(preliminaryDiscussions.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(preliminaryDiscussions.getTotalPages()));
		
		List<PreliminaryDiscussion> preliminaryDiscussionsList = preliminaryDiscussions.getContent();
		
		PreliminaryDiscussionConvertCriteriaDTO convertCriteria = new PreliminaryDiscussionConvertCriteriaDTO();
		List<PreliminaryDiscussionDTO> preliminaryDiscussionDTOs = this.convertPreliminaryDiscussionsToPreliminaryDiscussionDTOs(preliminaryDiscussionsList,convertCriteria);
		
		PreliminaryDiscussionPageDTO preliminaryDiscussionPageDTO = new PreliminaryDiscussionPageDTO();
		preliminaryDiscussionPageDTO.setPreliminaryDiscussions(preliminaryDiscussionDTOs);
		preliminaryDiscussionPageDTO.setTotalElements(preliminaryDiscussions.getTotalElements());
		return ResponseEntity.ok(preliminaryDiscussionPageDTO);
	}

	public List<PreliminaryDiscussionDTO> convertPreliminaryDiscussionsToPreliminaryDiscussionDTOs(List<PreliminaryDiscussion> preliminaryDiscussions, PreliminaryDiscussionConvertCriteriaDTO convertCriteria) {
		
		List<PreliminaryDiscussionDTO> preliminaryDiscussionDTOs = new ArrayList<PreliminaryDiscussionDTO>();
		
		for (PreliminaryDiscussion preliminaryDiscussion : preliminaryDiscussions) {
			preliminaryDiscussionDTOs.add(convertPreliminaryDiscussionToPreliminaryDiscussionDTO(preliminaryDiscussion,convertCriteria));
		}
		
		return preliminaryDiscussionDTOs;

	}
	
	public PreliminaryDiscussionDTO convertPreliminaryDiscussionToPreliminaryDiscussionDTO(PreliminaryDiscussion preliminaryDiscussion, PreliminaryDiscussionConvertCriteriaDTO convertCriteria) {
		
		PreliminaryDiscussionDTO preliminaryDiscussionDTO = new PreliminaryDiscussionDTO();
		
		preliminaryDiscussionDTO.setPreliminaryDiscussionId(preliminaryDiscussion.getPreliminaryDiscussionId());

	
		preliminaryDiscussionDTO.setOfferRange(preliminaryDiscussion.getOfferRange());

	

		
		return preliminaryDiscussionDTO;
	}

	public ResultDTO updatePreliminaryDiscussion(PreliminaryDiscussionDTO preliminaryDiscussionDTO, RequestDTO requestDTO) {
		
		PreliminaryDiscussion preliminaryDiscussion = preliminaryDiscussionDao.getById(preliminaryDiscussionDTO.getPreliminaryDiscussionId());

		preliminaryDiscussion.setPreliminaryDiscussionId(ControllerUtils.setValue(preliminaryDiscussion.getPreliminaryDiscussionId(), preliminaryDiscussionDTO.getPreliminaryDiscussionId()));

		preliminaryDiscussion.setOfferRange(ControllerUtils.setValue(preliminaryDiscussion.getOfferRange(), preliminaryDiscussionDTO.getOfferRange()));



        preliminaryDiscussion = preliminaryDiscussionDao.save(preliminaryDiscussion);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public PreliminaryDiscussionDTO getPreliminaryDiscussionDTOById(Integer preliminaryDiscussionId) {
	
		PreliminaryDiscussion preliminaryDiscussion = preliminaryDiscussionDao.getById(preliminaryDiscussionId);
			
		
		PreliminaryDiscussionConvertCriteriaDTO convertCriteria = new PreliminaryDiscussionConvertCriteriaDTO();
		return(this.convertPreliminaryDiscussionToPreliminaryDiscussionDTO(preliminaryDiscussion,convertCriteria));
	}







}
