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

import com.testapp1202.domain.Customer;
import com.testapp1202.dto.CustomerDTO;
import com.testapp1202.dto.CustomerSearchDTO;
import com.testapp1202.dto.CustomerPageDTO;
import com.testapp1202.service.CustomerService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.multipart.MultipartFile;




@CrossOrigin(origins = "*")
@RequestMapping("/customer")
@RestController
public class CustomerController {

	private final static Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	CustomerService customerService;



	@RequestMapping(value="/", method = RequestMethod.GET)
	public List<Customer> getAll() {

		List<Customer> customers = customerService.findAll();
		
		return customers;	
	}

	@GetMapping(value = "/{customerId}")
	@ResponseBody
	public CustomerDTO getCustomer(@PathVariable Integer customerId) {
		
		return (customerService.getCustomerDTOById(customerId));
	}

 	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> addCustomer(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {

		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerService.addCustomer(customerDTO, requestDTO);
		
		return result.asResponseEntity();
	}

	@GetMapping("/customers")
	public ResponseEntity<CustomerPageDTO> getCustomers(CustomerSearchDTO customerSearchDTO) {
 
		return customerService.getCustomers(customerSearchDTO);
	}	

	@RequestMapping(value = "/updateCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> updateCustomer(@RequestBody CustomerDTO customerDTO, HttpServletRequest request) {
		RequestDTO requestDTO = new RequestDTO(request);
		ResultDTO result = customerService.updateCustomer(customerDTO, requestDTO);
		
//		if (result.isSuccessful()) {
//		}

		return result.asResponseEntity();
	}



}
