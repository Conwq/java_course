package by.htp.ex.util.validation;

public final class ValidationProvider {
	private static final ValidationProvider instance = new ValidationProvider();
	private static final UserDataValidation userDataValidation = new UserDataValidationImpl();
	private static final CommentDataValidation commentDataValidation = new CommentDataValidationImpl();

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
