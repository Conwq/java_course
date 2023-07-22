package by.htp.ex.service.impl;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.ICommentService;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public final class CommentServiceImpl implements ICommentService{
	private final static ICommentDAO commentDAO = DaoProvider.getInstance().getCommentDao();

	@Override
	public List<Comment> findByIdNews(String id, Locale locale) throws ServiceException{
		try {
			int newsId = Integer.parseInt(id);
			return commentDAO.findByIdNews(newsId, locale);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("Comment with this id not found", e);
		}
	}

	@Override
	public void editCommentTextById(String id, String text) throws ServiceException{
		try {
			int commentId = Integer.parseInt(id);
			commentDAO.editCommentTextById(commentId, text);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("Comment with this id not found", e);
		}
	}

	@Override
	public void addComment(String text, String userId, String newsId) throws ServiceException {
		try {
			int convertUserId = Integer.parseInt(userId);
			int convertNewsId = Integer.parseInt(newsId);
			commentDAO.addComment(text, convertUserId, convertNewsId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("Comment with this id not found", e);
		}
	}

	@Override
	public void deleteById(String id) throws ServiceException {
		try {
			int commentId = Integer.parseInt(id);
			commentDAO.deleteById(commentId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("Comment with this id not found", e);
		}
	}

	@Override
	public String getTextByIdComment(String id) throws ServiceException {
		try {
			int commentId = Integer.parseInt(id);
			return commentDAO.getTextByIdComment(commentId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("Comment with this id not found", e);
		}
	}
}
