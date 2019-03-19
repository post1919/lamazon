package com.lamazon.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ApiError {
	private static final long serialVersionUID = -8172536311626933035L;

	private String message;

	@JsonProperty("documentation_url")
	private String documentationUrl;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDocumentationUrl() {
		return documentationUrl;
	}

	public void setDocumentationUrl(String documentationUrl) {
		this.documentationUrl = documentationUrl;
	}
}
