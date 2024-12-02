package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Offer;
import com.testapp1202.dto.OfferDTO;
import com.testapp1202.dto.OfferSearchDTO;
import com.testapp1202.dto.OfferPageDTO;
import com.testapp1202.dto.OfferConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface OfferService extends GenericService<Offer, Integer> {

	List<Offer> findAll();

	ResultDTO addOffer(OfferDTO offerDTO, RequestDTO requestDTO);

	ResultDTO updateOffer(OfferDTO offerDTO, RequestDTO requestDTO);

    Page<Offer> getAllOffers(Pageable pageable);

    Page<Offer> getAllOffers(Specification<Offer> spec, Pageable pageable);

	ResponseEntity<OfferPageDTO> getOffers(OfferSearchDTO offerSearchDTO);
	
	List<OfferDTO> convertOffersToOfferDTOs(List<Offer> offers, OfferConvertCriteriaDTO convertCriteria);

	OfferDTO getOfferDTOById(Integer offerId);







}





