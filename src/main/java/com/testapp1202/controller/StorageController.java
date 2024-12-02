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

import com.testapp1202.domain.Storage;
import com.testapp1202.dto.StorageDTO;
import com.testapp1202.dto.StorageSearchDTO;
import com.testapp1202.dto.StoragePageDTO;
import com.testapp1202.service.StorageService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/storage")
@RestController
public class StorageController {

	private final static Logger logger = LoggerFactory.getLogger(StorageController.class);

	@Autowired
	StorageService storageService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Storage> getAll() {

		List<Storage> storages = storageService.findAll();
		
		return storages;	
	}

	@GetMapping(value = "/{storageId}")
	@ResponseBody
	public StorageDTO getStorage(@PathVariable Integer storageId) {
		
		return (storageService.getStorageDTOById(storageId));
	}

 	@RequestMapping(value = "/addStorage", method = RequestMethod.POST)
	public ResponseEntity<?> addStorage(@RequestBody StorageDTO storageDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = storageService.addStorage(storageDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/storages")
	public ResponseEntity<StoragePageDTO> getStorages(StorageSearchDTO storageSearchDTO) {
 
		return storageService.getStorages(storageSearchDTO);
	}	

	@RequestMapping(value = "/updateStorage", method = RequestMethod.POST)
	public ResponseEntity<?> updateStorage(@RequestBody StorageDTO storageDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = storageService.updateStorage(storageDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
