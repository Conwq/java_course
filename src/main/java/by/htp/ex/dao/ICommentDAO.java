package by.htp.ex.dao;

import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.exception.DaoException;

public interface ICommentDAO {
	
	List<Comment> findByIdNews(int id) throws DaoException;
}
