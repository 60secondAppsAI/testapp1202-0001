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

import com.testapp1202.domain.PreliminaryDiscussion;
import com.testapp1202.dto.PreliminaryDiscussionDTO;
import com.testapp1202.dto.PreliminaryDiscussionSearchDTO;
import com.testapp1202.dto.PreliminaryDiscussionPageDTO;
import com.testapp1202.service.PreliminaryDiscussionService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/preliminaryDiscussion")
@RestController
public class PreliminaryDiscussionController {

	private final static Logger logger = LoggerFactory.getLogger(PreliminaryDiscussionController.class);

	@Autowired
	PreliminaryDiscussionService preliminaryDiscussionService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<PreliminaryDiscussion> getAll() {

		List<PreliminaryDiscussion> preliminaryDiscussions = preliminaryDiscussionService.findAll();
		
		return preliminaryDiscussions;	
	}

	@GetMapping(value = "/{preliminaryDiscussionId}")
	@ResponseBody
	public PreliminaryDiscussionDTO getPreliminaryDiscussion(@PathVariable Integer preliminaryDiscussionId) {
		
		return (preliminaryDiscussionService.getPreliminaryDiscussionDTOById(preliminaryDiscussionId));
	}

 	@RequestMapping(value = "/addPreliminaryDiscussion", method = RequestMethod.POST)
	public ResponseEntity<?> addPreliminaryDiscussion(@RequestBody PreliminaryDiscussionDTO preliminaryDiscussionDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preliminaryDiscussionService.addPreliminaryDiscussion(preliminaryDiscussionDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/preliminaryDiscussions")
	public ResponseEntity<PreliminaryDiscussionPageDTO> getPreliminaryDiscussions(PreliminaryDiscussionSearchDTO preliminaryDiscussionSearchDTO) {
 
		return preliminaryDiscussionService.getPreliminaryDiscussions(preliminaryDiscussionSearchDTO);
	}	

	@RequestMapping(value = "/updatePreliminaryDiscussion", method = RequestMethod.POST)
	public ResponseEntity<?> updatePreliminaryDiscussion(@RequestBody PreliminaryDiscussionDTO preliminaryDiscussionDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = preliminaryDiscussionService.updatePreliminaryDiscussion(preliminaryDiscussionDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
