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

public final class GoToPersonalCabinet implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static String JSP_ID_PARAM = "id";
	private final static String JSP_USER_INFO_PARAM = "userInfo";
	private final static String JSP_PERSONAL_CABINET_PARAM = "personal_cabinet";
	private final static String JSP_ACTION_PARAM = "action";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter(JSP_ID_PARAM);

		try{
			NewUserInfo userInfo = userService.getUser(id);
			request.getSession(true).setAttribute(JSP_USER_INFO_PARAM, userInfo);
			request.setAttribute(JSP_ACTION_PARAM, JSP_PERSONAL_CABINET_PARAM);
			request.getRequestDispatcher("/WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}
		catch (NumberFormatException | ServiceException e){
			response.sendRedirect("error/error.jsp");
		}
	}
}
