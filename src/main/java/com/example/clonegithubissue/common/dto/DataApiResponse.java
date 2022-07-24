package com.example.clonegithubissue.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class DataApiResponse<T> {

	private T data;

}
