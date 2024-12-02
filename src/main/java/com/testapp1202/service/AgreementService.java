package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Agreement;
import com.testapp1202.dto.AgreementDTO;
import com.testapp1202.dto.AgreementSearchDTO;
import com.testapp1202.dto.AgreementPageDTO;
import com.testapp1202.dto.AgreementConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface AgreementService extends GenericService<Agreement, Integer> {

	List<Agreement> findAll();

	ResultDTO addAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO);

	ResultDTO updateAgreement(AgreementDTO agreementDTO, RequestDTO requestDTO);

    Page<Agreement> getAllAgreements(Pageable pageable);

    Page<Agreement> getAllAgreements(Specification<Agreement> spec, Pageable pageable);

	ResponseEntity<AgreementPageDTO> getAgreements(AgreementSearchDTO agreementSearchDTO);
	
	List<AgreementDTO> convertAgreementsToAgreementDTOs(List<Agreement> agreements, AgreementConvertCriteriaDTO convertCriteria);

	AgreementDTO getAgreementDTOById(Integer agreementId);







}





