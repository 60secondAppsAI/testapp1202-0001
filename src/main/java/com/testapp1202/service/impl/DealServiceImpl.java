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
import com.testapp1202.dao.DealDAO;
import com.testapp1202.domain.Deal;
import com.testapp1202.dto.DealDTO;
import com.testapp1202.dto.DealSearchDTO;
import com.testapp1202.dto.DealPageDTO;
import com.testapp1202.dto.DealConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.DealService;
import com.testapp1202.util.ControllerUtils;





@Service
public class DealServiceImpl extends GenericServiceImpl<Deal, Integer> implements DealService {

    private final static Logger logger = LoggerFactory.getLogger(DealServiceImpl.class);

	@Autowired
	DealDAO dealDao;

	


	@Override
	public GenericDAO<Deal, Integer> getDAO() {
		return (GenericDAO<Deal, Integer>) dealDao;
	}
	
	public List<Deal> findAll () {
		List<Deal> deals = dealDao.findAll();
		
		return deals;	
		
	}

	public ResultDTO addDeal(DealDTO dealDTO, RequestDTO requestDTO) {

		Deal deal = new Deal();

		deal.setDealId(dealDTO.getDealId());


		deal.setSubmissionDate(dealDTO.getSubmissionDate());


		deal.setStatus(dealDTO.getStatus());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		deal = dealDao.save(deal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Deal> getAllDeals(Pageable pageable) {
		return dealDao.findAll(pageable);
	}

	public Page<Deal> getAllDeals(Specification<Deal> spec, Pageable pageable) {
		return dealDao.findAll(spec, pageable);
	}

	public ResponseEntity<DealPageDTO> getDeals(DealSearchDTO dealSearchDTO) {
	
			Integer dealId = dealSearchDTO.getDealId(); 
   			String status = dealSearchDTO.getStatus(); 
 			String sortBy = dealSearchDTO.getSortBy();
			String sortOrder = dealSearchDTO.getSortOrder();
			String searchQuery = dealSearchDTO.getSearchQuery();
			Integer page = dealSearchDTO.getPage();
			Integer size = dealSearchDTO.getSize();

	        Specification<Deal> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, dealId, "dealId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, status, "status"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("status")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<Deal> deals = this.getAllDeals(spec, pageable);
		
		//System.out.println(String.valueOf(deals.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(deals.getTotalPages()));
		
		List<Deal> dealsList = deals.getContent();
		
		DealConvertCriteriaDTO convertCriteria = new DealConvertCriteriaDTO();
		List<DealDTO> dealDTOs = this.convertDealsToDealDTOs(dealsList,convertCriteria);
		
		DealPageDTO dealPageDTO = new DealPageDTO();
		dealPageDTO.setDeals(dealDTOs);
		dealPageDTO.setTotalElements(deals.getTotalElements());
		return ResponseEntity.ok(dealPageDTO);
	}

	public List<DealDTO> convertDealsToDealDTOs(List<Deal> deals, DealConvertCriteriaDTO convertCriteria) {
		
		List<DealDTO> dealDTOs = new ArrayList<DealDTO>();
		
		for (Deal deal : deals) {
			dealDTOs.add(convertDealToDealDTO(deal,convertCriteria));
		}
		
		return dealDTOs;

	}
	
	public DealDTO convertDealToDealDTO(Deal deal, DealConvertCriteriaDTO convertCriteria) {
		
		DealDTO dealDTO = new DealDTO();
		
		dealDTO.setDealId(deal.getDealId());

	
		dealDTO.setSubmissionDate(deal.getSubmissionDate());

	
		dealDTO.setStatus(deal.getStatus());

	

		
		return dealDTO;
	}

	public ResultDTO updateDeal(DealDTO dealDTO, RequestDTO requestDTO) {
		
		Deal deal = dealDao.getById(dealDTO.getDealId());

		deal.setDealId(ControllerUtils.setValue(deal.getDealId(), dealDTO.getDealId()));

		deal.setSubmissionDate(ControllerUtils.setValue(deal.getSubmissionDate(), dealDTO.getSubmissionDate()));

		deal.setStatus(ControllerUtils.setValue(deal.getStatus(), dealDTO.getStatus()));



        deal = dealDao.save(deal);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public DealDTO getDealDTOById(Integer dealId) {
	
		Deal deal = dealDao.getById(dealId);
			
		
		DealConvertCriteriaDTO convertCriteria = new DealConvertCriteriaDTO();
		return(this.convertDealToDealDTO(deal,convertCriteria));
	}







}
