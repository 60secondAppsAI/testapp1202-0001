package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.Customer;
import com.testapp1202.dto.CustomerDTO;
import com.testapp1202.dto.CustomerSearchDTO;
import com.testapp1202.dto.CustomerPageDTO;
import com.testapp1202.dto.CustomerConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface CustomerService extends GenericService<Customer, Integer> {

	List<Customer> findAll();

	ResultDTO addCustomer(CustomerDTO customerDTO, RequestDTO requestDTO);

	ResultDTO updateCustomer(CustomerDTO customerDTO, RequestDTO requestDTO);

    Page<Customer> getAllCustomers(Pageable pageable);

    Page<Customer> getAllCustomers(Specification<Customer> spec, Pageable pageable);

	ResponseEntity<CustomerPageDTO> getCustomers(CustomerSearchDTO customerSearchDTO);
	
	List<CustomerDTO> convertCustomersToCustomerDTOs(List<Customer> customers, CustomerConvertCriteriaDTO convertCriteria);

	CustomerDTO getCustomerDTOById(Integer customerId);







}





