package com.example.clonegithubissue.issue.dto;

import com.example.clonegithubissue.issue.Issue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class IssueListResponse {

	private Long id;
	private String title;
//	private Set<Label> labels;
	private Boolean opened;
	private String authorName;

	public static IssueListResponse from(Issue issue) {
		return new IssueListResponse(issue.getId(),
			issue.getTitle(),
			issue.getOpened(),
			issue.getAuthor().getOauthName());
	}
}
