package by.htp.ex.util.validation;

public class ValidationProvider {

	private final static ValidationProvider instance = new ValidationProvider();
	private final UserDataValidationImpl userDataValidation = new UserDataValidationImpl();

	private ValidationProvider(){

	}

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidationImpl getUserValidator(){
		return userDataValidation;
	}
}
