package by.htp.ex.service;

import by.htp.ex.bean.Comment;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface ICommentService {
	List<Comment> findByIdNews(String id, Locale locale) throws ServiceException;
	void addComment(String text, String userId, String newsId) throws ServiceException;
	void deleteById(String id) throws ServiceException;
	String getTextByIdComment(String id) throws ServiceException;
	void editCommentTextById(String id, String text) throws ServiceException;
}
