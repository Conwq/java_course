package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_NEWS_PARAM = "news";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsId = request.getParameter(JSP_ID_PARAM);

		try {
			int id = Integer.parseInt(newsId);
			News news = newsService.findById(id);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.getRequestDispatcher("WEB-INF/jsp/edit_news.jsp").forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
