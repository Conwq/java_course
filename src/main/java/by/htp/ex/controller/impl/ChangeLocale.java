package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public final class ChangeLocale implements Command {
	private final static String JSP_LOCALE_PARAM = "locale";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute(JSP_LOCALE_PARAM, request.getParameter(JSP_LOCALE_PARAM));

		try {
			String referer = getRefererPath(request.getHeader("referer"));
			response.sendRedirect(referer);
		}
		catch (URISyntaxException e){
			response.sendRedirect("error/error.jsp");
		}
	}

	private String getRefererPath(String referer) throws URISyntaxException {
		String path = "index.jsp";

		if (referer != null){
			URI uri = new URI(referer);
			path = uri.getPath();
			String query = uri.getQuery();

			if (query != null){
				path += "?" + query;
			}
		}
		return path;
	}
}
