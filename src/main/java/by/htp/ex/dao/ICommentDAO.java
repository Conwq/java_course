package by.htp.ex.dao;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.exception.DaoException;

import java.util.List;

public interface ICommentDAO {
	
	List<Comment> findByIdNews(int id) throws DaoException;
	void addComment(String text, int userId, int newsId) throws DaoException;
	void deleteById(int id) throws DaoException;
	String getTextByIdComment(int id) throws DaoException;

	void editCommentTextById(int id, String text) throws DaoException;
}
