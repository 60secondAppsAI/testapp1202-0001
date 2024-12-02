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

import com.testapp1202.domain.Agreement;
import com.testapp1202.dto.AgreementDTO;
import com.testapp1202.dto.AgreementSearchDTO;
import com.testapp1202.dto.AgreementPageDTO;
import com.testapp1202.service.AgreementService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/agreement")
@RestController
public class AgreementController {

	private final static Logger logger = LoggerFactory.getLogger(AgreementController.class);

	@Autowired
	AgreementService agreementService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Agreement> getAll() {

		List<Agreement> agreements = agreementService.findAll();
		
		return agreements;	
	}

	@GetMapping(value = "/{agreementId}")
	@ResponseBody
	public AgreementDTO getAgreement(@PathVariable Integer agreementId) {
		
		return (agreementService.getAgreementDTOById(agreementId));
	}

 	@RequestMapping(value = "/addAgreement", method = RequestMethod.POST)
	public ResponseEntity<?> addAgreement(@RequestBody AgreementDTO agreementDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = agreementService.addAgreement(agreementDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/agreements")
	public ResponseEntity<AgreementPageDTO> getAgreements(AgreementSearchDTO agreementSearchDTO) {
 
		return agreementService.getAgreements(agreementSearchDTO);
	}	

	@RequestMapping(value = "/updateAgreement", method = RequestMethod.POST)
	public ResponseEntity<?> updateAgreement(@RequestBody AgreementDTO agreementDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = agreementService.updateAgreement(agreementDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
