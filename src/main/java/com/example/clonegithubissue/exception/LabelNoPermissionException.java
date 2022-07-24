package com.example.clonegithubissue.exception;

public class LabelNoPermissionException extends NotFoundException{

	@Override
	protected String getBodyMessage() {
		return "There is no permission to edit/delete label of the labelId.";
	}
}
