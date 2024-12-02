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

import com.testapp1202.domain.SmsNotification;
import com.testapp1202.dto.SmsNotificationDTO;
import com.testapp1202.dto.SmsNotificationSearchDTO;
import com.testapp1202.dto.SmsNotificationPageDTO;
import com.testapp1202.service.SmsNotificationService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/smsNotification")
@RestController
public class SmsNotificationController {

	private final static Logger logger = LoggerFactory.getLogger(SmsNotificationController.class);

	@Autowired
	SmsNotificationService smsNotificationService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<SmsNotification> getAll() {

		List<SmsNotification> smsNotifications = smsNotificationService.findAll();
		
		return smsNotifications;	
	}

	@GetMapping(value = "/{smsNotificationId}")
	@ResponseBody
	public SmsNotificationDTO getSmsNotification(@PathVariable Integer smsNotificationId) {
		
		return (smsNotificationService.getSmsNotificationDTOById(smsNotificationId));
	}

 	@RequestMapping(value = "/addSmsNotification", method = RequestMethod.POST)
	public ResponseEntity<?> addSmsNotification(@RequestBody SmsNotificationDTO smsNotificationDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = smsNotificationService.addSmsNotification(smsNotificationDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/smsNotifications")
	public ResponseEntity<SmsNotificationPageDTO> getSmsNotifications(SmsNotificationSearchDTO smsNotificationSearchDTO) {
 
		return smsNotificationService.getSmsNotifications(smsNotificationSearchDTO);
	}	

	@RequestMapping(value = "/updateSmsNotification", method = RequestMethod.POST)
	public ResponseEntity<?> updateSmsNotification(@RequestBody SmsNotificationDTO smsNotificationDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = smsNotificationService.updateSmsNotification(smsNotificationDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
