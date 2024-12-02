package com.testapp1202.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;

import com.testapp1202.domain.SmsNotification;
import com.testapp1202.dto.SmsNotificationDTO;
import com.testapp1202.dto.SmsNotificationSearchDTO;
import com.testapp1202.dto.SmsNotificationPageDTO;
import com.testapp1202.dto.SmsNotificationConvertCriteriaDTO;
import com.testapp1202.service.GenericService;
import com.testapp1202.dto.common.RequestDTO;
import com.testapp1202.dto.common.ResultDTO;
import java.util.List;
import java.util.Optional;





public interface SmsNotificationService extends GenericService<SmsNotification, Integer> {

	List<SmsNotification> findAll();

	ResultDTO addSmsNotification(SmsNotificationDTO smsNotificationDTO, RequestDTO requestDTO);

	ResultDTO updateSmsNotification(SmsNotificationDTO smsNotificationDTO, RequestDTO requestDTO);

    Page<SmsNotification> getAllSmsNotifications(Pageable pageable);

    Page<SmsNotification> getAllSmsNotifications(Specification<SmsNotification> spec, Pageable pageable);

	ResponseEntity<SmsNotificationPageDTO> getSmsNotifications(SmsNotificationSearchDTO smsNotificationSearchDTO);
	
	List<SmsNotificationDTO> convertSmsNotificationsToSmsNotificationDTOs(List<SmsNotification> smsNotifications, SmsNotificationConvertCriteriaDTO convertCriteria);

	SmsNotificationDTO getSmsNotificationDTOById(Integer smsNotificationId);







}





