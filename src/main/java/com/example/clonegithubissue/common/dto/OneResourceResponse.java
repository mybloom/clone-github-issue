package com.example.clonegithubissue.common.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OneResourceResponse<T> {

	private String type;
	private T attribute;
	private RelationDataResponse relationships;

}
