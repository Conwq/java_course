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

public class GoToPersonalCabinet implements Command {
	private static final IUserService userService = ServiceProvider.getInstance().getUserService();
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");

		try{
			int id = Integer.parseInt(idParam);
			NewUserInfo userInfo = userService.getUser(id);
			request.getSession(true).setAttribute("userInfo", userInfo);
			request.getRequestDispatcher("WEB-INF/jsp/personal_cabinet.jsp").forward(request, response);
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
		catch(NumberFormatException e){
			System.out.println(e.getMessage());
		}
	}
}
