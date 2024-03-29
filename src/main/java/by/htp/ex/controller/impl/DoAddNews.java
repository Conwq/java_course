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
import jakarta.servlet.http.Part;

import java.io.IOException;

public final class DoAddNews implements Command {
	private static final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final IUserService userService = ServiceProvider.getInstance().getUserService();
	private static final String JSP_TITLE_PARAM = "title";
	private static final String JSP_BRIEF_NEWS_PARAM = "brief_news";
	private static final String JSP_CONTENT_PARAM = "content";
	private static final String JSP_USER_ID_PARAM = "user_id";
	private static final String JSP_PHOTO_PARAM = "photo";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String title = request.getParameter(JSP_TITLE_PARAM);
			String briefNews = request.getParameter(JSP_BRIEF_NEWS_PARAM);
			String content = request.getParameter(JSP_CONTENT_PARAM);
			String id = request.getParameter(JSP_USER_ID_PARAM);
			Part imagePart = request.getPart(JSP_PHOTO_PARAM);
			String pathToImage = null;

			if (imagePart.getSize() > 0){
				pathToImage = getPathToSavedImage(imagePart, request);
			}

			NewUserInfo newUserInfo = userService.getUser(id);
			News news = new News(title, briefNews, content, pathToImage, newUserInfo);
			newsService.save(news);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (ServiceException | IOException e){
			response.sendRedirect("error/error.jsp");
		}
	}

	private String getPathToSavedImage(Part imagePart, HttpServletRequest request) throws IOException{
		String fileName = imagePart.getSubmittedFileName();
		String pathToImage = request.getServletContext().getRealPath("/images/") + fileName;
		imagePart.write(pathToImage);
		return "images/" + fileName;
	}
}
