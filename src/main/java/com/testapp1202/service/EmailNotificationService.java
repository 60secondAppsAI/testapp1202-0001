package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.EmailNotification;
import com.testapp1202.dto.EmailNotificationDTO;
import com.testapp1202.dto.EmailNotificationSearchDTO;
import com.testapp1202.dto.EmailNotificationPageDTO;
import com.testapp1202.dto.EmailNotificationConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface EmailNotificationService extends GenericService<EmailNotification, Integer> {

	List<EmailNotification> findAll();

	ResultDTO addEmailNotification(EmailNotificationDTO emailNotificationDTO, RequestDTO requestDTO);

	ResultDTO updateEmailNotification(EmailNotificationDTO emailNotificationDTO, RequestDTO requestDTO);

    Page<EmailNotification> getAllEmailNotifications(Pageable pageable);

    Page<EmailNotification> getAllEmailNotifications(Specification<EmailNotification> spec, Pageable pageable);

	ResponseEntity<EmailNotificationPageDTO> getEmailNotifications(EmailNotificationSearchDTO emailNotificationSearchDTO);
	
	List<EmailNotificationDTO> convertEmailNotificationsToEmailNotificationDTOs(List<EmailNotification> emailNotifications, EmailNotificationConvertCriteriaDTO convertCriteria);

	EmailNotificationDTO getEmailNotificationDTOById(Integer emailNotificationId);







}





