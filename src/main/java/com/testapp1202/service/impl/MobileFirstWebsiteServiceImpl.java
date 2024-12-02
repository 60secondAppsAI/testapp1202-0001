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
import com.testapp1202.dao.MobileFirstWebsiteDAO;
import com.testapp1202.domain.MobileFirstWebsite;
import com.testapp1202.dto.MobileFirstWebsiteDTO;
import com.testapp1202.dto.MobileFirstWebsiteSearchDTO;
import com.testapp1202.dto.MobileFirstWebsitePageDTO;
import com.testapp1202.dto.MobileFirstWebsiteConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.MobileFirstWebsiteService;
import com.testapp1202.util.ControllerUtils;





@Service
public class MobileFirstWebsiteServiceImpl extends GenericServiceImpl<MobileFirstWebsite, Integer> implements MobileFirstWebsiteService {

    private final static Logger logger = LoggerFactory.getLogger(MobileFirstWebsiteServiceImpl.class);

	@Autowired
	MobileFirstWebsiteDAO mobileFirstWebsiteDao;

	


	@Override
	public GenericDAO<MobileFirstWebsite, Integer> getDAO() {
		return (GenericDAO<MobileFirstWebsite, Integer>) mobileFirstWebsiteDao;
	}
	
	public List<MobileFirstWebsite> findAll () {
		List<MobileFirstWebsite> mobileFirstWebsites = mobileFirstWebsiteDao.findAll();
		
		return mobileFirstWebsites;	
		
	}

	public ResultDTO addMobileFirstWebsite(MobileFirstWebsiteDTO mobileFirstWebsiteDTO, RequestDTO requestDTO) {

		MobileFirstWebsite mobileFirstWebsite = new MobileFirstWebsite();

		mobileFirstWebsite.setMobileFirstWebsiteId(mobileFirstWebsiteDTO.getMobileFirstWebsiteId());


		mobileFirstWebsite.setWebsiteUrl(mobileFirstWebsiteDTO.getWebsiteUrl());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		mobileFirstWebsite = mobileFirstWebsiteDao.save(mobileFirstWebsite);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<MobileFirstWebsite> getAllMobileFirstWebsites(Pageable pageable) {
		return mobileFirstWebsiteDao.findAll(pageable);
	}

	public Page<MobileFirstWebsite> getAllMobileFirstWebsites(Specification<MobileFirstWebsite> spec, Pageable pageable) {
		return mobileFirstWebsiteDao.findAll(spec, pageable);
	}

	public ResponseEntity<MobileFirstWebsitePageDTO> getMobileFirstWebsites(MobileFirstWebsiteSearchDTO mobileFirstWebsiteSearchDTO) {
	
			Integer mobileFirstWebsiteId = mobileFirstWebsiteSearchDTO.getMobileFirstWebsiteId(); 
 			String websiteUrl = mobileFirstWebsiteSearchDTO.getWebsiteUrl(); 
 			String sortBy = mobileFirstWebsiteSearchDTO.getSortBy();
			String sortOrder = mobileFirstWebsiteSearchDTO.getSortOrder();
			String searchQuery = mobileFirstWebsiteSearchDTO.getSearchQuery();
			Integer page = mobileFirstWebsiteSearchDTO.getPage();
			Integer size = mobileFirstWebsiteSearchDTO.getSize();

	        Specification<MobileFirstWebsite> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, mobileFirstWebsiteId, "mobileFirstWebsiteId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, websiteUrl, "websiteUrl"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("websiteUrl")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<MobileFirstWebsite> mobileFirstWebsites = this.getAllMobileFirstWebsites(spec, pageable);
		
		//System.out.println(String.valueOf(mobileFirstWebsites.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(mobileFirstWebsites.getTotalPages()));
		
		List<MobileFirstWebsite> mobileFirstWebsitesList = mobileFirstWebsites.getContent();
		
		MobileFirstWebsiteConvertCriteriaDTO convertCriteria = new MobileFirstWebsiteConvertCriteriaDTO();
		List<MobileFirstWebsiteDTO> mobileFirstWebsiteDTOs = this.convertMobileFirstWebsitesToMobileFirstWebsiteDTOs(mobileFirstWebsitesList,convertCriteria);
		
		MobileFirstWebsitePageDTO mobileFirstWebsitePageDTO = new MobileFirstWebsitePageDTO();
		mobileFirstWebsitePageDTO.setMobileFirstWebsites(mobileFirstWebsiteDTOs);
		mobileFirstWebsitePageDTO.setTotalElements(mobileFirstWebsites.getTotalElements());
		return ResponseEntity.ok(mobileFirstWebsitePageDTO);
	}

	public List<MobileFirstWebsiteDTO> convertMobileFirstWebsitesToMobileFirstWebsiteDTOs(List<MobileFirstWebsite> mobileFirstWebsites, MobileFirstWebsiteConvertCriteriaDTO convertCriteria) {
		
		List<MobileFirstWebsiteDTO> mobileFirstWebsiteDTOs = new ArrayList<MobileFirstWebsiteDTO>();
		
		for (MobileFirstWebsite mobileFirstWebsite : mobileFirstWebsites) {
			mobileFirstWebsiteDTOs.add(convertMobileFirstWebsiteToMobileFirstWebsiteDTO(mobileFirstWebsite,convertCriteria));
		}
		
		return mobileFirstWebsiteDTOs;

	}
	
	public MobileFirstWebsiteDTO convertMobileFirstWebsiteToMobileFirstWebsiteDTO(MobileFirstWebsite mobileFirstWebsite, MobileFirstWebsiteConvertCriteriaDTO convertCriteria) {
		
		MobileFirstWebsiteDTO mobileFirstWebsiteDTO = new MobileFirstWebsiteDTO();
		
		mobileFirstWebsiteDTO.setMobileFirstWebsiteId(mobileFirstWebsite.getMobileFirstWebsiteId());

	
		mobileFirstWebsiteDTO.setWebsiteUrl(mobileFirstWebsite.getWebsiteUrl());

	

		
		return mobileFirstWebsiteDTO;
	}

	public ResultDTO updateMobileFirstWebsite(MobileFirstWebsiteDTO mobileFirstWebsiteDTO, RequestDTO requestDTO) {
		
		MobileFirstWebsite mobileFirstWebsite = mobileFirstWebsiteDao.getById(mobileFirstWebsiteDTO.getMobileFirstWebsiteId());

		mobileFirstWebsite.setMobileFirstWebsiteId(ControllerUtils.setValue(mobileFirstWebsite.getMobileFirstWebsiteId(), mobileFirstWebsiteDTO.getMobileFirstWebsiteId()));

		mobileFirstWebsite.setWebsiteUrl(ControllerUtils.setValue(mobileFirstWebsite.getWebsiteUrl(), mobileFirstWebsiteDTO.getWebsiteUrl()));



        mobileFirstWebsite = mobileFirstWebsiteDao.save(mobileFirstWebsite);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public MobileFirstWebsiteDTO getMobileFirstWebsiteDTOById(Integer mobileFirstWebsiteId) {
	
		MobileFirstWebsite mobileFirstWebsite = mobileFirstWebsiteDao.getById(mobileFirstWebsiteId);
			
		
		MobileFirstWebsiteConvertCriteriaDTO convertCriteria = new MobileFirstWebsiteConvertCriteriaDTO();
		return(this.convertMobileFirstWebsiteToMobileFirstWebsiteDTO(mobileFirstWebsite,convertCriteria));
	}







}
