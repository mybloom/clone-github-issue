package com.example.clonegithubissue.milestone.dto;

import com.example.clonegithubissue.milestone.Milestone;
import java.time.LocalDate;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

@ToString
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class MilestoneGetResponse {

	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private int completionPercentage;
	private int openIssueCount;
	private int closedIssueCount;

	public static MilestoneGetResponse from(Milestone milestone) {
		return MilestoneGetResponse.builder()
			.title(milestone.getTitle())
			.description(milestone.getDescription())
			.dueDate(milestone.getDueDate())
			.build();
	}
}
