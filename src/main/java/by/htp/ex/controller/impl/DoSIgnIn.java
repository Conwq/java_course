package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.ErrorHandler;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoSIgnIn implements Command {
	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";
	private static final String JSP_USER_PARAM = "user";
	private static final String JSP_USER_INFO_PARAM = "userInfo";
	private static final String JSP_USER_ACTIVE = "active";
	private static final String JSP_USER_NOT_ACTIVE = "not active";
	private static final String JSP_USER_ROLE = "role";
	private static final String JSP_AUTHENTICATION_ERROR_PARAM = "AuthenticationError";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(JSP_LOGIN_PARAM);
 		String password = request.getParameter(JSP_PASSWORD_PARAM);

		 request.getSession().removeAttribute(JSP_AUTHENTICATION_ERROR_PARAM);

		if (!isValidData(login, password)){
			request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE);
			request.getSession().setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, "Wrong Login/Password");
			response.sendRedirect("controller?command=go_to_base_page");
		}

		else {
			try {
				NewUserInfo newUserInfo = service.signIn(login, password);
				String role = newUserInfo.getRole().getRole();

				request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_ACTIVE);
				request.getSession().setAttribute(JSP_USER_ROLE, role);
				request.getSession().setAttribute(JSP_USER_INFO_PARAM, newUserInfo);
				response.sendRedirect("controller?command=go_to_news_list");
			}
			catch (ServiceException e) {
				request.getSession(true).setAttribute(JSP_AUTHENTICATION_ERROR_PARAM, ErrorHandler.extractErrorMessage(e));
				response.sendRedirect("controller?command=go_to_base_page");
			}
		}
	}

	public boolean isValidData(String login, String password){
		return login.length() >= 1 && password.length() >= 1;
	}

}
