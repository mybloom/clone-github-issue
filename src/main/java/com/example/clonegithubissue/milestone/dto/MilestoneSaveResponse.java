package com.example.clonegithubissue.milestone.dto;

import com.example.clonegithubissue.milestone.Milestone;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MilestoneSaveResponse {

	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;

	public static MilestoneSaveResponse from(Milestone milestone) {
		return MilestoneSaveResponse.builder()
			.id(milestone.getId())
			.title(milestone.getTitle())
			.description(milestone.getDescription())
			.dueDate(milestone.getDueDate())
			.build();
	}
}
