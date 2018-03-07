package com.exam.dto;

/**
 * Class to generate to return a Json with an possible error.
 * 
 * @author RafaelRa
 */
public enum Message {
	BAD_REQUEST("Sorry. BAD REQUEST. Try it again."), 
	INTERNAL_SERVER_ERROR("Sorry. INTERNAL ERROR. Try it again."), 
	SUCCESS(""), 
	ENTITY_INTEGRITY("You need to delete it's child before delete it ."), 
	NOT_FOUND("Sorry. We couldn't find what are you looking for.");

	private String description;

	Message(String description) {
		this.description = description;

	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}


}
