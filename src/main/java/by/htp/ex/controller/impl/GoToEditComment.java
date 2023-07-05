package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class GoToEditComment implements Command {
	private final static ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private final static String JSP_COMMENT_ID_PARAM = "comment_id";
	private final static String JSP_NEWS_ID_PARAM = "news_id";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commentId = request.getParameter(JSP_COMMENT_ID_PARAM);
		String newsId = request.getParameter(JSP_NEWS_ID_PARAM);
		try{
			int parseCommentId = Integer.parseInt(commentId);
			int parseNewsId = Integer.parseInt(newsId);

			String text = commentService.getTextByIdComment(parseCommentId);

			request.setAttribute("comment_id", parseCommentId);
			request.setAttribute("comment_text", text);
			request.getRequestDispatcher("controller?command=go_to_view_news&id=" + parseNewsId).forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			e.printStackTrace();
		}
	}
}