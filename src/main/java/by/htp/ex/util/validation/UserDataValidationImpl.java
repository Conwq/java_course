package by.htp.ex.util.validation;

public final class UserDataValidationImpl implements UserDataValidation {
	@Override
	public boolean isValidData(String login, String email, String password) {
		return login.length() >= 1 && email.length() >= 1 && password.length() >= 1;
	}

	@Override
	public boolean isValidData(String login, String email, String name, String surname, String city, String password, String repeatPassword) {
		if (password.length() < 1 || !password.equals(repeatPassword)){
			return false;
		}
		return login.length()>0 && email.length()>0 && name.length()>0 && surname.length()>0 && city.length()>0;
	}

	@Override
	public boolean isValidData(String login, String password) {
		return login.length() >= 1 && password.length() >= 1;
	}
}
