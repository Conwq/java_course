package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToViewNews implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	/*
		В данынй конкрид контроллер мы переходим, когда авторизованный пользователь в baseLayout -> body -> newsList нажимает на кнопку View News.
		Эта кнопка является реферальной ссылкой на FrontController с командой go_to_view_news и при помащи паттерна Command она переходит в данный конкрид контроллер

	 */

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		News news;
		
		String id;

		id = request.getParameter("id");
		
		try {
			news  = newsService.findById(Integer.parseInt(id));
			request.setAttribute("news", news);
			request.setAttribute("presentation", "viewNews");

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
