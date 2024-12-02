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
import com.testapp1202.dao.AgreementDAO;
import com.testapp1202.domain.Agreement;
import com.testapp1202.dto.AgreementDTO;
import com.testapp1202.dto.AgreementSearchDTO;
import com.testapp1202.dto.AgreementPageDTO;
import com.testapp1202.dto.AgreementConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.AgreementService;
import com.testapp1202.util.ControllerUtils;





@Service
public class AgreementServiceImpl extends GenericServiceImpl<Agreement, Integer> implements AgreementService {

    private final static Logger logger = LoggerFactory.getLogger(AgreementServiceImpl.class);

	@Autowired
	AgreementDAO agreementDao;

	


	@Override
	public GenericDAO<Agreement, Integer> getDAO() {
		return (GenericDAO<Agreement, Integer>) agreementDao;
	}
	
	public List<Agreement> findAll () {
		List<Agreement> agreements = agreementDao.findAll();
		
		return agreements;	
		
	}

	public ResultDTO addAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO) {

		Agreement agreement = new Agreement();

		agreement.setAgreementId(agreementDTO.getAgreementId());


		agreement.setSignedDate(agreementDTO.getSignedDate());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		agreement = agreementDao.save(agreement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Agreement> getAllAgreements(Pageable pageable) {
		return agreementDao.findAll(pageable);
	}

	public Page<Agreement> getAllAgreements(Specification<Agreement> spec, Pageable pageable) {
		return agreementDao.findAll(spec, pageable);
	}

	public ResponseEntity<AgreementPageDTO> getAgreements(AgreementSearchDTO agreementSearchDTO) {
	
			Integer agreementId = agreementSearchDTO.getAgreementId(); 
   			String sortBy = agreementSearchDTO.getSortBy();
			String sortOrder = agreementSearchDTO.getSortOrder();
			String searchQuery = agreementSearchDTO.getSearchQuery();
			Integer page = agreementSearchDTO.getPage();
			Integer size = agreementSearchDTO.getSize();

	        Specification<Agreement> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, agreementId, "agreementId"); 
			
 			

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

		Page<Agreement> agreements = this.getAllAgreements(spec, pageable);
		
		//System.out.println(String.valueOf(agreements.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(agreements.getTotalPages()));
		
		List<Agreement> agreementsList = agreements.getContent();
		
		AgreementConvertCriteriaDTO convertCriteria = new AgreementConvertCriteriaDTO();
		List<AgreementDTO> agreementDTOs = this.convertAgreementsToAgreementDTOs(agreementsList,convertCriteria);
		
		AgreementPageDTO agreementPageDTO = new AgreementPageDTO();
		agreementPageDTO.setAgreements(agreementDTOs);
		agreementPageDTO.setTotalElements(agreements.getTotalElements());
		return ResponseEntity.ok(agreementPageDTO);
	}

	public List<AgreementDTO> convertAgreementsToAgreementDTOs(List<Agreement> agreements, AgreementConvertCriteriaDTO convertCriteria) {
		
		List<AgreementDTO> agreementDTOs = new ArrayList<AgreementDTO>();
		
		for (Agreement agreement : agreements) {
			agreementDTOs.add(convertAgreementToAgreementDTO(agreement,convertCriteria));
		}
		
		return agreementDTOs;

	}
	
	public AgreementDTO convertAgreementToAgreementDTO(Agreement agreement, AgreementConvertCriteriaDTO convertCriteria) {
		
		AgreementDTO agreementDTO = new AgreementDTO();
		
		agreementDTO.setAgreementId(agreement.getAgreementId());

	
		agreementDTO.setSignedDate(agreement.getSignedDate());

	

		
		return agreementDTO;
	}

	public ResultDTO updateAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO) {
		
		Agreement agreement = agreementDao.getById(agreementDTO.getAgreementId());

		agreement.setAgreementId(ControllerUtils.setValue(agreement.getAgreementId(), agreementDTO.getAgreementId()));

		agreement.setSignedDate(ControllerUtils.setValue(agreement.getSignedDate(), agreementDTO.getSignedDate()));



        agreement = agreementDao.save(agreement);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public AgreementDTO getAgreementDTOById(Integer agreementId) {
	
		Agreement agreement = agreementDao.getById(agreementId);
			
		
		AgreementConvertCriteriaDTO convertCriteria = new AgreementConvertCriteriaDTO();
		return(this.convertAgreementToAgreementDTO(agreement,convertCriteria));
	}







}
