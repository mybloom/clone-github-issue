package com.example.clonegithubissue.label.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class LabelResponse {

	private Long id;
	private String title;
	private String color;
	private String description;

}
