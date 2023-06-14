package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ChangeLocale implements Command {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getSession(true).setAttribute("locale", request.getParameter("locale"));
		System.out.println(request.getSession().getAttribute("user"));
		if (request.getSession().getAttribute("user") == null || !(request.getSession().getAttribute("user").equals("active")))
			response.sendRedirect("index.jsp");
		else
			response.sendRedirect("controller?command=go_to_news_list");
	}
}
