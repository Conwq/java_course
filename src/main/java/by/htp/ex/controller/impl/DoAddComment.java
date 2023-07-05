package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public final class DoAddComment implements Command{
	
	private final static String JSP_TEXT_PARAM = "text";
	private final static String JSP_USER_ID_PARAM = "user_id";
	private final static String JSP_NEWS_ID_PARAM = "news_id";

	private final static ICommentService commentService = ServiceProvider.getInstance().getCommentService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text = request.getParameter(JSP_TEXT_PARAM);

		try {
			int userId = Integer.parseInt(request.getParameter(JSP_USER_ID_PARAM));
			int newsId = Integer.parseInt(request.getParameter(JSP_NEWS_ID_PARAM));

			commentService.addComment(text, userId, newsId);

			response.sendRedirect("controller?command=go_to_view_news&id=" + newsId);
		}
		catch (NumberFormatException e){
			e.printStackTrace();
		}
		catch (ServiceException e){
			System.out.println(e.getMessage());
		}
	}
}
