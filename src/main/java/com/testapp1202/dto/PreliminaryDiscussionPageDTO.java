package com.testapp1202.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PreliminaryDiscussionPageDTO {

	private Integer page = 0;
	private Long totalElements = 0L;

	private List<PreliminaryDiscussionDTO> preliminaryDiscussions;
}





