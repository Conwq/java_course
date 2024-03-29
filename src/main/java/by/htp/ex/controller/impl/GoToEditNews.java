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
	private static final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final String JSP_ID_PARAM = "id";
	private static final String JSP_NEWS_PARAM = "news";
	private static final String JSP_ACTION_PARAM = "action";
	private static final String JSP_EDIT_NEWS_PARAM = "edit_news";
	private static final String JSP_LOCALIZATION_PARAM = "localization";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsId = request.getParameter(JSP_ID_PARAM);
		Locale locale = (Locale) request.getSession().getAttribute(JSP_LOCALIZATION_PARAM);

		try {
			News news = newsService.findById(newsId, locale);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.setAttribute(JSP_ACTION_PARAM, JSP_EDIT_NEWS_PARAM);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}