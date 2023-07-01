package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

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
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private static final String JSP_ID_PARAM = "id";
	private static final String JSP_NEWS_PARAM = "news";
	private static final String JSP_PRESENTATION_PARAM = "presentation";
	private static final String JSP_VIEW_NEWS_PARAM = "viewNews";
	private static final String JSP_COMMENTS_PARAM = "comments";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(JSP_ID_PARAM);
		
		//TODO получить здесь по id_news новости комментарии, которые относятся к данной новости

		try {
			int parseId = Integer.parseInt(id);
			News news  = newsService.findById(parseId);


			//TODO получаем комментарии которые относится к нашей новости
			List<Comment> comments = commentService.findByIdNews(parseId);
			
			request.setAttribute(JSP_COMMENTS_PARAM, comments);
			request.setAttribute(JSP_NEWS_PARAM, news);
			request.setAttribute(JSP_PRESENTATION_PARAM, JSP_VIEW_NEWS_PARAM);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			e.printStackTrace();
			response.sendRedirect("/error/error.jsp");
		}
	}
}
