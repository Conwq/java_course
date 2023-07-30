package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoBanUser implements Command{
	private static final IUserService userService = ServiceProvider.getInstance().getUserService(); 
	private static final String JSP_USER_ID_PARAM = "user_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String userId = request.getParameter(JSP_USER_ID_PARAM);
			userService.banUser(userId);
			response.sendRedirect("controller?command=go_to_users_list");
		}
		catch(ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}
