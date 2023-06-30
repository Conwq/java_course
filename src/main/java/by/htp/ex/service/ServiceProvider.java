package by.htp.ex.service;

import by.htp.ex.service.impl.CommentServiceImpl;
import by.htp.ex.service.impl.NewsServiceImpl;
import by.htp.ex.service.impl.UserServiceImpl;

public final class ServiceProvider {
	private static final ServiceProvider instance = new ServiceProvider();
	private final IUserService userService = new UserServiceImpl();
	private final INewsService newsService = new NewsServiceImpl();
	private final ICommentService commentService = new CommentServiceImpl();
	
	public ICommentService getCommentService() {
		return commentService;
	}
	
	public INewsService getNewsService() {
		return newsService;
	}

	public IUserService getUserService() {
		return userService;
	}

	public static ServiceProvider getInstance() {
		return instance;
	}
}
