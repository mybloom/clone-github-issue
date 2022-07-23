package com.example.clonegithubissue.label.dto;

import lombok.Getter;

@Getter
public class LabelSaveRequest {

	private Long id;
	private String title;
	private String description;
	private String color;

}
