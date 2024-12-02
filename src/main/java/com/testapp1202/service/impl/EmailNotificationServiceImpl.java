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
import com.testapp1202.dao.EmailNotificationDAO;
import com.testapp1202.domain.EmailNotification;
import com.testapp1202.dto.EmailNotificationDTO;
import com.testapp1202.dto.EmailNotificationSearchDTO;
import com.testapp1202.dto.EmailNotificationPageDTO;
import com.testapp1202.dto.EmailNotificationConvertCriteriaDTO;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import com.testapp1202.service.EmailNotificationService;
import com.testapp1202.util.ControllerUtils;





@Service
public class EmailNotificationServiceImpl extends GenericServiceImpl<EmailNotification, Integer> implements EmailNotificationService {

    private final static Logger logger = LoggerFactory.getLogger(EmailNotificationServiceImpl.class);

	@Autowired
	EmailNotificationDAO emailNotificationDao;

	


	@Override
	public GenericDAO<EmailNotification, Integer> getDAO() {
		return (GenericDAO<EmailNotification, Integer>) emailNotificationDao;
	}
	
	public List<EmailNotification> findAll () {
		List<EmailNotification> emailNotifications = emailNotificationDao.findAll();
		
		return emailNotifications;	
		
	}

	public ResultDTO addEmailNotification(EmailNotificationDTO emailNotificationDTO, RequestDTO requestDTO) {

		EmailNotification emailNotification = new EmailNotification();

		emailNotification.setEmailNotificationId(emailNotificationDTO.getEmailNotificationId());


		emailNotification.setSubject(emailNotificationDTO.getSubject());


		emailNotification.setBody(emailNotificationDTO.getBody());


		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date date = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());

		emailNotification = emailNotificationDao.save(emailNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}
	
	public Page<EmailNotification> getAllEmailNotifications(Pageable pageable) {
		return emailNotificationDao.findAll(pageable);
	}

	public Page<EmailNotification> getAllEmailNotifications(Specification<EmailNotification> spec, Pageable pageable) {
		return emailNotificationDao.findAll(spec, pageable);
	}

	public ResponseEntity<EmailNotificationPageDTO> getEmailNotifications(EmailNotificationSearchDTO emailNotificationSearchDTO) {
	
			Integer emailNotificationId = emailNotificationSearchDTO.getEmailNotificationId(); 
 			String subject = emailNotificationSearchDTO.getSubject(); 
 			String body = emailNotificationSearchDTO.getBody(); 
 			String sortBy = emailNotificationSearchDTO.getSortBy();
			String sortOrder = emailNotificationSearchDTO.getSortOrder();
			String searchQuery = emailNotificationSearchDTO.getSearchQuery();
			Integer page = emailNotificationSearchDTO.getPage();
			Integer size = emailNotificationSearchDTO.getSize();

	        Specification<EmailNotification> spec = Specification.where(null);

			spec = ControllerUtils.andIfNecessary(spec, emailNotificationId, "emailNotificationId"); 
			
			spec = ControllerUtils.andIfNecessary(spec, subject, "subject"); 
			
			spec = ControllerUtils.andIfNecessary(spec, body, "body"); 
			

		if (searchQuery != null && !searchQuery.isEmpty()) {
			spec = spec.and((root, query, cb) -> cb.or(

             cb.like(cb.lower(root.get("subject")), "%" + searchQuery.toLowerCase() + "%") 
             , cb.like(cb.lower(root.get("body")), "%" + searchQuery.toLowerCase() + "%") 
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

		Page<EmailNotification> emailNotifications = this.getAllEmailNotifications(spec, pageable);
		
		//System.out.println(String.valueOf(emailNotifications.getTotalElements()) + " total ${classNamelPlural}, viewing page X of " + String.valueOf(emailNotifications.getTotalPages()));
		
		List<EmailNotification> emailNotificationsList = emailNotifications.getContent();
		
		EmailNotificationConvertCriteriaDTO convertCriteria = new EmailNotificationConvertCriteriaDTO();
		List<EmailNotificationDTO> emailNotificationDTOs = this.convertEmailNotificationsToEmailNotificationDTOs(emailNotificationsList,convertCriteria);
		
		EmailNotificationPageDTO emailNotificationPageDTO = new EmailNotificationPageDTO();
		emailNotificationPageDTO.setEmailNotifications(emailNotificationDTOs);
		emailNotificationPageDTO.setTotalElements(emailNotifications.getTotalElements());
		return ResponseEntity.ok(emailNotificationPageDTO);
	}

	public List<EmailNotificationDTO> convertEmailNotificationsToEmailNotificationDTOs(List<EmailNotification> emailNotifications, EmailNotificationConvertCriteriaDTO convertCriteria) {
		
		List<EmailNotificationDTO> emailNotificationDTOs = new ArrayList<EmailNotificationDTO>();
		
		for (EmailNotification emailNotification : emailNotifications) {
			emailNotificationDTOs.add(convertEmailNotificationToEmailNotificationDTO(emailNotification,convertCriteria));
		}
		
		return emailNotificationDTOs;

	}
	
	public EmailNotificationDTO convertEmailNotificationToEmailNotificationDTO(EmailNotification emailNotification, EmailNotificationConvertCriteriaDTO convertCriteria) {
		
		EmailNotificationDTO emailNotificationDTO = new EmailNotificationDTO();
		
		emailNotificationDTO.setEmailNotificationId(emailNotification.getEmailNotificationId());

	
		emailNotificationDTO.setSubject(emailNotification.getSubject());

	
		emailNotificationDTO.setBody(emailNotification.getBody());

	

		
		return emailNotificationDTO;
	}

	public ResultDTO updateEmailNotification(EmailNotificationDTO emailNotificationDTO, RequestDTO requestDTO) {
		
		EmailNotification emailNotification = emailNotificationDao.getById(emailNotificationDTO.getEmailNotificationId());

		emailNotification.setEmailNotificationId(ControllerUtils.setValue(emailNotification.getEmailNotificationId(), emailNotificationDTO.getEmailNotificationId()));

		emailNotification.setSubject(ControllerUtils.setValue(emailNotification.getSubject(), emailNotificationDTO.getSubject()));

		emailNotification.setBody(ControllerUtils.setValue(emailNotification.getBody(), emailNotificationDTO.getBody()));



        emailNotification = emailNotificationDao.save(emailNotification);
		
		ResultDTO result = new ResultDTO();
		return result;
	}

	public EmailNotificationDTO getEmailNotificationDTOById(Integer emailNotificationId) {
	
		EmailNotification emailNotification = emailNotificationDao.getById(emailNotificationId);
			
		
		EmailNotificationConvertCriteriaDTO convertCriteria = new EmailNotificationConvertCriteriaDTO();
		return(this.convertEmailNotificationToEmailNotificationDTO(emailNotification,convertCriteria));
	}







}
