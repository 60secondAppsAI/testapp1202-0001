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
import com.testapp1202.dao.ValuationDAO;
import com.testapp1202.domain.Valuation;
import com.testapp1202.dto.ValuationDTO;
import com.testapp1202.dto.ValuationSearchDTO;
import com.testapp1202.dto.ValuationPageDTO;
import com.testapp1202.dto.ValuationConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.ValuationService;
import com.testapp1202.util.ControllerUtils;





@Service
public class ValuationServiceImpl extends GenericServiceImpl<Valuation, Integer> implements ValuationService {

    private final static Logger logger = LoggerFactory.getLogger(ValuationServiceImpl.class);

	@Autowired
	ValuationDAO valuationDao;

	


	@Override
	public GenericDAO<Valuation, Integer> getDAO() {
		return (GenericDAO<Valuation, Integer>) valuationDao;
	}
	
	public List<Valuation> findAll () {
		List<Valuation> valuations = valuationDao.findAll();
		
		return valuations;	
		
	}

	public ResultDTO addValuation(ValuationDTO valuationDTO, RequestDTO requestDTO) {

		Valuation valuation = new Valuation();

		valuation.setValuationId(valuationDTO.getValuationId());


		valuation.setValuationRange(valuationDTO.getValuationRange());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		valuation = valuationDao.save(valuation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Valuation> getAllValuations(Pageable pageable) {
		return valuationDao.findAll(pageable);
	}

	public Page<Valuation> getAllValuations(Specification<Valuation> spec, Pageable pageable) {
		return valuationDao.findAll(spec, pageable);
	}

	public ResponseEntity<ValuationPageDTO> getValuations(ValuationSearchDTO valuationSearchDTO) {
	
			Integer valuationId = valuationSearchDTO.getValuationId(); 
  			String sortBy = valuationSearchDTO.getSortBy();
			String sortOrder = valuationSearchDTO.getSortOrder();
			String searchQuery = valuationSearchDTO.getSearchQuery();
			Integer page = valuationSearchDTO.getPage();
			Integer size = valuationSearchDTO.getSize();

	        Specification<Valuation> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, valuationId, "valuationId"); 
			
			

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

		Page<Valuation> valuations = this.getAllValuations(spec, pageable);
		
		//System.out.println(String.valueOf(valuations.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(valuations.getTotalPages()));
		
		List<Valuation> valuationsList = valuations.getContent();
		
		ValuationConvertCriteriaDTO convertCriteria = new ValuationConvertCriteriaDTO();
		List<ValuationDTO> valuationDTOs = this.convertValuationsToValuationDTOs(valuationsList,convertCriteria);
		
		ValuationPageDTO valuationPageDTO = new ValuationPageDTO();
		valuationPageDTO.setValuations(valuationDTOs);
		valuationPageDTO.setTotalElements(valuations.getTotalElements());
		return ResponseEntity.ok(valuationPageDTO);
	}

	public List<ValuationDTO> convertValuationsToValuationDTOs(List<Valuation> valuations, ValuationConvertCriteriaDTO convertCriteria) {
		
		List<ValuationDTO> valuationDTOs = new ArrayList<ValuationDTO>();
		
		for (Valuation valuation : valuations) {
			valuationDTOs.add(convertValuationToValuationDTO(valuation,convertCriteria));
		}
		
		return valuationDTOs;

	}
	
	public ValuationDTO convertValuationToValuationDTO(Valuation valuation, ValuationConvertCriteriaDTO convertCriteria) {
		
		ValuationDTO valuationDTO = new ValuationDTO();
		
		valuationDTO.setValuationId(valuation.getValuationId());

	
		valuationDTO.setValuationRange(valuation.getValuationRange());

	

		
		return valuationDTO;
	}

	public ResultDTO updateValuation(ValuationDTO valuationDTO, RequestDTO requestDTO) {
		
		Valuation valuation = valuationDao.getById(valuationDTO.getValuationId());

		valuation.setValuationId(ControllerUtils.setValue(valuation.getValuationId(), valuationDTO.getValuationId()));

		valuation.setValuationRange(ControllerUtils.setValue(valuation.getValuationRange(), valuationDTO.getValuationRange()));



        valuation = valuationDao.save(valuation);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ValuationDTO getValuationDTOById(Integer valuationId) {
	
		Valuation valuation = valuationDao.getById(valuationId);
			
		
		ValuationConvertCriteriaDTO convertCriteria = new ValuationConvertCriteriaDTO();
		return(this.convertValuationToValuationDTO(valuation,convertCriteria));
	}







}
