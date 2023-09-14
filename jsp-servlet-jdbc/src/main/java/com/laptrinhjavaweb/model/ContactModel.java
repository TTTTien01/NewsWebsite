package com.laptrinhjavaweb.model;

public class ContactModel extends AbstractModel<ContactModel> {

	private String fullName;
	private String email;
	private String phoneNumber;
	private String title;
	private String content;
	
	public ContactModel() {
		
	}

	public ContactModel(String fullName, String email, String phoneNumber, String title, String content) {
		super();
		this.fullName = fullName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.title = title;
		this.content = content;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

}