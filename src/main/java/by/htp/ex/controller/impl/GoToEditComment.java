package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class GoToEditComment implements Command {
	private final static ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private final static String JSP_COMMENT_ID_PARAM = "comment_id";
	private final static String JSP_NEWS_ID_PARAM = "news_id";
	private final static String JSP_COMMENT_TEXT_PARAM = "comment_text";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			String commentId = request.getParameter(JSP_COMMENT_ID_PARAM);
			String newsId = request.getParameter(JSP_NEWS_ID_PARAM);
			String text = commentService.getTextByIdComment(commentId);

			request.setAttribute(JSP_COMMENT_ID_PARAM, commentId);
			request.setAttribute(JSP_COMMENT_TEXT_PARAM, text);
			request.getRequestDispatcher("controller?command=go_to_view_news&id=" + newsId).forward(request, response);
		}
		catch(ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
