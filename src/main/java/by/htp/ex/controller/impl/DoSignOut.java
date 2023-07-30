package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.CookiesHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoSignOut implements Command {
	private static final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final CookiesHelper cookies = CookiesHelper.getInstance();
	private static final String JSP_USER_PARAM = "user";
	private static final String JSP_USER_NOT_ACTIVE_PARAM = "not active";
	private static final String COOKIE_NAME = "my_cookie";

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
