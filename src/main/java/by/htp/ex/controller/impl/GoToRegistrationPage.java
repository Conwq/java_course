package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class GoToRegistrationPage implements Command {

	private final static String JSP_REGISTRATION_PARAM = "registration";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(JSP_REGISTRATION_PARAM, JSP_REGISTRATION_PARAM);
		request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	}
}
