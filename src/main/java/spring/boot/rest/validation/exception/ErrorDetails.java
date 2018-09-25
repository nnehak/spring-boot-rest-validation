package spring.boot.rest.validation.exception;

import java.util.Date;

public class ErrorDetails {
	private Date timestamp;
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	private String message;
	private String details;
	
	public ErrorDetails(java.util.Date date, String message, String details){
		super();
		this.timestamp=date;
		this.message=message;
		this.details=details;
	}

}
