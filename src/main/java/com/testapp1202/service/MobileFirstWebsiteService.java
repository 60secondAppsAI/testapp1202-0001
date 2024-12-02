package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.MobileFirstWebsite;
import com.testapp1202.dto.MobileFirstWebsiteDTO;
import com.testapp1202.dto.MobileFirstWebsiteSearchDTO;
import com.testapp1202.dto.MobileFirstWebsitePageDTO;
import com.testapp1202.dto.MobileFirstWebsiteConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface MobileFirstWebsiteService extends GenericService<MobileFirstWebsite, Integer> {

	List<MobileFirstWebsite> findAll();

	ResultDTO addMobileFirstWebsite(MobileFirstWebsiteDTO mobileFirstWebsiteDTO, RequestDTO requestDTO);

	ResultDTO updateMobileFirstWebsite(MobileFirstWebsiteDTO mobileFirstWebsiteDTO, RequestDTO requestDTO);

    Page<MobileFirstWebsite> getAllMobileFirstWebsites(Pageable pageable);

    Page<MobileFirstWebsite> getAllMobileFirstWebsites(Specification<MobileFirstWebsite> spec, Pageable pageable);

	ResponseEntity<MobileFirstWebsitePageDTO> getMobileFirstWebsites(MobileFirstWebsiteSearchDTO mobileFirstWebsiteSearchDTO);
	
	List<MobileFirstWebsiteDTO> convertMobileFirstWebsitesToMobileFirstWebsiteDTOs(List<MobileFirstWebsite> mobileFirstWebsites, MobileFirstWebsiteConvertCriteriaDTO convertCriteria);

	MobileFirstWebsiteDTO getMobileFirstWebsiteDTOById(Integer mobileFirstWebsiteId);







}





