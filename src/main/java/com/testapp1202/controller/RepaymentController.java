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

import com.testapp1202.domain.Repayment;
import com.testapp1202.dto.RepaymentDTO;
import com.testapp1202.dto.RepaymentSearchDTO;
import com.testapp1202.dto.RepaymentPageDTO;
import com.testapp1202.service.RepaymentService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/repayment")
@RestController
public class RepaymentController {

	private final static Logger logger = LoggerFactory.getLogger(RepaymentController.class);

	@Autowired
	RepaymentService repaymentService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Repayment> getAll() {

		List<Repayment> repayments = repaymentService.findAll();
		
		return repayments;	
	}

	@GetMapping(value = "/{repaymentId}")
	@ResponseBody
	public RepaymentDTO getRepayment(@PathVariable Integer repaymentId) {
		
		return (repaymentService.getRepaymentDTOById(repaymentId));
	}

 	@RequestMapping(value = "/addRepayment", method = RequestMethod.POST)
	public ResponseEntity<?> addRepayment(@RequestBody RepaymentDTO repaymentDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = repaymentService.addRepayment(repaymentDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/repayments")
	public ResponseEntity<RepaymentPageDTO> getRepayments(RepaymentSearchDTO repaymentSearchDTO) {
 
		return repaymentService.getRepayments(repaymentSearchDTO);
	}	

	@RequestMapping(value = "/updateRepayment", method = RequestMethod.POST)
	public ResponseEntity<?> updateRepayment(@RequestBody RepaymentDTO repaymentDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = repaymentService.updateRepayment(repaymentDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
