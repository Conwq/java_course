package by.htp.ex.dao.impl;

import by.htp.ex.bean.Comment;
import by.htp.ex.dao.ICommentDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.Converter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class CommentDAO implements ICommentDAO{
	private final static Converter converter = Converter.getInstance();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();
	private final static String DB_TEXT_COLUMN = "text";
	private final static String DB_COMMENT_ID_COLUMN = "comment_id";
	private final static String DB_DATE_COMMENT_COLUMN = "date_comment";

	private final static String SQL_TO_DELETE_COMMENT = "DELETE FROM comments WHERE comment_id = ?";
	@Override
	public void deleteById(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_DELETE_COMMENT)){
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (ConnectionPoolException | SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_TO_GET_COMMENT = "SELECT text FROM comments WHERE comment_id = ?";
	@Override
	public String getTextByIdComment(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_TO_GET_COMMENT)){
			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			resultSet.next();

			return resultSet.getString(DB_TEXT_COLUMN);
		}
		catch (ConnectionPoolException | SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_GET_ALL_COMMENTS_FROM_NEWS = "SELECT * FROM news JOIN comments ON news.news_id = comments.news_id "
			+ "JOIN users ON comments.users_id = users.id WHERE news.news_id = ?";
	@Override
	public List<Comment> findByIdNews(int id) throws DaoException {
		List<Comment> comments = null;
		
		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_GET_ALL_COMMENTS_FROM_NEWS)) {

			preparedStatement.setInt(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();

			comments = new ArrayList<>();
			
			while (resultSet.next()){
				Comment comment = new Comment();
				comment.setCommentId(resultSet.getInt(DB_COMMENT_ID_COLUMN));
				comment.setText(resultSet.getString(DB_TEXT_COLUMN));
				comment.setDate(converter.convertDate(resultSet.getString(DB_DATE_COMMENT_COLUMN)));
				comment.setNewUserInfo(converter.convertNewUserInfo(resultSet));
				comments.add(comment);
			}
		}
		catch(SQLException | ConnectionPoolException e) {
			throw new DaoException(e);
		}
		return comments;
	}

	private final static String SQL_ADD_COMMENT = "INSERT INTO comments (text, news_id, users_id) VALUES (?,?,?)";
	@Override
	public void addComment(String text, int userId, int newsId) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_COMMENT)){

			preparedStatement.setString(1, text);
			preparedStatement.setInt(2, newsId);
			preparedStatement.setInt(3, userId);

			preparedStatement.executeUpdate();
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_EDIT_COMMENT = "UPDATE comments SET text = ? WHERE comment_id = ?";
	@Override
	public void editCommentTextById(int id, String text) throws DaoException{
		try(Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SQL_EDIT_COMMENT)){
			preparedStatement.setString(1, text);
			preparedStatement.setInt(2, id);

			preparedStatement.executeUpdate();
		}
		catch (SQLException | ConnectionPoolException e){
			throw new DaoException(e);
		}
	}
}
