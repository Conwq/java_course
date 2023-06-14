package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		String id = request.getParameter("id");
		
		try {

			//TODO ДОБАВИТЬ ПРОВЕРКУ ИБО СДЕСЬ МОЖЕТ НЕ РАСПАРСИТЬ
			news  = newsService.findById(Integer.parseInt(id));

			request.setAttribute("news", news);
			request.setAttribute("presentation", "viewNews");
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e) {
			e.printStackTrace();
		}
	}
}
