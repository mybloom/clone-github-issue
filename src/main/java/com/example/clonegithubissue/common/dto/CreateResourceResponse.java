package com.example.clonegithubissue.common.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateResourceResponse<T> {

	private String type;
	private T attribute;

}
