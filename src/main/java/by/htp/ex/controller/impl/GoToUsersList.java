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

public final class GoToUsersList implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static String JSP_USERS_PARAM = "users";
	private final static String JSP_ACTION_PARAM = "action";
	private final static String JSP_USERS_LIST_PARAM = "users_list";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try{
			List<NewUserInfo> usersInfo = userService.getUsers();
			request.setAttribute(JSP_USERS_PARAM, usersInfo);
			request.setAttribute(JSP_ACTION_PARAM, JSP_USERS_LIST_PARAM);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}

