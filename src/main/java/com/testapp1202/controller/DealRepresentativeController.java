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

import com.testapp1202.domain.DealRepresentative;
import com.testapp1202.dto.DealRepresentativeDTO;
import com.testapp1202.dto.DealRepresentativeSearchDTO;
import com.testapp1202.dto.DealRepresentativePageDTO;
import com.testapp1202.service.DealRepresentativeService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/dealRepresentative")
@RestController
public class DealRepresentativeController {

	private final static Logger logger = LoggerFactory.getLogger(DealRepresentativeController.class);

	@Autowired
	DealRepresentativeService dealRepresentativeService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DealRepresentative> getAll() {

		List<DealRepresentative> dealRepresentatives = dealRepresentativeService.findAll();
		
		return dealRepresentatives;	
	}

	@GetMapping(value = "/{dealRepresentativeId}")
	@ResponseBody
	public DealRepresentativeDTO getDealRepresentative(@PathVariable Integer dealRepresentativeId) {
		
		return (dealRepresentativeService.getDealRepresentativeDTOById(dealRepresentativeId));
	}

 	@RequestMapping(value = "/addDealRepresentative", method = RequestMethod.POST)
	public ResponseEntity<?> addDealRepresentative(@RequestBody DealRepresentativeDTO dealRepresentativeDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealRepresentativeService.addDealRepresentative(dealRepresentativeDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/dealRepresentatives")
	public ResponseEntity<DealRepresentativePageDTO> getDealRepresentatives(DealRepresentativeSearchDTO dealRepresentativeSearchDTO) {
 
		return dealRepresentativeService.getDealRepresentatives(dealRepresentativeSearchDTO);
	}	

	@RequestMapping(value = "/updateDealRepresentative", method = RequestMethod.POST)
	public ResponseEntity<?> updateDealRepresentative(@RequestBody DealRepresentativeDTO dealRepresentativeDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealRepresentativeService.updateDealRepresentative(dealRepresentativeDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
