package by.htp.ex.controller.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.validation.UserDataValidation;
import by.htp.ex.util.validation.ValidationProvider;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class DoEditUserInfo implements Command {
	private final static IUserService userService = ServiceProvider.getInstance().getUserService();
	private final static UserDataValidation validation = ValidationProvider.getInstance().getUserValidator();
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
		
		if(validation.isValidData(login, email, password)) {
			response.sendRedirect("/error/error.jsp");
			return;
		}
		
		try {
			int id = Integer.parseInt(idParam);
			String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
			NewUserInfo newUserInfo = new NewUserInfo(id, login, email, hashedPassword);
 			userService.updateUserInfo(newUserInfo);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException | ServiceException e){
			e.printStackTrace();
			response.sendRedirect("/error/error.jsp");
		}
	}
}
