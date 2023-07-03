package by.htp.ex.util.validation;

public final class UserDataValidationImpl implements UserDataValidation {
	@Override
	public boolean isValidData(String login, String email, String password) {
		return login.length() >= 1 && email.length() >= 1 && password.length() >= 1;
	}

	@Override
	public boolean isValidData(String login, String password) {
		return login.length() >= 1 && password.length() >= 1;
	}
}
