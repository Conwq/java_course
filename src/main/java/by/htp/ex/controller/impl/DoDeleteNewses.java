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
	private final static String JSP_ERROR_PARAM = "error_delete_newses";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String[] newses = request.getParameterValues("select_newses");

			if (newses == null){
				request.getSession(true).setAttribute(JSP_ERROR_PARAM, "Please select at least one item to delete");
				response.sendRedirect("controller?command=go_to_news_list_e");
				return;
			}

			newsService.deleteNewses(newses);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch(ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
