package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoSignOut implements Command {
	private final static IUserService service = ServiceProvider.getInstance().getUserService();
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_USER_NOT_ACTIVE_PARAM = "not active";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE_PARAM);
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(Cookie cookie: cookies) {
				if(cookie.getName().equals("my_cookie")) {
					cookie.setMaxAge(0);
					response.addCookie(cookie);
					try {
						service.deleteCookie(cookie.getValue());
					}
					catch(ServiceException e) {
						response.sendRedirect("error/error.jsp");
					}
				}
			}
		}
		response.sendRedirect("index.jsp");
	}
}
