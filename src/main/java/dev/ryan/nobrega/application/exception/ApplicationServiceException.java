package dev.ryan.nobrega.application.exception;


import dev.ryan.nobrega.infra.propertiesMessage.MessageBundle;

import java.io.Serial;
import java.io.Serializable;

public class ApplicationServiceException extends Exception implements Serializable {
	@Serial
	private static final long serialVersionUID = 2013512271265915763L;	

	private static final String MESSAGE_DEFAUT = "message.default";
	
	private Integer statusCode;
	public ApplicationServiceException(String messageKeyLoc) {
		super(MessageBundle.getMessage(messageKeyLoc));
	}
	
	
	public ApplicationServiceException(String messageKeyLoc, Integer statusCode) {
		super(MessageBundle.getMessage(messageKeyLoc));
		this.statusCode = statusCode;
	}

	public ApplicationServiceException(String messageKeyLoc, Integer statusCode, String[] params) {
		super(MessageBundle.getMessage(messageKeyLoc,params));
		this.statusCode = statusCode;
	}
	
	public ApplicationServiceException(){
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
