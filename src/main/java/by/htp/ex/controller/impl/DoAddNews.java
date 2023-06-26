package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoAddNews implements Command {

	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//TODO NEED VALIDATION AND ADD ERROR IN JSP PAGE

		String title = request.getParameter("title");
		String briefNews = request.getParameter("brief_news");
		String content = request.getParameter("content");
		String photoPath = request.getParameter("photo");
		String userId = request.getParameter("user_id");

		try {
			int id = Integer.parseInt(userId);

			NewUserInfo newUserInfo = userService.getUser(id);
			News news = new News(title, briefNews, content, photoPath, newUserInfo);
			newsService.save(news);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
