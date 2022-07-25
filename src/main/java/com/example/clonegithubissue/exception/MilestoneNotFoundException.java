package com.example.clonegithubissue.exception;

public class MilestoneNotFoundException extends NotFoundException{

	@Override
	protected String getBodyMessage() {
		return "There is no corresponding milestoneId.";
	}
}
