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
import java.util.Locale;

public final class GoToEditNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_NEWS_PARAM = "news";
	private final static String JSP_ACTION_PARAM = "action";
	private final static String JSP_EDIT_NEWS_PARAM = "edit_news";
	private final static String JSP_LOCALIZATION_PARAM = "localization";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsId = request.getParameter(JSP_ID_PARAM);
		Locale locale = (Locale) request.getSession().getAttribute(JSP_LOCALIZATION_PARAM);

		try {
			int id = Integer.parseInt(newsId);
			News news = newsService.findById(id, locale);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.setAttribute(JSP_ACTION_PARAM, JSP_EDIT_NEWS_PARAM);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (NumberFormatException e) {
			response.sendRedirect("/error/error.jsp");
		}
		catch (ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}