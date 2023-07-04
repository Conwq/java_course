package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoUnbanUser implements Command {

	private final IUserService userService = ServiceProvider.getInstance().getUserService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("user_id");
		System.out.println(userId);

		try{
			int parseUserId = Integer.parseInt(userId);
			userService.unbanUser(parseUserId);
			response.sendRedirect("controller?command=go_to_users_list");
		}
		catch (NumberFormatException e){
			e.printStackTrace();
			response.sendRedirect("error/error.jsp");
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
	}
}
