package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Locale;

public final class GoToRegistrationPage implements Command {
	private static final String JSP_REGISTRATION_PARAM = "registration";
	private static final String JSP_ACTION_PARAM = "action";
	private static final String JSP_LOCAL_PARAM = "local";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(JSP_ACTION_PARAM, JSP_REGISTRATION_PARAM);
		request.setAttribute(JSP_LOCAL_PARAM, Locale.getDefault());
		request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
	}
}
