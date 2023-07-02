package by.htp.ex.service;

import by.htp.ex.bean.Comment;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;

public interface ICommentService {
	
	List<Comment> findByIdNews(int id) throws ServiceException;
	void addComment(String text, int userId, int newsId) throws ServiceException;
	void deleteById(int id) throws ServiceException;
	String getTextByIdComment(int id) throws ServiceException;

	void editCommentTextById(int id, String text) throws ServiceException;
}
