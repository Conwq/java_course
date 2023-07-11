package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import java.util.Locale;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public final class GoToBasePage implements Command{
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static String JSP_NEWS_PARAM = "news";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			//TODO ЧТОТО СДЕЛАТЬ С 5 ОНА НЕ ИСПОЛЬЗУЕТСЯ (ДОБАВИТЬ ВОЗМОЖНОСТЬ ВЫБОРА КОЛИЧЕСТВА ОТОБРАЖАЕМЫХ НОВОСТЕЙ?)
			List<News> latestNews = newsService.latestList(5, Locale.getDefault());

			request.setAttribute(JSP_NEWS_PARAM, latestNews);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}

		catch (ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}
