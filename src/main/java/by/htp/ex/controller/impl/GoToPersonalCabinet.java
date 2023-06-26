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
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_USER_INFO_PARAM = "userInfo";
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter(JSP_ID_PARAM);

		try{
			int id = Integer.parseInt(idParam);
			NewUserInfo userInfo = userService.getUser(id);
			request.getSession(true).setAttribute(JSP_USER_INFO_PARAM, userInfo);
			request.getRequestDispatcher("WEB-INF/jsp/personal_cabinet.jsp").forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("/error/error.jsp");
		}
	}
}
