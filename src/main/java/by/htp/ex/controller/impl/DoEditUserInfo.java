package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public final class DoEditUserInfo implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_LOGIN_PARAM = "login";
	private final static String JSP_EMAIL_PARAM = "email";
	private final static String JSP_PASSWORD_PARAM = "password";
	private final static String JSP_ERROR_PARAM = "error_edit_user";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter(JSP_ID_PARAM);
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String email = request.getParameter(JSP_EMAIL_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);

		request.getSession().removeAttribute(JSP_ERROR_PARAM);
		
		if(!validation.isValidData(login, email, password)) {
			request.getSession(true).setAttribute(JSP_ERROR_PARAM, "Login/Email/Password not valid. The number of characters must not be less than 1");
			response.sendRedirect("controller?command=go_to_personal_cabinet&id=" + idParam);
			return;
		}
		
		try {
			int id = Integer.parseInt(idParam);
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			NewUserInfo newUserInfo = new NewUserInfo(id, login, email, hashedPassword);
 			userService.updateUserInfo(newUserInfo);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (ServiceException e){
			String errorMessage = e.getCause().getMessage() != null ? e.getCause().getMessage() : "Error with registration, repeat later";
			request.getSession(true).setAttribute(JSP_ERROR_PARAM, errorMessage);
			response.sendRedirect("controller?command=go_to_personal_cabinet_e&id=" + idParam);
		}
		catch (NumberFormatException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
