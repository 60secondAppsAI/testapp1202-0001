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

import com.testapp1202.domain.Deal;
import com.testapp1202.dto.DealDTO;
import com.testapp1202.dto.DealSearchDTO;
import com.testapp1202.dto.DealPageDTO;
import com.testapp1202.service.DealService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/deal")
@RestController
public class DealController {

	private final static Logger logger = LoggerFactory.getLogger(DealController.class);

	@Autowired
	DealService dealService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Deal> getAll() {

		List<Deal> deals = dealService.findAll();
		
		return deals;	
	}

	@GetMapping(value = "/{dealId}")
	@ResponseBody
	public DealDTO getDeal(@PathVariable Integer dealId) {
		
		return (dealService.getDealDTOById(dealId));
	}

 	@RequestMapping(value = "/addDeal", method = RequestMethod.POST)
	public ResponseEntity<?> addDeal(@RequestBody DealDTO dealDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealService.addDeal(dealDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/deals")
	public ResponseEntity<DealPageDTO> getDeals(DealSearchDTO dealSearchDTO) {
 
		return dealService.getDeals(dealSearchDTO);
	}	

	@RequestMapping(value = "/updateDeal", method = RequestMethod.POST)
	public ResponseEntity<?> updateDeal(@RequestBody DealDTO dealDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealService.updateDeal(dealDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
