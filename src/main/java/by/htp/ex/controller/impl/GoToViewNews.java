package by.htp.ex.controller.impl;

import by.htp.ex.bean.Comment;
import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public final class GoToViewNews implements Command {
	private static final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private static final String JSP_ID_PARAM = "id";
	private static final String JSP_NEWS_PARAM = "news";
	private static final String JSP_PRESENTATION_PARAM = "presentation";
	private static final String JSP_VIEW_NEWS_PARAM = "viewNews";
	private static final String JSP_COMMENTS_PARAM = "comments";
	private static final String JSP_LOCALIZATION_PARAM = "localization";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter(JSP_ID_PARAM);
			Locale locale = (Locale) request.getSession().getAttribute(JSP_LOCALIZATION_PARAM);
			News news  = newsService.findById(id, locale);
			List<Comment> comments = commentService.findByIdNews(id, locale);

			request.setAttribute(JSP_COMMENTS_PARAM, comments);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.setAttribute(JSP_PRESENTATION_PARAM, JSP_VIEW_NEWS_PARAM);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
