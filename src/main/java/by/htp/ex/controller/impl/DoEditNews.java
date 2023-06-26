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

public class DoEditNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_TITLE_PARAM = "title";
	private final static String JSP_BRIEF_NEWS_PARAM = "brief_news";
	private final static String JSP_CONTENT_PARAM = "content";
	private final static String JSP_DATE_PARAM = "date";
	private final static String JSP_PHOTO_PARAM = "photo";


	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(JSP_ID_PARAM);
		String title = request.getParameter(JSP_TITLE_PARAM);
		String briefNews = request.getParameter(JSP_BRIEF_NEWS_PARAM);
		String content = request.getParameter(JSP_CONTENT_PARAM);
		String date = request.getParameter(JSP_DATE_PARAM);
		String photo = request.getParameter(JSP_PHOTO_PARAM);

		try {
			int parseId = Integer.parseInt(id);
			News news = new News(parseId, title, briefNews, content, date, photo);
			newsService.update(news);
			response.sendRedirect("controller?command=go_to_view_news&id=" + id);
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
