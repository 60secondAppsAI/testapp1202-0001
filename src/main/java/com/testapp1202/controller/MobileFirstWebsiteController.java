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

import com.testapp1202.domain.MobileFirstWebsite;
import com.testapp1202.dto.MobileFirstWebsiteDTO;
import com.testapp1202.dto.MobileFirstWebsiteSearchDTO;
import com.testapp1202.dto.MobileFirstWebsitePageDTO;
import com.testapp1202.service.MobileFirstWebsiteService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/mobileFirstWebsite")
@RestController
public class MobileFirstWebsiteController {

	private final static Logger logger = LoggerFactory.getLogger(MobileFirstWebsiteController.class);

	@Autowired
	MobileFirstWebsiteService mobileFirstWebsiteService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<MobileFirstWebsite> getAll() {

		List<MobileFirstWebsite> mobileFirstWebsites = mobileFirstWebsiteService.findAll();
		
		return mobileFirstWebsites;	
	}

	@GetMapping(value = "/{mobileFirstWebsiteId}")
	@ResponseBody
	public MobileFirstWebsiteDTO getMobileFirstWebsite(@PathVariable Integer mobileFirstWebsiteId) {
		
		return (mobileFirstWebsiteService.getMobileFirstWebsiteDTOById(mobileFirstWebsiteId));
	}

 	@RequestMapping(value = "/addMobileFirstWebsite", method = RequestMethod.POST)
	public ResponseEntity<?> addMobileFirstWebsite(@RequestBody MobileFirstWebsiteDTO mobileFirstWebsiteDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mobileFirstWebsiteService.addMobileFirstWebsite(mobileFirstWebsiteDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/mobileFirstWebsites")
	public ResponseEntity<MobileFirstWebsitePageDTO> getMobileFirstWebsites(MobileFirstWebsiteSearchDTO mobileFirstWebsiteSearchDTO) {
 
		return mobileFirstWebsiteService.getMobileFirstWebsites(mobileFirstWebsiteSearchDTO);
	}	

	@RequestMapping(value = "/updateMobileFirstWebsite", method = RequestMethod.POST)
	public ResponseEntity<?> updateMobileFirstWebsite(@RequestBody MobileFirstWebsiteDTO mobileFirstWebsiteDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = mobileFirstWebsiteService.updateMobileFirstWebsite(mobileFirstWebsiteDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
