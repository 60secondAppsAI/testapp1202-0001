package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Repayment;
import com.testapp1202.dto.RepaymentDTO;
import com.testapp1202.dto.RepaymentSearchDTO;
import com.testapp1202.dto.RepaymentPageDTO;
import com.testapp1202.dto.RepaymentConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface RepaymentService extends GenericService<Repayment, Integer> {

	List<Repayment> findAll();

	ResultDTO addRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO);

	ResultDTO updateRepayment(RepaymentDTO repaymentDTO, RequestDTO requestDTO);

    Page<Repayment> getAllRepayments(Pageable pageable);

    Page<Repayment> getAllRepayments(Specification<Repayment> spec, Pageable pageable);

	ResponseEntity<RepaymentPageDTO> getRepayments(RepaymentSearchDTO repaymentSearchDTO);
	
	List<RepaymentDTO> convertRepaymentsToRepaymentDTOs(List<Repayment> repayments, RepaymentConvertCriteriaDTO convertCriteria);

	RepaymentDTO getRepaymentDTOById(Integer repaymentId);







}





