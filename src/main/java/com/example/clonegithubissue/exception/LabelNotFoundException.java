package com.example.clonegithubissue.exception;

public class LabelNotFoundException extends NotFoundException{

	@Override
	protected String getBodyMessage() {
		return "There is no corresponding labelId.";
	}
}
