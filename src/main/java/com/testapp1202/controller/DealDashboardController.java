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

import com.testapp1202.domain.DealDashboard;
import com.testapp1202.dto.DealDashboardDTO;
import com.testapp1202.dto.DealDashboardSearchDTO;
import com.testapp1202.dto.DealDashboardPageDTO;
import com.testapp1202.service.DealDashboardService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/dealDashboard")
@RestController
public class DealDashboardController {

	private final static Logger logger = LoggerFactory.getLogger(DealDashboardController.class);

	@Autowired
	DealDashboardService dealDashboardService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DealDashboard> getAll() {

		List<DealDashboard> dealDashboards = dealDashboardService.findAll();
		
		return dealDashboards;	
	}

	@GetMapping(value = "/{dealDashboardId}")
	@ResponseBody
	public DealDashboardDTO getDealDashboard(@PathVariable Integer dealDashboardId) {
		
		return (dealDashboardService.getDealDashboardDTOById(dealDashboardId));
	}

 	@RequestMapping(value = "/addDealDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> addDealDashboard(@RequestBody DealDashboardDTO dealDashboardDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealDashboardService.addDealDashboard(dealDashboardDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/dealDashboards")
	public ResponseEntity<DealDashboardPageDTO> getDealDashboards(DealDashboardSearchDTO dealDashboardSearchDTO) {
 
		return dealDashboardService.getDealDashboards(dealDashboardSearchDTO);
	}	

	@RequestMapping(value = "/updateDealDashboard", method = RequestMethod.POST)
	public ResponseEntity<?> updateDealDashboard(@RequestBody DealDashboardDTO dealDashboardDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = dealDashboardService.updateDealDashboard(dealDashboardDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
