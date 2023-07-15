package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.CookiesHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoSignOut implements Command {
	private final static IUserService service = ServiceProvider.getInstance().getUserService();
	private final static CookiesHelper cookies = CookiesHelper.getInstance();
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_USER_NOT_ACTIVE_PARAM = "not active";
	private final static String COOKIE_NAME = "my_cookie";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE_PARAM);
		Cookie cookie = cookies.getCookie(request, COOKIE_NAME);
		
		try {
			if (cookie != null) {
				cookie.setMaxAge(0);
				response.addCookie(cookie);
				service.deleteCookie(cookie.getValue());
			}
			
			response.sendRedirect("index.jsp");
		}
		catch (ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}
