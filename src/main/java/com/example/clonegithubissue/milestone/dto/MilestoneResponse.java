package com.example.clonegithubissue.milestone.dto;

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
public class MilestoneResponse {

	private Long id;
	private String title;
	private String description;
	private LocalDate dueDate;
	private int completionPercentage;
	private int openIssueCount;
	private int closedIssueCount;

}
