package com.example.clonegithubissue.common.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class DataResponse<T> {

	private String type;
	private Long id;
	private List<T> attributes;

}
