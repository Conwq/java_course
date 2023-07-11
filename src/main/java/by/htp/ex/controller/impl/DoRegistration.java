package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.NewsManagerHelper;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Locale;

public final class DoRegistration implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
	private final static NewsManagerHelper helper = NewsManagerHelper.getInstance();
	private final static String JSP_LOGIN_PARAM = "login";
	private final static String JSP_EMAIL_PARAM = "email";
	private final static String JSP_NAME_PARAM = "name";
	private final static String JSP_SURNAME_PARAM = "surname";
	private final static String JSP_CITY_PARAM = "city";
	private final static String JSP_PASSWORD_PARAM = "password";
	private final static String JSP_PASSWORD_REPEAT_PARAM = "password_repeat";
	private final static String JSP_SELECTED_LOCALE_PARAM = "selectedLocale";
	private static final String JSP_REGISTRATION_ERROR_PARAM = "RegistrationError";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewUserInfo user;
		Locale locale;
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String email = request.getParameter(JSP_EMAIL_PARAM);
		String name = request.getParameter(JSP_NAME_PARAM);
		String surname = request.getParameter(JSP_SURNAME_PARAM);
		String city = request.getParameter(JSP_CITY_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);
		String repeatPassword = request.getParameter(JSP_PASSWORD_REPEAT_PARAM);

		request.getSession().removeAttribute(JSP_REGISTRATION_ERROR_PARAM);

//		if (!validation.isValidData(login, email, name, surname, city, password, repeatPassword)){
//
//			request.getSession(true).setAttribute(JSP_REGISTRATION_ERROR_PARAM, "Not valid data. The number of characters must not be less than 1");
//			response.sendRedirect("controller?command=go_to_registration_page");
//			return;
//		}
		
		try {
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			password = null;
			user = new NewUserInfo(login, email, hashedPassword, name, surname, city);
			locale = helper.getLocale(request.getParameter(JSP_SELECTED_LOCALE_PARAM));

			userService.registration(user, locale);

			response.sendRedirect("controller?command=go_to_base_page");
		}
		catch (ServiceException e) {
			request.getSession(true).setAttribute(JSP_REGISTRATION_ERROR_PARAM, e.getCause().getMessage());
			response.sendRedirect("controller?command=go_to_registration_page");
		}
	}
}
