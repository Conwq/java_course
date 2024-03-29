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
	private static final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final String JSP_ID_PARAM = "id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String newsId = request.getParameter(JSP_ID_PARAM);
			newsService.delete(newsId);

			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
