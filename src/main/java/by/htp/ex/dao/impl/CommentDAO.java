package by.htp.ex.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;
import by.htp.ex.util.DatabaseHelper;

public class CommentDAO implements ICommentDAO{
	private final static DatabaseHelper helper = DatabaseHelper.getInstance();
	
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
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<Comment> comments = null;
		
		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			
			//TODO написать JOIN на получение данных и из user и из news
//			preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE news_id = ?");
			preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE news_id = ? "
															+ "JOIN news on commets.news_id = news.news_id "
															+ "JOIN users ON news.users_id = users.id");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();
			comments = new ArrayList<>();
			
			while (resultSet.next()){
				Comment comment = new Comment();
				comment.setText(resultSet.getString("text"));
				
				//TODO тут нужно форматировать дату, для того, чтобы ее добавить в качестве поля объекту
				//TODO обязательно проверить
				comment.setDate(resultSet.getDate("publication_date"));
				
				//TODO тоже исправить, придумать какой нибудь маппер + добавить SQL запрос на получение новости по id из БД
//				comment.setNews((News)(resultSet.getObject("news_id")));
				
				//TODO тоже исправить, придумать какой нибудь маппер + добавить SQL запрос на получение новости по id из БД
//				comment.setNewUserInfo((NewUserInfo) (resultSet.getObject("user_id")));
				
				//TODO проверить чтобы не дублировались название колонок, а то может быть ошибка
				comment.setNewUserInfo(helper.parseUserInfo(resultSet));
				
				comment.setNews(helper.parseNews(resultSet));
				
				comments.add(comment);
			}
			
			return comments;
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
		}
	}
	
}
