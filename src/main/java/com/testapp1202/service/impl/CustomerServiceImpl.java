package com.testapp1202.service.impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;



import com.testapp1202.dao.GenericDAO;
import com.testapp1202.service.GenericService;
import com.testapp1202.service.impl.GenericServiceImpl;
import com.testapp1202.dao.CustomerDAO;
import com.testapp1202.domain.Customer;
import com.testapp1202.dto.CustomerDTO;
import com.testapp1202.dto.CustomerSearchDTO;
import com.testapp1202.dto.CustomerPageDTO;
import com.testapp1202.dto.CustomerConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.CustomerService;
import com.testapp1202.util.ControllerUtils;





@Service
public class CustomerServiceImpl extends GenericServiceImpl<Customer, Integer> implements CustomerService {

    private final static Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	CustomerDAO customerDao;

	


	@Override
	public GenericDAO<Customer, Integer> getDAO() {
		return (GenericDAO<Customer, Integer>) customerDao;
	}
	
	public List<Customer> findAll () {
		List<Customer> customers = customerDao.findAll();
		
		return customers;	
		
	}

	public ResultDTO addCustomer(CustomerDTO customerDTO, RequestDTO requestDTO) {

		Customer customer = new Customer();

		customer.setCustomerId(customerDTO.getCustomerId());


		customer.setName(customerDTO.getName());


		customer.setEmail(customerDTO.getEmail());


		customer.setPhone(customerDTO.getPhone());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		customer = customerDao.save(customer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<Customer> getAllCustomers(Pageable pageable) {
		return customerDao.findAll(pageable);
	}

	public Page<Customer> getAllCustomers(Specification<Customer> spec, Pageable pageable) {
		return customerDao.findAll(spec, pageable);
	}

	public ResponseEntity<CustomerPageDTO> getCustomers(CustomerSearchDTO customerSearchDTO) {
	
			Integer customerId = customerSearchDTO.getCustomerId(); 
 			String name = customerSearchDTO.getName(); 
 			String email = customerSearchDTO.getEmail(); 
 			String phone = customerSearchDTO.getPhone(); 
 			String sortBy = customerSearchDTO.getSortBy();
			String sortOrder = customerSearchDTO.getSortOrder();
			String searchQuery = customerSearchDTO.getSearchQuery();
			Integer page = customerSearchDTO.getPage();
			Integer size = customerSearchDTO.getSize();

	        Specification<Customer> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, customerId, "customerId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, name, "name"); 
			
			spec = ControllerUtils.andIfNecessary(spec, email, "email"); 
			
			spec = ControllerUtils.andIfNecessary(spec, phone, "phone"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("name")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("email")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("phone")), "%" + searchQuery.toLowerCase() + "%") 
		));}
		
		Sort sort = Sort.unsorted();
		if (sortBy != null && !sortBy.isEmpty() && sortOrder != null && !sortOrder.isEmpty()) {
			if (sortOrder.equalsIgnoreCase("asc")) {
				sort = Sort.by(sortBy).ascending();
			} else if (sortOrder.equalsIgnoreCase("desc")) {
				sort = Sort.by(sortBy).descending();
			}
		}
		Pageable pageable = PageRequest.of(page, size, sort);

		Page<Customer> customers = this.getAllCustomers(spec, pageable);
		
		//System.out.println(String.valueOf(customers.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(customers.getTotalPages()));
		
		List<Customer> customersList = customers.getContent();
		
		CustomerConvertCriteriaDTO convertCriteria = new CustomerConvertCriteriaDTO();
		List<CustomerDTO> customerDTOs = this.convertCustomersToCustomerDTOs(customersList,convertCriteria);
		
		CustomerPageDTO customerPageDTO = new CustomerPageDTO();
		customerPageDTO.setCustomers(customerDTOs);
		customerPageDTO.setTotalElements(customers.getTotalElements());
		return ResponseEntity.ok(customerPageDTO);
	}

	public List<CustomerDTO> convertCustomersToCustomerDTOs(List<Customer> customers, CustomerConvertCriteriaDTO convertCriteria) {
		
		List<CustomerDTO> customerDTOs = new ArrayList<CustomerDTO>();
		
		for (Customer customer : customers) {
			customerDTOs.add(convertCustomerToCustomerDTO(customer,convertCriteria));
		}
		
		return customerDTOs;

	}
	
	public CustomerDTO convertCustomerToCustomerDTO(Customer customer, CustomerConvertCriteriaDTO convertCriteria) {
		
		CustomerDTO customerDTO = new CustomerDTO();
		
		customerDTO.setCustomerId(customer.getCustomerId());

	
		customerDTO.setName(customer.getName());

	
		customerDTO.setEmail(customer.getEmail());

	
		customerDTO.setPhone(customer.getPhone());

	

		
		return customerDTO;
	}

	public ResultDTO updateCustomer(CustomerDTO customerDTO, RequestDTO requestDTO) {
		
		Customer customer = customerDao.getById(customerDTO.getCustomerId());

		customer.setCustomerId(ControllerUtils.setValue(customer.getCustomerId(), customerDTO.getCustomerId()));

		customer.setName(ControllerUtils.setValue(customer.getName(), customerDTO.getName()));

		customer.setEmail(ControllerUtils.setValue(customer.getEmail(), customerDTO.getEmail()));

		customer.setPhone(ControllerUtils.setValue(customer.getPhone(), customerDTO.getPhone()));



        customer = customerDao.save(customer);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public CustomerDTO getCustomerDTOById(Integer customerId) {
	
		Customer customer = customerDao.getById(customerId);
			
		
		CustomerConvertCriteriaDTO convertCriteria = new CustomerConvertCriteriaDTO();
		return(this.convertCustomerToCustomerDTO(customer,convertCriteria));
	}







}
