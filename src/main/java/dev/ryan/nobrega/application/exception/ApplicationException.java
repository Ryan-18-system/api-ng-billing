package dev.ryan.nobrega.application.exception;


import dev.ryan.nobrega.propertiesMessage.MessageBundle;

public class ApplicationException extends Exception {
	
	private static final long serialVersionUID = 2013512271265915763L;	

	private static final String MESSAGE_DEFAUT = "message.default";
	
	private Integer statusCode;
	public ApplicationException(String messageKeyLoc) {
		super(MessageBundle.getMessage(messageKeyLoc));
	}
	
	
	public ApplicationException(String messageKeyLoc, Integer statusCode) {
		super(MessageBundle.getMessage(messageKeyLoc));
		this.statusCode = statusCode;
	}
	
	public ApplicationException(){
		super(MessageBundle.getMessage(MessageBundle.getMessage(MESSAGE_DEFAUT)));
		this.statusCode = 500;
	}

	public Integer getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
}
