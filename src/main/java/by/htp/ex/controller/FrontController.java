package by.htp.ex.controller;

import by.htp.ex.controller.command.Command;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


public class FrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private final CommandProvider provider = CommandProvider.getInstance();


	//TODO ТУТ СОЗДАТЬ ТРЕТИЙ МЕТОД КОТОРЫЙ БУДЕТ ВЫПОЛНЯТЬ ДАННЫЕ ЗАПРОСЫ?

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String commandName = request.getParameter("command");
		Command command = provider.getCommand(commandName);
		command.execute(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
