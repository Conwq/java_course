package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class DoSignOut implements Command {
	private final static String JSP_USER_PARAM = "user";
	private final static String JSP_USER_NOT_ACTIVE_PARAM = "not active";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(JSP_USER_PARAM, JSP_USER_NOT_ACTIVE_PARAM);
		response.sendRedirect("index.jsp");
	}
}
