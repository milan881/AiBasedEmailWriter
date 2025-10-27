package com.email.model;

import lombok.Data;

@Data
public class EmailRequest {

	public String EmailContent;
	public String tone;
	public String getEmailContent() {
		return EmailContent;
	}
	public void setEmailContent(String emailContent) {
		EmailContent = emailContent;
	}
	public String getTone() {
		return tone;
	}
	public void setTone(String tone) {
		this.tone = tone;
	}



}
