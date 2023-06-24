package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoEditNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String briefNews = request.getParameter("brief_news");
		String content = request.getParameter("content");
		String date = request.getParameter("date");
		String photo = request.getParameter("photo");


		try {
			int parseId = Integer.parseInt(id);
			News news = new News(parseId, title, briefNews, content, date, photo);
			newsService.update(news);
			response.sendRedirect("controller?command=go_to_view_news&id=" + id);
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
		catch (NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}
}
