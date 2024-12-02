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
import com.testapp1202.dao.ShippingDetailsDAO;
import com.testapp1202.domain.ShippingDetails;
import com.testapp1202.dto.ShippingDetailsDTO;
import com.testapp1202.dto.ShippingDetailsSearchDTO;
import com.testapp1202.dto.ShippingDetailsPageDTO;
import com.testapp1202.dto.ShippingDetailsConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.ShippingDetailsService;
import com.testapp1202.util.ControllerUtils;





@Service
public class ShippingDetailsServiceImpl extends GenericServiceImpl<ShippingDetails, Integer> implements ShippingDetailsService {

    private final static Logger logger = LoggerFactory.getLogger(ShippingDetailsServiceImpl.class);

	@Autowired
	ShippingDetailsDAO shippingDetailsDao;

	


	@Override
	public GenericDAO<ShippingDetails, Integer> getDAO() {
		return (GenericDAO<ShippingDetails, Integer>) shippingDetailsDao;
	}
	
	public List<ShippingDetails> findAll () {
		List<ShippingDetails> shippingDetailss = shippingDetailsDao.findAll();
		
		return shippingDetailss;	
		
	}

	public ResultDTO addShippingDetails(ShippingDetailsDTO shippingDetailsDTO, RequestDTO requestDTO) {

		ShippingDetails shippingDetails = new ShippingDetails();

		shippingDetails.setShippingDetailsId(shippingDetailsDTO.getShippingDetailsId());


		shippingDetails.setBoxShippedDate(shippingDetailsDTO.getBoxShippedDate());


		shippingDetails.setTrackingNumber(shippingDetailsDTO.getTrackingNumber());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		shippingDetails = shippingDetailsDao.save(shippingDetails);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<ShippingDetails> getAllShippingDetailss(Pageable pageable) {
		return shippingDetailsDao.findAll(pageable);
	}

	public Page<ShippingDetails> getAllShippingDetailss(Specification<ShippingDetails> spec, Pageable pageable) {
		return shippingDetailsDao.findAll(spec, pageable);
	}

	public ResponseEntity<ShippingDetailsPageDTO> getShippingDetailss(ShippingDetailsSearchDTO shippingDetailsSearchDTO) {
	
			Integer shippingDetailsId = shippingDetailsSearchDTO.getShippingDetailsId(); 
   			String trackingNumber = shippingDetailsSearchDTO.getTrackingNumber(); 
 			String sortBy = shippingDetailsSearchDTO.getSortBy();
			String sortOrder = shippingDetailsSearchDTO.getSortOrder();
			String searchQuery = shippingDetailsSearchDTO.getSearchQuery();
			Integer page = shippingDetailsSearchDTO.getPage();
			Integer size = shippingDetailsSearchDTO.getSize();

	        Specification<ShippingDetails> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, shippingDetailsId, "shippingDetailsId"); 
			
 			
			spec = ControllerUtils.andIfNecessary(spec, trackingNumber, "trackingNumber"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("trackingNumber")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<ShippingDetails> shippingDetailss = this.getAllShippingDetailss(spec, pageable);
		
		//System.out.println(String.valueOf(shippingDetailss.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(shippingDetailss.getTotalPages()));
		
		List<ShippingDetails> shippingDetailssList = shippingDetailss.getContent();
		
		ShippingDetailsConvertCriteriaDTO convertCriteria = new ShippingDetailsConvertCriteriaDTO();
		List<ShippingDetailsDTO> shippingDetailsDTOs = this.convertShippingDetailssToShippingDetailsDTOs(shippingDetailssList,convertCriteria);
		
		ShippingDetailsPageDTO shippingDetailsPageDTO = new ShippingDetailsPageDTO();
		shippingDetailsPageDTO.setShippingDetailss(shippingDetailsDTOs);
		shippingDetailsPageDTO.setTotalElements(shippingDetailss.getTotalElements());
		return ResponseEntity.ok(shippingDetailsPageDTO);
	}

	public List<ShippingDetailsDTO> convertShippingDetailssToShippingDetailsDTOs(List<ShippingDetails> shippingDetailss, ShippingDetailsConvertCriteriaDTO convertCriteria) {
		
		List<ShippingDetailsDTO> shippingDetailsDTOs = new ArrayList<ShippingDetailsDTO>();
		
		for (ShippingDetails shippingDetails : shippingDetailss) {
			shippingDetailsDTOs.add(convertShippingDetailsToShippingDetailsDTO(shippingDetails,convertCriteria));
		}
		
		return shippingDetailsDTOs;

	}
	
	public ShippingDetailsDTO convertShippingDetailsToShippingDetailsDTO(ShippingDetails shippingDetails, ShippingDetailsConvertCriteriaDTO convertCriteria) {
		
		ShippingDetailsDTO shippingDetailsDTO = new ShippingDetailsDTO();
		
		shippingDetailsDTO.setShippingDetailsId(shippingDetails.getShippingDetailsId());

	
		shippingDetailsDTO.setBoxShippedDate(shippingDetails.getBoxShippedDate());

	
		shippingDetailsDTO.setTrackingNumber(shippingDetails.getTrackingNumber());

	

		
		return shippingDetailsDTO;
	}

	public ResultDTO updateShippingDetails(ShippingDetailsDTO shippingDetailsDTO, RequestDTO requestDTO) {
		
		ShippingDetails shippingDetails = shippingDetailsDao.getById(shippingDetailsDTO.getShippingDetailsId());

		shippingDetails.setShippingDetailsId(ControllerUtils.setValue(shippingDetails.getShippingDetailsId(), shippingDetailsDTO.getShippingDetailsId()));

		shippingDetails.setBoxShippedDate(ControllerUtils.setValue(shippingDetails.getBoxShippedDate(), shippingDetailsDTO.getBoxShippedDate()));

		shippingDetails.setTrackingNumber(ControllerUtils.setValue(shippingDetails.getTrackingNumber(), shippingDetailsDTO.getTrackingNumber()));



        shippingDetails = shippingDetailsDao.save(shippingDetails);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public ShippingDetailsDTO getShippingDetailsDTOById(Integer shippingDetailsId) {
	
		ShippingDetails shippingDetails = shippingDetailsDao.getById(shippingDetailsId);
			
		
		ShippingDetailsConvertCriteriaDTO convertCriteria = new ShippingDetailsConvertCriteriaDTO();
		return(this.convertShippingDetailsToShippingDetailsDTO(shippingDetails,convertCriteria));
	}







}
