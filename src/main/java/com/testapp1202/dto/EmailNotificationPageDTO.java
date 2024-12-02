package com.testapp1202.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EmailNotificationPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<EmailNotificationDTO> emailNotifications;
}





