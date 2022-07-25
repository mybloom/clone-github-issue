package com.example.clonegithubissue.exception;

public class MilestoneDuplicateDataException extends DuplicateDataException {

	@Override
	protected String getBodyMessage() {
		return "Milestone title has already been taken";
	}
}
