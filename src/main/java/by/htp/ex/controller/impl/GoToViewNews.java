package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import by.htp.ex.bean.Comment;
import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public final class GoToViewNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_NEWS_PARAM = "news";
	private final static String JSP_PRESENTATION_PARAM = "presentation";
	private final static String JSP_VIEW_NEWS_PARAM = "viewNews";
	private final static String JSP_COMMENTS_PARAM = "comments";
	private final static String JSP_LOCALIZATION_PARAM = "localization";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(JSP_ID_PARAM);
		Locale locale = (Locale) request.getSession().getAttribute(JSP_LOCALIZATION_PARAM);
		
		try {
			int parseId = Integer.parseInt(id);
			News news  = newsService.findById(parseId, locale);

			List<Comment> comments = commentService.findByIdNews(parseId);

			request.setAttribute(JSP_COMMENTS_PARAM, comments);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.setAttribute(JSP_PRESENTATION_PARAM, JSP_VIEW_NEWS_PARAM);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
