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
import com.testapp1202.dao.DealRepresentativeDAO;
import com.testapp1202.domain.DealRepresentative;
import com.testapp1202.dto.DealRepresentativeDTO;
import com.testapp1202.dto.DealRepresentativeSearchDTO;
import com.testapp1202.dto.DealRepresentativePageDTO;
import com.testapp1202.dto.DealRepresentativeConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.DealRepresentativeService;
import com.testapp1202.util.ControllerUtils;





@Service
public class DealRepresentativeServiceImpl extends GenericServiceImpl<DealRepresentative, Integer> implements DealRepresentativeService {

    private final static Logger logger = LoggerFactory.getLogger(DealRepresentativeServiceImpl.class);

	@Autowired
	DealRepresentativeDAO dealRepresentativeDao;

	


	@Override
	public GenericDAO<DealRepresentative, Integer> getDAO() {
		return (GenericDAO<DealRepresentative, Integer>) dealRepresentativeDao;
	}
	
	public List<DealRepresentative> findAll () {
		List<DealRepresentative> dealRepresentatives = dealRepresentativeDao.findAll();
		
		return dealRepresentatives;	
		
	}

	public ResultDTO addDealRepresentative(DealRepresentativeDTO dealRepresentativeDTO, RequestDTO requestDTO) {

		DealRepresentative dealRepresentative = new DealRepresentative();

		dealRepresentative.setDealRepresentativeId(dealRepresentativeDTO.getDealRepresentativeId());


		dealRepresentative.setName(dealRepresentativeDTO.getName());


		dealRepresentative.setEmail(dealRepresentativeDTO.getEmail());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		dealRepresentative = dealRepresentativeDao.save(dealRepresentative);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<DealRepresentative> getAllDealRepresentatives(Pageable pageable) {
		return dealRepresentativeDao.findAll(pageable);
	}

	public Page<DealRepresentative> getAllDealRepresentatives(Specification<DealRepresentative> spec, Pageable pageable) {
		return dealRepresentativeDao.findAll(spec, pageable);
	}

	public ResponseEntity<DealRepresentativePageDTO> getDealRepresentatives(DealRepresentativeSearchDTO dealRepresentativeSearchDTO) {
	
			Integer dealRepresentativeId = dealRepresentativeSearchDTO.getDealRepresentativeId(); 
 			String name = dealRepresentativeSearchDTO.getName(); 
 			String email = dealRepresentativeSearchDTO.getEmail(); 
 			String sortBy = dealRepresentativeSearchDTO.getSortBy();
			String sortOrder = dealRepresentativeSearchDTO.getSortOrder();
			String searchQuery = dealRepresentativeSearchDTO.getSearchQuery();
			Integer page = dealRepresentativeSearchDTO.getPage();
			Integer size = dealRepresentativeSearchDTO.getSize();

	        Specification<DealRepresentative> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dealRepresentativeId, "dealRepresentativeId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<DealRepresentative> dealRepresentatives = this.getAllDealRepresentatives(spec, pageable);
		
		//System.out.println(String.valueOf(dealRepresentatives.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(dealRepresentatives.getTotalPages()));
		
		List<DealRepresentative> dealRepresentativesList = dealRepresentatives.getContent();
		
		DealRepresentativeConvertCriteriaDTO convertCriteria = new DealRepresentativeConvertCriteriaDTO();
		List<DealRepresentativeDTO> dealRepresentativeDTOs = this.convertDealRepresentativesToDealRepresentativeDTOs(dealRepresentativesList,convertCriteria);
		
		DealRepresentativePageDTO dealRepresentativePageDTO = new DealRepresentativePageDTO();
		dealRepresentativePageDTO.setDealRepresentatives(dealRepresentativeDTOs);
		dealRepresentativePageDTO.setTotalElements(dealRepresentatives.getTotalElements());
		return ResponseEntity.ok(dealRepresentativePageDTO);
	}

	public List<DealRepresentativeDTO> convertDealRepresentativesToDealRepresentativeDTOs(List<DealRepresentative> dealRepresentatives, DealRepresentativeConvertCriteriaDTO convertCriteria) {
		
		List<DealRepresentativeDTO> dealRepresentativeDTOs = new ArrayList<DealRepresentativeDTO>();
		
		for (DealRepresentative dealRepresentative : dealRepresentatives) {
			dealRepresentativeDTOs.add(convertDealRepresentativeToDealRepresentativeDTO(dealRepresentative,convertCriteria));
		}
		
		return dealRepresentativeDTOs;

	}
	
	public DealRepresentativeDTO convertDealRepresentativeToDealRepresentativeDTO(DealRepresentative dealRepresentative, DealRepresentativeConvertCriteriaDTO convertCriteria) {
		
		DealRepresentativeDTO dealRepresentativeDTO = new DealRepresentativeDTO();
		
		dealRepresentativeDTO.setDealRepresentativeId(dealRepresentative.getDealRepresentativeId());

	
		dealRepresentativeDTO.setName(dealRepresentative.getName());

	
		dealRepresentativeDTO.setEmail(dealRepresentative.getEmail());

	

		
		return dealRepresentativeDTO;
	}

	public ResultDTO updateDealRepresentative(DealRepresentativeDTO dealRepresentativeDTO, RequestDTO requestDTO) {
		
		DealRepresentative dealRepresentative = dealRepresentativeDao.getById(dealRepresentativeDTO.getDealRepresentativeId());

		dealRepresentative.setDealRepresentativeId(ControllerUtils.setValue(dealRepresentative.getDealRepresentativeId(), dealRepresentativeDTO.getDealRepresentativeId()));

		dealRepresentative.setName(ControllerUtils.setValue(dealRepresentative.getName(), dealRepresentativeDTO.getName()));

		dealRepresentative.setEmail(ControllerUtils.setValue(dealRepresentative.getEmail(), dealRepresentativeDTO.getEmail()));



        dealRepresentative = dealRepresentativeDao.save(dealRepresentative);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DealRepresentativeDTO getDealRepresentativeDTOById(Integer dealRepresentativeId) {
	
		DealRepresentative dealRepresentative = dealRepresentativeDao.getById(dealRepresentativeId);
			
		
		DealRepresentativeConvertCriteriaDTO convertCriteria = new DealRepresentativeConvertCriteriaDTO();
		return(this.convertDealRepresentativeToDealRepresentativeDTO(dealRepresentative,convertCriteria));
	}







}
