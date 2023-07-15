package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

public final class GoToAddNews implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static String JSP_USERS_PARAM = "users";
	private final static String JSP_ACTION_PARAM = "action";
	private final static String JSP_ADD_NEWS_PARAM = "add_news";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//TODO ДОБАВИТЬ REDIRECT? ПОТОМУ ЧТО В НАШЕЙ БАЗЕ МОЖЕТ БЫТЬ МНОГО ПОЛЬЗОВАТЕЛЕЙ И ПРИ F5 БУДЕТ ПРОИСХОДИТЬ ПОСТОЯННОЕ ОБРАЩЕНИЕ К БД. ХОТЯ ДАННАЯ ВОЗМОЖНОСТЬ ПРИСУТСТВУЕТ ЛИШЬ У АДМИНА

		try {
			List<NewUserInfo> users = userService.getUsers();
			request.setAttribute(JSP_USERS_PARAM, users);
			request.setAttribute(JSP_ACTION_PARAM, JSP_ADD_NEWS_PARAM);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}