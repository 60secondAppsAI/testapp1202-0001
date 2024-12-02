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
import com.testapp1202.dao.SeoStrategyDAO;
import com.testapp1202.domain.SeoStrategy;
import com.testapp1202.dto.SeoStrategyDTO;
import com.testapp1202.dto.SeoStrategySearchDTO;
import com.testapp1202.dto.SeoStrategyPageDTO;
import com.testapp1202.dto.SeoStrategyConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.SeoStrategyService;
import com.testapp1202.util.ControllerUtils;





@Service
public class SeoStrategyServiceImpl extends GenericServiceImpl<SeoStrategy, Integer> implements SeoStrategyService {

    private final static Logger logger = LoggerFactory.getLogger(SeoStrategyServiceImpl.class);

	@Autowired
	SeoStrategyDAO seoStrategyDao;

	


	@Override
	public GenericDAO<SeoStrategy, Integer> getDAO() {
		return (GenericDAO<SeoStrategy, Integer>) seoStrategyDao;
	}
	
	public List<SeoStrategy> findAll () {
		List<SeoStrategy> seoStrategys = seoStrategyDao.findAll();
		
		return seoStrategys;	
		
	}

	public ResultDTO addSeoStrategy(SeoStrategyDTO seoStrategyDTO, RequestDTO requestDTO) {

		SeoStrategy seoStrategy = new SeoStrategy();

		seoStrategy.setSeoStrategyId(seoStrategyDTO.getSeoStrategyId());


		seoStrategy.setStrategyDescription(seoStrategyDTO.getStrategyDescription());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		seoStrategy = seoStrategyDao.save(seoStrategy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SeoStrategy> getAllSeoStrategys(Pageable pageable) {
		return seoStrategyDao.findAll(pageable);
	}

	public Page<SeoStrategy> getAllSeoStrategys(Specification<SeoStrategy> spec, Pageable pageable) {
		return seoStrategyDao.findAll(spec, pageable);
	}

	public ResponseEntity<SeoStrategyPageDTO> getSeoStrategys(SeoStrategySearchDTO seoStrategySearchDTO) {
	
			Integer seoStrategyId = seoStrategySearchDTO.getSeoStrategyId(); 
 			String strategyDescription = seoStrategySearchDTO.getStrategyDescription(); 
 			String sortBy = seoStrategySearchDTO.getSortBy();
			String sortOrder = seoStrategySearchDTO.getSortOrder();
			String searchQuery = seoStrategySearchDTO.getSearchQuery();
			Integer page = seoStrategySearchDTO.getPage();
			Integer size = seoStrategySearchDTO.getSize();

	        Specification<SeoStrategy> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, seoStrategyId, "seoStrategyId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, strategyDescription, "strategyDescription"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("strategyDescription")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SeoStrategy> seoStrategys = this.getAllSeoStrategys(spec, pageable);
		
		//System.out.println(String.valueOf(seoStrategys.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(seoStrategys.getTotalPages()));
		
		List<SeoStrategy> seoStrategysList = seoStrategys.getContent();
		
		SeoStrategyConvertCriteriaDTO convertCriteria = new SeoStrategyConvertCriteriaDTO();
		List<SeoStrategyDTO> seoStrategyDTOs = this.convertSeoStrategysToSeoStrategyDTOs(seoStrategysList,convertCriteria);
		
		SeoStrategyPageDTO seoStrategyPageDTO = new SeoStrategyPageDTO();
		seoStrategyPageDTO.setSeoStrategys(seoStrategyDTOs);
		seoStrategyPageDTO.setTotalElements(seoStrategys.getTotalElements());
		return ResponseEntity.ok(seoStrategyPageDTO);
	}

	public List<SeoStrategyDTO> convertSeoStrategysToSeoStrategyDTOs(List<SeoStrategy> seoStrategys, SeoStrategyConvertCriteriaDTO convertCriteria) {
		
		List<SeoStrategyDTO> seoStrategyDTOs = new ArrayList<SeoStrategyDTO>();
		
		for (SeoStrategy seoStrategy : seoStrategys) {
			seoStrategyDTOs.add(convertSeoStrategyToSeoStrategyDTO(seoStrategy,convertCriteria));
		}
		
		return seoStrategyDTOs;

	}
	
	public SeoStrategyDTO convertSeoStrategyToSeoStrategyDTO(SeoStrategy seoStrategy, SeoStrategyConvertCriteriaDTO convertCriteria) {
		
		SeoStrategyDTO seoStrategyDTO = new SeoStrategyDTO();
		
		seoStrategyDTO.setSeoStrategyId(seoStrategy.getSeoStrategyId());

	
		seoStrategyDTO.setStrategyDescription(seoStrategy.getStrategyDescription());

	

		
		return seoStrategyDTO;
	}

	public ResultDTO updateSeoStrategy(SeoStrategyDTO seoStrategyDTO, RequestDTO requestDTO) {
		
		SeoStrategy seoStrategy = seoStrategyDao.getById(seoStrategyDTO.getSeoStrategyId());

		seoStrategy.setSeoStrategyId(ControllerUtils.setValue(seoStrategy.getSeoStrategyId(), seoStrategyDTO.getSeoStrategyId()));

		seoStrategy.setStrategyDescription(ControllerUtils.setValue(seoStrategy.getStrategyDescription(), seoStrategyDTO.getStrategyDescription()));



        seoStrategy = seoStrategyDao.save(seoStrategy);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SeoStrategyDTO getSeoStrategyDTOById(Integer seoStrategyId) {
	
		SeoStrategy seoStrategy = seoStrategyDao.getById(seoStrategyId);
			
		
		SeoStrategyConvertCriteriaDTO convertCriteria = new SeoStrategyConvertCriteriaDTO();
		return(this.convertSeoStrategyToSeoStrategyDTO(seoStrategy,convertCriteria));
	}







}
