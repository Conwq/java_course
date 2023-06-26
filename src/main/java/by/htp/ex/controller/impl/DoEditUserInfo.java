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

public class DoEditUserInfo implements Command {
	private static final IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_LOGIN_PARAM = "login";
	private final static String JSP_EMAIL_PARAM = "email";
	private final static String JSP_PASSWORD_PARAM = "password";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String idParam = request.getParameter(JSP_ID_PARAM);
		String login = request.getParameter(JSP_LOGIN_PARAM);
		String email = request.getParameter(JSP_EMAIL_PARAM);
		String password = request.getParameter(JSP_PASSWORD_PARAM);

		try{
			int id = Integer.parseInt(idParam);
			NewUserInfo newUserInfo = new NewUserInfo(id, login, email, password);
			userService.updateUserInfo(newUserInfo);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
