package by.htp.ex.dao;

import by.htp.ex.dao.impl.CommentDAO;
import by.htp.ex.dao.impl.NewsDAO;
import by.htp.ex.dao.impl.UserDAO;

public final class DaoProvider {
	private final static DaoProvider instance = new DaoProvider();
	private final static IUserDAO userDao = new UserDAO();
	private final static INewsDAO newsDAO = new NewsDAO();
	private final static ICommentDAO commentDAO = new CommentDAO();
	
	private DaoProvider() {
	}
	
	public ICommentDAO getCommentDao() {
		return commentDAO;
	}
	
	public IUserDAO getUserDao() {
		return userDao;
	}
	
	public INewsDAO getNewsDAO() {
		return newsDAO;
	}

	public static DaoProvider getInstance() {
		return instance;
	}
}
