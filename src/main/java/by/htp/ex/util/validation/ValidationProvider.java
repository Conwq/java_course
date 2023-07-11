package by.htp.ex.util.validation;

public final class ValidationProvider {
	private final static ValidationProvider instance = new ValidationProvider();
	private final static UserDataValidation userDataValidation = new UserDataValidationImpl();
	private final static CommentDataValidation commentDataValidation = new CommentDataValidationImpl();

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
