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

public final class GoToBasePage implements Command{
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final String JSP_NEWS_PARAM = "news";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {

			//TODO ЧТОТО СДЕЛАТЬ С 5 ОНА НЕ ИСПОЛЬЗУЕТСЯ (ДОБАВИТЬ ВОЗМОЖНОСТЬ ВЫБОРА КОЛИЧЕСТВА ОТОБРАЖАЕМЫХ НОВОСТЕЙ?)

			List<News> latestNews = newsService.latestList(5);
			String active = (String) request.getAttribute("user");
			if (active == null || active.equals("not active")){
				active = "not active";
			}
			request.getSession(true).setAttribute("user", active);
			request.setAttribute(JSP_NEWS_PARAM, latestNews);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}

		catch (ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}
