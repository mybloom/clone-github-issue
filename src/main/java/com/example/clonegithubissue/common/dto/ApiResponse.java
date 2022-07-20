package com.example.clonegithubissue.common.dto;

import java.util.List;
import lombok.Getter;

@Getter
public class ApiResponse<DataResponse> {

	private List<DataResponse> data;

}
