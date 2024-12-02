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

import com.testapp1202.domain.EmailNotification;
import com.testapp1202.dto.EmailNotificationDTO;
import com.testapp1202.dto.EmailNotificationSearchDTO;
import com.testapp1202.dto.EmailNotificationPageDTO;
import com.testapp1202.service.EmailNotificationService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/emailNotification")
@RestController
public class EmailNotificationController {

	private final static Logger logger = LoggerFactory.getLogger(EmailNotificationController.class);

	@Autowired
	EmailNotificationService emailNotificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<EmailNotification> getAll() {

		List<EmailNotification> emailNotifications = emailNotificationService.findAll();
		
		return emailNotifications;	
	}

	@GetMapping(value = "/{emailNotificationId}")
	@ResponseBody
	public EmailNotificationDTO getEmailNotification(@PathVariable Integer emailNotificationId) {
		
		return (emailNotificationService.getEmailNotificationDTOById(emailNotificationId));
	}

 	@RequestMapping(value = "/addEmailNotification", method = RequestMethod.POST)
	public ResponseEntity<?> addEmailNotification(@RequestBody EmailNotificationDTO emailNotificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = emailNotificationService.addEmailNotification(emailNotificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/emailNotifications")
	public ResponseEntity<EmailNotificationPageDTO> getEmailNotifications(EmailNotificationSearchDTO emailNotificationSearchDTO) {
 
		return emailNotificationService.getEmailNotifications(emailNotificationSearchDTO);
	}	

	@RequestMapping(value = "/updateEmailNotification", method = RequestMethod.POST)
	public ResponseEntity<?> updateEmailNotification(@RequestBody EmailNotificationDTO emailNotificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = emailNotificationService.updateEmailNotification(emailNotificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
