package by.htp.ex.service.exception;

public final class ServiceException extends Exception {

	public ServiceException(){
		super();
	}

	public ServiceException(String e) {
		super(e);
	}

	public ServiceException(Exception e) {
		super(e);
	}
}
