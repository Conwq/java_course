package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoRegistration implements Command {
	private final String LOGIN = "login";
	private final String EMAIL = "email";
	private final String PASSWORD = "password";

	private final IUserService userService = ServiceProvider.getInstance().getUserService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		NewUserInfo user = new NewUserInfo(
				request.getParameter(LOGIN),
				request.getParameter(EMAIL),
				request.getParameter(PASSWORD),
				Role.USER);

		try {
			userService.registration(user);
			response.sendRedirect("controller?command=go_to_base_page");
		}

		catch (ServiceException e) {
			System.out.println(e.getMessage());
		}
	}
}
