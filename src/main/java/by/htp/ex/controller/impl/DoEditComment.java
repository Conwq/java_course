package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.validation.CommentDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoEditComment implements Command {
	private final static ICommentService commentService = ServiceProvider.getInstance().getCommentService();
	private final static CommentDataValidation validator = ValidationProvider.getInstance().getCommentDataValidation();
	private final static String JSP_TEXT_PARAM = "text";
	private final static String JSP_COMMENT_ID_PARAM = "comment_id";
	private final static String JSP_NEWS_ID_PARAM = "news_id";
	private final static String JSP_ERROR_PARAM = "error";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter(JSP_TEXT_PARAM);
		String commentId = request.getParameter(JSP_COMMENT_ID_PARAM);
		String newsId = request.getParameter(JSP_NEWS_ID_PARAM);

		request.getSession().removeAttribute(JSP_ERROR_PARAM);

		if (!validator.textValidator(text)){
			request.getSession(true).setAttribute(JSP_ERROR_PARAM, "The number of characters in the text must be more than two");
			request.getRequestDispatcher("controller?command=go_to_edit_comment&comment_id=" + commentId + "&news_id=" + newsId).forward(request, response);
			return;
		}

		try {
			int parseCommentId = Integer.parseInt(commentId);
			int parseNewsId = Integer.parseInt(newsId);
			commentService.editCommentTextById(parseCommentId, text);
			response.sendRedirect("controller?command=go_to_view_news&id=" + parseNewsId);
		}
		catch (ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
		catch (NumberFormatException e){
			request.setAttribute(JSP_ERROR_PARAM, "Current comment not exist");
			request.getRequestDispatcher("/error/error.jsp").forward(request, response);
		}
	}
}
