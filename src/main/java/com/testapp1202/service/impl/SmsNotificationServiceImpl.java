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
import com.testapp1202.dao.SmsNotificationDAO;
import com.testapp1202.domain.SmsNotification;
import com.testapp1202.dto.SmsNotificationDTO;
import com.testapp1202.dto.SmsNotificationSearchDTO;
import com.testapp1202.dto.SmsNotificationPageDTO;
import com.testapp1202.dto.SmsNotificationConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.SmsNotificationService;
import com.testapp1202.util.ControllerUtils;





@Service
public class SmsNotificationServiceImpl extends GenericServiceImpl<SmsNotification, Integer> implements SmsNotificationService {

    private final static Logger logger = LoggerFactory.getLogger(SmsNotificationServiceImpl.class);

	@Autowired
	SmsNotificationDAO smsNotificationDao;

	


	@Override
	public GenericDAO<SmsNotification, Integer> getDAO() {
		return (GenericDAO<SmsNotification, Integer>) smsNotificationDao;
	}
	
	public List<SmsNotification> findAll () {
		List<SmsNotification> smsNotifications = smsNotificationDao.findAll();
		
		return smsNotifications;	
		
	}

	public ResultDTO addSmsNotification(SmsNotificationDTO smsNotificationDTO, RequestDTO requestDTO) {

		SmsNotification smsNotification = new SmsNotification();

		smsNotification.setSmsNotificationId(smsNotificationDTO.getSmsNotificationId());


		smsNotification.setMessage(smsNotificationDTO.getMessage());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		smsNotification = smsNotificationDao.save(smsNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<SmsNotification> getAllSmsNotifications(Pageable pageable) {
		return smsNotificationDao.findAll(pageable);
	}

	public Page<SmsNotification> getAllSmsNotifications(Specification<SmsNotification> spec, Pageable pageable) {
		return smsNotificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<SmsNotificationPageDTO> getSmsNotifications(SmsNotificationSearchDTO smsNotificationSearchDTO) {
	
			Integer smsNotificationId = smsNotificationSearchDTO.getSmsNotificationId(); 
 			String message = smsNotificationSearchDTO.getMessage(); 
 			String sortBy = smsNotificationSearchDTO.getSortBy();
			String sortOrder = smsNotificationSearchDTO.getSortOrder();
			String searchQuery = smsNotificationSearchDTO.getSearchQuery();
			Integer page = smsNotificationSearchDTO.getPage();
			Integer size = smsNotificationSearchDTO.getSize();

	        Specification<SmsNotification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, smsNotificationId, "smsNotificationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, message, "message"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("message")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<SmsNotification> smsNotifications = this.getAllSmsNotifications(spec, pageable);
		
		//System.out.println(String.valueOf(smsNotifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(smsNotifications.getTotalPages()));
		
		List<SmsNotification> smsNotificationsList = smsNotifications.getContent();
		
		SmsNotificationConvertCriteriaDTO convertCriteria = new SmsNotificationConvertCriteriaDTO();
		List<SmsNotificationDTO> smsNotificationDTOs = this.convertSmsNotificationsToSmsNotificationDTOs(smsNotificationsList,convertCriteria);
		
		SmsNotificationPageDTO smsNotificationPageDTO = new SmsNotificationPageDTO();
		smsNotificationPageDTO.setSmsNotifications(smsNotificationDTOs);
		smsNotificationPageDTO.setTotalElements(smsNotifications.getTotalElements());
		return ResponseEntity.ok(smsNotificationPageDTO);
	}

	public List<SmsNotificationDTO> convertSmsNotificationsToSmsNotificationDTOs(List<SmsNotification> smsNotifications, SmsNotificationConvertCriteriaDTO convertCriteria) {
		
		List<SmsNotificationDTO> smsNotificationDTOs = new ArrayList<SmsNotificationDTO>();
		
		for (SmsNotification smsNotification : smsNotifications) {
			smsNotificationDTOs.add(convertSmsNotificationToSmsNotificationDTO(smsNotification,convertCriteria));
		}
		
		return smsNotificationDTOs;

	}
	
	public SmsNotificationDTO convertSmsNotificationToSmsNotificationDTO(SmsNotification smsNotification, SmsNotificationConvertCriteriaDTO convertCriteria) {
		
		SmsNotificationDTO smsNotificationDTO = new SmsNotificationDTO();
		
		smsNotificationDTO.setSmsNotificationId(smsNotification.getSmsNotificationId());

	
		smsNotificationDTO.setMessage(smsNotification.getMessage());

	

		
		return smsNotificationDTO;
	}

	public ResultDTO updateSmsNotification(SmsNotificationDTO smsNotificationDTO, RequestDTO requestDTO) {
		
		SmsNotification smsNotification = smsNotificationDao.getById(smsNotificationDTO.getSmsNotificationId());

		smsNotification.setSmsNotificationId(ControllerUtils.setValue(smsNotification.getSmsNotificationId(), smsNotificationDTO.getSmsNotificationId()));

		smsNotification.setMessage(ControllerUtils.setValue(smsNotification.getMessage(), smsNotificationDTO.getMessage()));



        smsNotification = smsNotificationDao.save(smsNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public SmsNotificationDTO getSmsNotificationDTOById(Integer smsNotificationId) {
	
		SmsNotification smsNotification = smsNotificationDao.getById(smsNotificationId);
			
		
		SmsNotificationConvertCriteriaDTO convertCriteria = new SmsNotificationConvertCriteriaDTO();
		return(this.convertSmsNotificationToSmsNotificationDTO(smsNotification,convertCriteria));
	}







}
