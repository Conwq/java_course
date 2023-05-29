package by.htp.ex.controller.impl;

import java.io.IOException;
import java.util.List;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GoToNewsList implements Command {
	
	private final INewsService newsService = ServiceProvider.getInstance().getNewsService();

	/*
		В данный конкрид контроллер мы приходим когда произошла успешная авторизация пользователя в конкрид контроллере DoSignIn.

		Тут мы получаем с нашего ДАО, пройдя слой сервисов, список всех наших новостей сохраняя его в запросе под ключом "news" и дополнительно в качестве атрибута
		добавляем ключ=значение   "presentation"="newsList".
		Делаем форвард на главную страницу.

		Если с ДАО вернулась ошибка, то мы ее обрабатываем нашим созданным классом Exception
	*/

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<News> newsList;
		try {
			newsList = newsService.list();
			request.setAttribute("news", newsList);
			request.setAttribute("presentation", "newsList");
			//	request.setAttribute("news", null);

			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e) {
			//	Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
