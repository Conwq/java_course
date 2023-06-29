package by.htp.ex.util.validation;

public interface UserDataValidation {
       boolean isValidData(String login, String email, String password);
       boolean isValidData(String login, String password);
}
