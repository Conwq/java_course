package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;

public class CommentDAO implements ICommentDAO{
	static {
		try {
			Class.forName(ConstantsName.DB_DRIVER);
		}
		catch(ClassNotFoundException e) {
			System.out.println("Class not found");
		}
	}

	@Override
	public List<Comment> findByIdNews(int id) throws DaoException {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			
			return null;
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
	}
	
}
