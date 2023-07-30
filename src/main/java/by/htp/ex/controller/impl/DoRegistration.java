package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.Converter;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.Locale;

public final class DoRegistration implements Command {
	private static final IUserService userService = ServiceProvider.getInstance().getUserService();
	private static final UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
	private static final Converter converter = Converter.getInstance();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_EMAIL_PARAM = "email";
	private static final String JSP_NAME_PARAM = "name";
	private static final String JSP_SURNAME_PARAM = "surname";
	private static final String JSP_CITY_PARAM = "city";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_PASSWORD_REPEAT_PARAM = "password_repeat";
	private static final String JSP_SELECTED_LOCALE_PARAM = "selectedLocale";
	private static final String JSP_ERROR_PARAM = "error_registration";

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

		if (!validation.isValidData(login, email, name, surname, city, password, repeatPassword)) {

			request.getSession(true).setAttribute(JSP_ERROR_PARAM, "Not valid data. The number of characters must not be less than 1.");
			response.sendRedirect("controller?command=go_to_registration_page");
			return;
		}

		try {
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			password = null;
			user = new NewUserInfo(login, email, hashedPassword, name, surname, city);
			locale = converter.getLocale(request.getParameter(JSP_SELECTED_LOCALE_PARAM));

			userService.registration(user, locale);

			response.sendRedirect("controller?command=go_to_base_page");
		}
		catch (ServiceException e) {
			String errorMessage = e.getCause().getMessage() != null ? e.getCause().getMessage() : "Error with registration, repeat later";
			request.getSession(true).setAttribute(JSP_ERROR_PARAM, errorMessage);
			response.sendRedirect("controller?command=go_to_registration_page_e");
		}
	}
}
