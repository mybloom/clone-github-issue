package com.example.clonegithubissue.exception;

public class LabelDuplicateDataException extends DuplicateDataException {

	@Override
	protected String getBodyMessage() {
		return "Label title has already been taken";
	}
}
