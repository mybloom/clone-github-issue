package com.example.clonegithubissue.common.dto;

public enum ApiResourceType {

	LABEL("labels"),
	MEMBER("members"),
	MILESTONE("milestones"),
	ISSUE("issues");

	private final String resourceType;

	ApiResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	public String getResourceType() {
		return resourceType;
	}
}
