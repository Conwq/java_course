package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoEditComment implements Command {
	private static final ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private static final String JSP_TEXT_PARAM = "text";
	private static final String JSP_COMMENT_ID_PARAM = "comment_id";
	private static final String JSP_NEWS_ID_PARAM = "news_id";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String text = request.getParameter(JSP_TEXT_PARAM);
			String commentId = request.getParameter(JSP_COMMENT_ID_PARAM);
			String newsId = request.getParameter(JSP_NEWS_ID_PARAM);
			commentService.editCommentTextById(commentId, text);

			response.sendRedirect("controller?command=go_to_view_news&id=" + newsId);
		}
		catch (ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
