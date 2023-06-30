package by.htp.ex.controller.impl;

import java.io.IOException;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DoAddComment implements Command{
	
	private final static String JSP_COMMENT_PARAM = "comment";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String comment = (String) request.getAttribute(JSP_COMMENT_PARAM);
	}
	
}
