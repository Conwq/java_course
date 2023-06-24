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

public class GoToEditNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsId = request.getParameter("id");
		int id = Integer.parseInt(newsId);

		try {
			News news = newsService.findById(id);
			request.setAttribute("news", news);
			request.getRequestDispatcher("WEB-INF/jsp/edit_news.jsp").forward(request, response);
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
	}
}
