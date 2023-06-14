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

public final class DoRegistration implements Command {
	private final String LOGIN = "login";
	private final String EMAIL = "email";
	private final String PASSWORD = "password";
	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String login = request.getParameter(LOGIN);
		String email = request.getParameter(EMAIL);
		String password = request.getParameter(PASSWORD);

		request.getSession().removeAttribute("RegistrationError");

		if (dataValidator(login, email, password)){
			request.getSession(true).setAttribute("RegistrationError", "Login/Email/Password error. The number of characters must not be less than 2");
			response.sendRedirect("controller?command=go_to_registration_page");
		}

		else {
			//TODO ЭТО ИЗМЕНИТЬ НА ПАТТЕРН BUILDER
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

	public boolean dataValidator(String login, String email, String password){
		return (login.length() < 2 || email.length() < 2 || password.length() < 2);
	}
}
