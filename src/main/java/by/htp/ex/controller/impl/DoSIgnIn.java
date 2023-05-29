package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.IUserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoSIgnIn implements Command {



	/*
		Данный метод сработает, когда пользователь нажмет клавишу Sign in. Она отправит пользователя на FrontController с командой do_sign_in, которую обработает Command
		и отправит на текущий Конкрид контроллер.
		В данном метода происходит следующая логика:

				- HTTP request "вынимает из формы" параметры, которые пользователь отправил на сервер.

				- Данные проходят небольшую валидацию логина и пароля

				- Данные отправляются в БД для того, чтобы определить, под какой ролью находится указанный юзер:

						Если роль не эквивалентна "guest", то мы открываем сессию (getSession(true)), в нашу сессию помещаем текущего пользователя со значение "active" , что означает, что
						данный пользователь в системе есть и мы его активировали. Так же в качестве атрибута в сессию вкладываем его роль и совершаем redirect
						на контроллер с командой = go_to_news_list.

						Иначе вкладываем в сессию юзера со значением "not active" , а так же вкладываем ошибку под ключом "AuthenticationError"
						и значением "wrong login or password". После чего делаем форвард на главную страницу. Если авторизация не пройдет, то на главной странице
						сы динамически отобразим данную ошибку.
	*/

	private final IUserService service = ServiceProvider.getInstance().getUserService();
	private static final String JSP_LOGIN_PARAM = "login";
	private static final String JSP_PASSWORD_PARAM = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		login = request.getParameter(JSP_LOGIN_PARAM);
		password = request.getParameter(JSP_PASSWORD_PARAM);


		try {
			String role = service.signIn(login, password);
			if (!role.equals("guest")) {
				request.getSession(true).setAttribute("user", "active");
				request.getSession().setAttribute("role", role);
				response.sendRedirect("controller?command=go_to_news_list");
			}

			else {
				request.getSession(true).setAttribute("user", "not active");
				request.setAttribute("AuthenticationError", "wrong login or password");
				request.getRequestDispatcher("controller?command=go_to_base_page").forward(request, response);
			}
			
		}
		catch (ServiceException e) {

		}
	}

}
