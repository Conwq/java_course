package by.htp.ex.util.validation;

public class CommentDataValidationImpl implements CommentDataValidation{
	@Override
	public boolean textValidator(String text) {
		return text.length() >= 2;
	}
}
