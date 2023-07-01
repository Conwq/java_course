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
			preparedStatement = connection.prepareStatement("SELECT * FROM news "
															+ "JOIN comments ON news.news_id = comments.news_id "
															+ "JOIN users ON comments.users_id = users.id "
															+ "WHERE news.news_id = ?");
			preparedStatement.setInt(1, id);
			resultSet = preparedStatement.executeQuery();

			comments = new ArrayList<>();
			
			while (resultSet.next()){
				Comment comment = new Comment();
				comment.setCommentId(resultSet.getInt("comment_id"));
				comment.setText(resultSet.getString("text"));
				comment.setDate(resultSet.getTimestamp("date_comment"));
				comment.setNewUserInfo(helper.parseUserInfo(resultSet));
				comment.setNews(helper.parseNews(resultSet));
				comments.add(comment);
			}
		}
		catch(SQLException e) {
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
		}
		return comments;
	}

	@Override
	public void addComment(String text, int userId, int newsId) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try{
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			preparedStatement = connection.prepareStatement("INSERT INTO comments (text, news_id, users_id) VALUES (?,?,?)");
			preparedStatement.setString(1, text);
			preparedStatement.setInt(2, newsId);
			preparedStatement.setInt(3, userId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
		finally{
			helper.closeConnectionResources(connection, preparedStatement);
		}
	}
}
