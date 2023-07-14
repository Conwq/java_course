package by.htp.ex.controller.impl;

import by.htp.ex.bean.News;
import by.htp.ex.controller.command.Command;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.ServiceProvider;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.util.CookiesHelper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public final class GoToBasePage implements Command{
	private final static INewsService newsService = ServiceProvider.getInstance().getNewsService();
	private final static CookiesHelper cookies = CookiesHelper.getInstance();
	private final static String JSP_NEWS_PARAM = "news";
	private final static String COOKIE_NAME = "my_cookie";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			Cookie cookie;
			if ((cookie = cookies.getCookie(request, COOKIE_NAME)) != null){
				response.sendRedirect("controller?command=do_sign_in&cookie_value=" + cookie.getValue());
				return;
			}

			List<News> latestNews = newsService.latestList(5, Locale.getDefault());

			request.setAttribute(JSP_NEWS_PARAM, latestNews);
			request.getRequestDispatcher("WEB-INF/pages/layouts/baseLayout.jsp").forward(request, response);
		}

		catch (ServiceException e) {
			response.sendRedirect("error/error.jsp");
		}
	}
}
