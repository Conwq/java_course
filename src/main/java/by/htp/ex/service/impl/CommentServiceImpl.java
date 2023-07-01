package by.htp.ex.service.impl;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;

public class CommentServiceImpl implements ICommentService{
	private final static ICommentDAO commentDAO = DaoProvider.getInstance().getCommentDao();

	@Override
	public List<Comment> findByIdNews(int id) throws ServiceException{
		try {
			return commentDAO.findByIdNews(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void addComment(String text, int userId, int newsId) throws ServiceException {
		try {
			commentDAO.addComment(text, userId, newsId);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}
}
