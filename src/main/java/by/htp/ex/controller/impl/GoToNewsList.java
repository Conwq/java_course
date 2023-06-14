package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class GoToNewsList implements Command {
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final String JSP_NEWS_PARAM = "news";
	private final String JSP_PRESENTATION_PARAM = "presentation";
	private final String JSP_NEWS_LIST_PARAM = "newsList";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			newsList = newsService.list();
			request.setAttribute(JSP_NEWS_PARAM, newsList);
			request.setAttribute(JSP_PRESENTATION_PARAM, JSP_NEWS_LIST_PARAM);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
