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

public final class DoRegistration implements Command {
	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	private final UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_EMAIL_PARAM = "email";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_REGISTRATION_ERROR_PARAM = "RegistrationError";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String email = request.getParameter(JSP_EMAIL_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);

		request.getSession().removeAttribute(JSP_REGISTRATION_ERROR_PARAM);

		if (!validation.isValidData(login, email, password)){
			request.getSession(true).setAttribute(JSP_REGISTRATION_ERROR_PARAM, "Login/Email/Password not valid. The number of characters must not be less than 1");
			response.sendRedirect("controller?command=go_to_registration_page");
			return;
		}
		
		try {
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			password = null;
			NewUserInfo user = new NewUserInfo(login, email, hashedPassword);
			userService.registration(user);
			response.sendRedirect("controller?command=go_to_base_page");
		}
		catch (ServiceException e) {
			request.getSession(true).setAttribute(JSP_REGISTRATION_ERROR_PARAM, e.getCause().getMessage());
			response.sendRedirect("controller?command=go_to_registration_page");
		}
	}
}
