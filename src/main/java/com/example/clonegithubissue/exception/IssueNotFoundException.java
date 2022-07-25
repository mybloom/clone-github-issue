package com.example.clonegithubissue.exception;

public class IssueNotFoundException extends NotFoundException{

	@Override
	protected String getBodyMessage() {
		return "There is no corresponding issueId.";
	}
}
