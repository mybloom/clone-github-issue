package com.example.clonegithubissue.exception;

public class MilestoneNoPermissionException extends NotFoundException{

	@Override
	protected String getBodyMessage() {
		return "There is no permission to edit/delete milestone of the milestoneId.";
	}
}
