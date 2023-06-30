package by.htp.ex.service;

import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.service.exception.ServiceException;

public interface ICommentService {
	
	List<Comment> findByIdNews(int id) throws ServiceException;
}
