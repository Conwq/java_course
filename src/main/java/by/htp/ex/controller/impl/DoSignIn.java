package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;
import java.util.UUID;

public final class DoSignIn implements Command {
	private final static IUserService service = ServiceProvider.getInstance().getUserService();
	private final static UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
	private final static String JSP_LOGIN_PARAM = "login";
	private final static String JSP_PASSWORD_PARAM = "password";
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_USER_INFO_PARAM = "userInfo";
	private final static String JSP_USER_ACTIVE_PARAM = "active";
	private final static String JSP_USER_NOT_ACTIVE_PARAM = "not active";
	private final static String JSP_USER_ROLE_PARAM = "role";
	private final static String JSP_LOCALIZATION_PARAM = "localization";
	private final static String JSP_AUTHENTICATION_ERROR_PARAM = "AuthenticationError";
	private final static String JSP_LOCALE_PARAM = "locale";
	private final static String JSP_COOKIE_VALUE = "cookie_value";
	private final static String JSP_REMEMBER_PARAM = "remember";
	private final static String JSP_TRUE_PARAM = "true";
	private final static String COOKIE_NAME = "my_cookie";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession().removeAttribute(JSP_AUTHENTICATION_ERROR_PARAM);
		
//		if (!validation.isValidData(login, password)) {
//			request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE_PARAM);
//			request.getSession().setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, "Wrong Login/Password");
//			response.sendRedirect("controller?command=go_to_base_page");
//			return;
//		}
		
		try {
			NewUserInfo newUserInfo = signIn(request);
			createCookie(request, response, newUserInfo);

			request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_ACTIVE_PARAM);
			request.getSession().setAttribute(JSP_USER_ROLE_PARAM, newUserInfo.getRole().getRole());
			request.getSession().setAttribute(JSP_USER_INFO_PARAM, newUserInfo);
			request.getSession().setAttribute(JSP_LOCALIZATION_PARAM, newUserInfo.getLocale());
			request.getSession().setAttribute(JSP_LOCALE_PARAM, newUserInfo.getLocale().getLanguage());

			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (ServiceException e) {
			if (e.getCause() == null){
				response.sendRedirect("error/error.jsp");
				return;
			}
			request.getSession(true).setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, e.getCause().getMessage());
			response.sendRedirect("controller?command=go_to_base_page");
		}
	}

	private void createCookie(HttpServletRequest request, HttpServletResponse response, NewUserInfo newUserInfo)throws ServiceException{
		String remember = request.getParameter(JSP_REMEMBER_PARAM);
		if(remember != null && remember.equals(JSP_TRUE_PARAM)) {
			Cookie cookie = new Cookie(COOKIE_NAME, UUID.randomUUID().toString());
			cookie.setMaxAge(300);
			service.addCookieForUser(newUserInfo.getUserId(), cookie.getValue());
			response.addCookie(cookie);
		}
	}

	private NewUserInfo signIn(HttpServletRequest request) throws ServiceException{
		String cookieValue = request.getParameter(JSP_COOKIE_VALUE);
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);

		return cookieValue == null ? service.signInWithLoginAndPassword(login, password) : service.signInWithCookie(cookieValue);
	}
}
