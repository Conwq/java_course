package by.htp.ex.controller.impl;

import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DoDeleteNews implements Command {
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idParam = request.getParameter("id");

		try {
			int id = Integer.parseInt(idParam);
			newsService.delete(id);
			response.sendRedirect("controller?command=go_to_news_list");
		}
		catch (NumberFormatException e){
			System.out.println("Данного id не существвует - " + idParam);
		}
		catch (ServiceException e){
			e.printStackTrace();
		}
	}
}
