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

import com.testapp1202.domain.SeoStrategy;
import com.testapp1202.dto.SeoStrategyDTO;
import com.testapp1202.dto.SeoStrategySearchDTO;
import com.testapp1202.dto.SeoStrategyPageDTO;
import com.testapp1202.service.SeoStrategyService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/seoStrategy")
@RestController
public class SeoStrategyController {

	private final static Logger logger = LoggerFactory.getLogger(SeoStrategyController.class);

	@Autowired
	SeoStrategyService seoStrategyService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SeoStrategy> getAll() {

		List<SeoStrategy> seoStrategys = seoStrategyService.findAll();
		
		return seoStrategys;	
	}

	@GetMapping(value = "/{seoStrategyId}")
	@ResponseBody
	public SeoStrategyDTO getSeoStrategy(@PathVariable Integer seoStrategyId) {
		
		return (seoStrategyService.getSeoStrategyDTOById(seoStrategyId));
	}

 	@RequestMapping(value = "/addSeoStrategy", method = RequestMethod.POST)
	public ResponseEntity<?> addSeoStrategy(@RequestBody SeoStrategyDTO seoStrategyDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seoStrategyService.addSeoStrategy(seoStrategyDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/seoStrategys")
	public ResponseEntity<SeoStrategyPageDTO> getSeoStrategys(SeoStrategySearchDTO seoStrategySearchDTO) {
 
		return seoStrategyService.getSeoStrategys(seoStrategySearchDTO);
	}	

	@RequestMapping(value = "/updateSeoStrategy", method = RequestMethod.POST)
	public ResponseEntity<?> updateSeoStrategy(@RequestBody SeoStrategyDTO seoStrategyDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = seoStrategyService.updateSeoStrategy(seoStrategyDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
