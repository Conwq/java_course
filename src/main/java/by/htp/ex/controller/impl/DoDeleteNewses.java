package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public final class DoDeleteNewses implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] newses = request.getParameterValues("select_newses");

		try {
			newsService.deleteNewses(newses);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch(NullPointerException e){
			response.sendRedirect("/error/error.jsp");
		}
		catch(ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
