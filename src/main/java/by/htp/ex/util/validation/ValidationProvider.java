package by.htp.ex.util.validation;

public class ValidationProvider {

	private final static ValidationProvider instance = new ValidationProvider();
	private final UserDataValidation userDataValidation = new UserDataValidationImpl();
	private final CommentDataValidation commentDataValidation = new CommentDataValidationImpl();

	private ValidationProvider(){

	}

	public static ValidationProvider getInstance() {
		return instance;
	}

	public UserDataValidation getUserValidator(){
		return userDataValidation;
	}
	public CommentDataValidation getCommentDataValidation(){
		return commentDataValidation;
	}
}
