package com.example.clonegithubissue.milestone.dto;

import java.time.LocalDate;
import lombok.Getter;

@Getter
public class MilestoneSaveRequest {

	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;

}
