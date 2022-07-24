package com.example.clonegithubissue.label.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LabelSaveRequest {

	private Long id;
	private String title;
	private String description;
	private String color;

}
