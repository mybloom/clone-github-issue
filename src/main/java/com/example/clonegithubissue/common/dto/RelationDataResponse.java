package com.example.clonegithubissue.common.dto;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RelationDataResponse<T> {

	private String type;
	private List<T> attributes;

}
