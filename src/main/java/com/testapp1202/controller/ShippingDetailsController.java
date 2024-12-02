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

import com.testapp1202.domain.ShippingDetails;
import com.testapp1202.dto.ShippingDetailsDTO;
import com.testapp1202.dto.ShippingDetailsSearchDTO;
import com.testapp1202.dto.ShippingDetailsPageDTO;
import com.testapp1202.service.ShippingDetailsService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/shippingDetails")
@RestController
public class ShippingDetailsController {

	private final static Logger logger = LoggerFactory.getLogger(ShippingDetailsController.class);

	@Autowired
	ShippingDetailsService shippingDetailsService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<ShippingDetails> getAll() {

		List<ShippingDetails> shippingDetailss = shippingDetailsService.findAll();
		
		return shippingDetailss;	
	}

	@GetMapping(value = "/{shippingDetailsId}")
	@ResponseBody
	public ShippingDetailsDTO getShippingDetails(@PathVariable Integer shippingDetailsId) {
		
		return (shippingDetailsService.getShippingDetailsDTOById(shippingDetailsId));
	}

 	@RequestMapping(value = "/addShippingDetails", method = RequestMethod.POST)
	public ResponseEntity<?> addShippingDetails(@RequestBody ShippingDetailsDTO shippingDetailsDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingDetailsService.addShippingDetails(shippingDetailsDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/shippingDetailss")
	public ResponseEntity<ShippingDetailsPageDTO> getShippingDetailss(ShippingDetailsSearchDTO shippingDetailsSearchDTO) {
 
		return shippingDetailsService.getShippingDetailss(shippingDetailsSearchDTO);
	}	

	@RequestMapping(value = "/updateShippingDetails", method = RequestMethod.POST)
	public ResponseEntity<?> updateShippingDetails(@RequestBody ShippingDetailsDTO shippingDetailsDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = shippingDetailsService.updateShippingDetails(shippingDetailsDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
