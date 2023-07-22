package by.htp.ex.service.exception;

import java.security.Provider;

public final class ServiceException extends Exception {

	public ServiceException(){
		super();
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Exception e) {
		super(e);
	}

	public ServiceException(String message, Exception e){
		super(message, e);
	}
}
