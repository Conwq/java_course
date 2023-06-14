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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);

		//TODO ТУТ ПРОВЕСТИ ЛЕГКУЮ ВАЛИДАЦИЮ ДАННЫХ

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
				request.setAttribute("AuthenticationError", "wrong login or password");
				request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);
			}
		}

		catch (ServiceException e) {

		}
	}
}
