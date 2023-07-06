package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.util.UserActivationCheck;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class ChangeLocale implements Command {
	private final static UserActivationCheck activationCheck = UserActivationCheck.getInstance();
	private final static String JSP_LOCALE_PARAM = "locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(JSP_LOCALE_PARAM, request.getParameter(JSP_LOCALE_PARAM));
		boolean isActive = activationCheck.isUserActive(request);
		String path = isActive ? "controller?command=go_to_news_list" : "index.jsp";

		response.sendRedirect(path);
	}
}
