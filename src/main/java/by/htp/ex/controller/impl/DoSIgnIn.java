package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoSIgnIn implements Command {
	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_USER_PARAM = "user";
	private static final String JSP_USER_ACTIVE = "active";
	private static final String JSP_USER_NOT_ACTIVE = "not active";
	private static final String JSP_USER_ROLE = "role";
	private static final String JSP_AUTHENTICATION_ERROR_PARAM = "AuthenticationError";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(JSP_LOGIN_PARAM);
 		String password = request.getParameter(JSP_PASSWORD_PARAM);

		//TODO ЗАМЕНИТЬ ФОРВАРД НА РЕДИРЕКТ (ИСПОЛЬЗОВАТЬ СЕССИЮ?)
		if (!isValidData(login, password)) {
			 request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE);
			 request.getSession().setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, "Login/Password should not be empty");
			 request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);
		 }

		try {
			String role = service.signIn(login, password);

			//TODO ПОДУМАТЬ КАК ЗАМЕНИТЬ IF ELSE

			if (!role.equals("guest")) {
				request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_ACTIVE);
				request.getSession().setAttribute(JSP_USER_ROLE, role);
				response.sendRedirect("controller?command=go_to_news_list");
			}

			else {
				request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE);
				request.setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, "wrong login or password");
				request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);
			}
		}

		catch (ServiceException e) {

		}
	}


	public boolean isValidData(String login, String password){
		if (login.length() < 1 || password.length() < 1)
			return false;
		return true;
	}
}
