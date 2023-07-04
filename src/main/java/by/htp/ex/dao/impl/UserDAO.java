package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.pool.ConnectionPool;
import by.htp.ex.dao.pool.ConnectionPoolException;
import by.htp.ex.util.DatabaseHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO implements IUserDAO {
	private final static DatabaseHelper helper = DatabaseHelper.getInstance();
	private final static ConnectionPool connectionPool = ConnectionPool.getInstance();

	private boolean isExistUser(NewUserInfo user, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE email = ? OR login = ? AND id <> ?");

		preparedStatement.setString(1, user.getEmail());
		preparedStatement.setString(2, user.getLogin());
		preparedStatement.setInt(3, user.getUserId());
		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet.next();
	}

	@Override
	public void registration(NewUserInfo user) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			connection = connectionPool.takeConnection();
			connection.setAutoCommit(false);
			preparedStatement = connection.prepareStatement("INSERT INTO users (login, password, email) VALUES (?, ?, ?)");

			if (!isExistUser(user, connection)) {
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmail());

				preparedStatement.executeUpdate();
				connection.commit();
			}
			else {
				throw new DaoException("User with this email or login exists");
			}
		}
		catch (SQLException | ConnectionPoolException e) {
			if (connection != null) {
				try {
					connection.rollback();
				}
				catch (SQLException ex) {
					throw new DaoException(ex);
				}
			}
			throw new DaoException(e);
		}
		finally {
			try {
				connectionPool.closeConnection(connection, preparedStatement);
			}
			catch (ConnectionPoolException e) {
				throw new DaoException(e);
			}
		}
	}

	@Override
	public NewUserInfo authorization(String login, String password) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE login = ?")) {

			preparedStatement.setString(1, login);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return helper.parseUserInfo(resultSet, password);
			}
			else {
				throw new DaoException("User not found with this login");
			}
		}
		catch (SQLException e) {
			throw new DaoException("Error with SQL", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("Error with ConnectionPool", e);
		}
	}

	@Override
	public List<NewUserInfo> getUsers() throws DaoException {
		List<NewUserInfo> usersInfo = null;

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users");
			 ResultSet resultSet = preparedStatement.executeQuery()) {

			usersInfo = new ArrayList<>();
			while (resultSet.next()) {
				usersInfo.add(helper.parseUserInfo(resultSet));
			}
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
		return usersInfo;
	}

	@Override
	public NewUserInfo getUser(int id) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?")) {

			preparedStatement.setInt(1, id);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return helper.parseUserInfo(resultSet);
			}
			else {
				throw new DaoException("User with current data not exist");
			}
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
	}

	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws DaoException {

		try (Connection connection = connectionPool.takeConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET login=?, password=?, email=? WHERE id=?")) {

			if (isExistUser(userInfo, connection)){
				throw new DaoException("User with current email or login exist");
			}

			preparedStatement.setString(1, userInfo.getLogin());
			preparedStatement.setString(2, userInfo.getPassword());
			preparedStatement.setString(3, userInfo.getEmail());
			preparedStatement.setInt(4, userInfo.getUserId());
			preparedStatement.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException("SQLException", e);
		}
		catch (ConnectionPoolException e){
			throw new DaoException("ConnectionPoolException", e);
		}
	}

	@Override
	public void unbanUser(int id) throws DaoException {
		try (Connection connection = connectionPool.takeConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE users SET banned = 0 WHERE id = ?")){
			NewUserInfo user = getUser(id);

			if (!user.isBanned()){
				throw new DaoException("User can not banned");
			}
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
		catch (ConnectionPoolException e){
			e.printStackTrace();
		}
	}
}
