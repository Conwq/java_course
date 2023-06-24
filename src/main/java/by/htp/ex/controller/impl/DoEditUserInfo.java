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

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");
		String login = request.getParameter("login");
		String email = request.getParameter("email");
		String password = request.getParameter("password");

		try{
			int id = Integer.parseInt(idParam);
			NewUserInfo newUserInfo = new NewUserInfo(id, login, email, password);
			userService.updateUserInfo(newUserInfo);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (ServiceException e){
			System.out.println(e.getMessage());
		}
		catch (NumberFormatException e){
			e.printStackTrace();
		}


	}
}
