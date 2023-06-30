package by.htp.ex.service.impl;

import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.exception.ServiceException;

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
}
