package com.testapp1202.controller;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;
import java.util.ArrayList;


import com.testapp1202.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.Date;

import com.testapp1202.domain.Offer;
import com.testapp1202.dto.OfferDTO;
import com.testapp1202.dto.OfferSearchDTO;
import com.testapp1202.dto.OfferPageDTO;
import com.testapp1202.service.OfferService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/offer")
@RestController
public class OfferController {

	private final static Logger logger = LoggerFactory.getLogger(OfferController.class);

	@Autowired
	OfferService offerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Offer> getAll() {

		List<Offer> offers = offerService.findAll();
		
		return offers;	
	}

	@GetMapping(value = "/{offerId}")
	@ResponseBody
	public OfferDTO getOffer(@PathVariable Integer offerId) {
		
		return (offerService.getOfferDTOById(offerId));
	}

 	@RequestMapping(value = "/addOffer", method = RequestMethod.POST)
	public ResponseEntity<?> addOffer(@RequestBody OfferDTO offerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = offerService.addOffer(offerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/offers")
	public ResponseEntity<OfferPageDTO> getOffers(OfferSearchDTO offerSearchDTO) {
 
		return offerService.getOffers(offerSearchDTO);
	}	

	@RequestMapping(value = "/updateOffer", method = RequestMethod.POST)
	public ResponseEntity<?> updateOffer(@RequestBody OfferDTO offerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = offerService.updateOffer(offerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
