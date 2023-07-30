package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;

public final class DoEditNews implements Command {
	private static final INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private static final String JSP_ID_PARAM = "id";
	private static final String JSP_TITLE_PARAM = "title";
	private static final String JSP_BRIEF_NEWS_PARAM = "brief_news";
	private static final String JSP_CONTENT_PARAM = "content";
	private static final String JSP_PHOTO_PARAM = "photo";
	private static final String JSP_PHOTO_PATH_PARAM = "photo_path";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String id = request.getParameter(JSP_ID_PARAM);
			String title = request.getParameter(JSP_TITLE_PARAM);
			String briefNews = request.getParameter(JSP_BRIEF_NEWS_PARAM);
			String content = request.getParameter(JSP_CONTENT_PARAM);

			Part imagePart = request.getPart(JSP_PHOTO_PARAM);
			String pathToImage = request.getParameter(JSP_PHOTO_PATH_PARAM);

			if (imagePart.getSize() > 0){
				pathToImage = getPathToSavedImage(imagePart, request);
			}

			int parseId = Integer.parseInt(id);
			News news = new News(parseId, title, briefNews, content, pathToImage);
			newsService.update(news);
			response.sendRedirect("controller?command=go_to_view_news&id=" + id);
		}
		catch (NumberFormatException | ServiceException e) {
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
