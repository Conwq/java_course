package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoDeleteNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static String JSP_ID_PARAM = "id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter(JSP_ID_PARAM);

		try {
			int id = Integer.parseInt(idParam);
			newsService.delete(id);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException | ServiceException e){
			e.printStackTrace();
			response.sendRedirect("/error/error.jsp");
		}
	}
}
