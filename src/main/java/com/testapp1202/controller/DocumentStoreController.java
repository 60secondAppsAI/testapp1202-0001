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

import com.testapp1202.domain.DocumentStore;
import com.testapp1202.dto.DocumentStoreDTO;
import com.testapp1202.dto.DocumentStoreSearchDTO;
import com.testapp1202.dto.DocumentStorePageDTO;
import com.testapp1202.service.DocumentStoreService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/documentStore")
@RestController
public class DocumentStoreController {

	private final static Logger logger = LoggerFactory.getLogger(DocumentStoreController.class);

	@Autowired
	DocumentStoreService documentStoreService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<DocumentStore> getAll() {

		List<DocumentStore> documentStores = documentStoreService.findAll();
		
		return documentStores;	
	}

	@GetMapping(value = "/{documentStoreId}")
	@ResponseBody
	public DocumentStoreDTO getDocumentStore(@PathVariable Integer documentStoreId) {
		
		return (documentStoreService.getDocumentStoreDTOById(documentStoreId));
	}

 	@RequestMapping(value = "/addDocumentStore", method = RequestMethod.POST)
	public ResponseEntity<?> addDocumentStore(@RequestBody DocumentStoreDTO documentStoreDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = documentStoreService.addDocumentStore(documentStoreDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/documentStores")
	public ResponseEntity<DocumentStorePageDTO> getDocumentStores(DocumentStoreSearchDTO documentStoreSearchDTO) {
 
		return documentStoreService.getDocumentStores(documentStoreSearchDTO);
	}	

	@RequestMapping(value = "/updateDocumentStore", method = RequestMethod.POST)
	public ResponseEntity<?> updateDocumentStore(@RequestBody DocumentStoreDTO documentStoreDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = documentStoreService.updateDocumentStore(documentStoreDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
