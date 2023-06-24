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

public class GoToUsersList implements Command {
	private static final IUserService userService = ServiceProvider.getInstance().getUserService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try{
			List<NewUserInfo> usersInfo = userService.getUsers();
			request.setAttribute("users", usersInfo);
			request.getRequestDispatcher("WEB-INF/jsp/users_list.jsp").forward(request, response);
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
	}
}

