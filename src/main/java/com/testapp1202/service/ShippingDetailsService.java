package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.ShippingDetails;
import com.testapp1202.dto.ShippingDetailsDTO;
import com.testapp1202.dto.ShippingDetailsSearchDTO;
import com.testapp1202.dto.ShippingDetailsPageDTO;
import com.testapp1202.dto.ShippingDetailsConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface ShippingDetailsService extends GenericService<ShippingDetails, Integer> {

	List<ShippingDetails> findAll();

	ResultDTO addShippingDetails(ShippingDetailsDTO shippingDetailsDTO, RequestDTO requestDTO);

	ResultDTO updateShippingDetails(ShippingDetailsDTO shippingDetailsDTO, RequestDTO requestDTO);

    Page<ShippingDetails> getAllShippingDetailss(Pageable pageable);

    Page<ShippingDetails> getAllShippingDetailss(Specification<ShippingDetails> spec, Pageable pageable);

	ResponseEntity<ShippingDetailsPageDTO> getShippingDetailss(ShippingDetailsSearchDTO shippingDetailsSearchDTO);
	
	List<ShippingDetailsDTO> convertShippingDetailssToShippingDetailsDTOs(List<ShippingDetails> shippingDetailss, ShippingDetailsConvertCriteriaDTO convertCriteria);

	ShippingDetailsDTO getShippingDetailsDTOById(Integer shippingDetailsId);







}





