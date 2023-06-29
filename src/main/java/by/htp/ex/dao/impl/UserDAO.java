package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;
import by.htp.ex.util.DatabaseHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO implements IUserDAO {
	private final static DatabaseHelper helper = DatabaseHelper.getInstance();

	static {
		try {
			Class.forName(ConstantsName.DB_DRIVER);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//Если пользователь есть, то возвращает true инчае false
	private final static String SQL_QUERY_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ? OR login = ?";
	private boolean isExistUser(NewUserInfo user, Connection connection) throws SQLException {
		PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_EMAIL);

		preparedStatement.setString(1, user.getEmail());
		preparedStatement.setString(2, user.getLogin());
		ResultSet resultSet = preparedStatement.executeQuery();

		return resultSet.next();
	}

	private final static String SQL_QUERY_ADD_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
	@Override
	public void registration(NewUserInfo user) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try  {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(SQL_QUERY_ADD_USER);

			if (!isExistUser(user, connection)){
				preparedStatement.setString(1, user.getLogin());
				preparedStatement.setString(2, user.getPassword());
				preparedStatement.setString(3, user.getEmail());
				
				preparedStatement.executeUpdate();
				connection.commit();
			}
			else {
				connection.rollback();
				throw new DaoException("User with this email or login exists");
			}
		}
		catch (SQLException e) {
			helper.exceptionSQLHandler(connection, e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement);
		}
	}

	private final static String SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ?";
	@Override
	public NewUserInfo authorization(String login, String password) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD);

			preparedStatement.setString(1, login);
			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return helper.parseUserInfo(resultSet, password);
			}
			else {
				throw new DaoException("User not found with this login");
			}
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
		}
	}

	private final static String SQL_QUERY_GET_LIST_USERS = "SELECT * FROM users";
	@Override
	public List<NewUserInfo> getUsers() throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		List<NewUserInfo> usersInfo = null;

		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_LIST_USERS);
			resultSet = preparedStatement.executeQuery();
			usersInfo = new ArrayList<>();

			while (resultSet.next()){
				usersInfo.add(helper.parseUserInfo(resultSet));
			}
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
		}

		return usersInfo;
	}

	private final static String SQL_QUERY_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	@Override
	public NewUserInfo getUser(int id) throws DaoException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);

			preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_ID);
			preparedStatement.setInt(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return helper.parseUserInfo(resultSet);
			}
			else {
				throw new DaoException("User with current data not exist");
			}
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
		finally{
			helper.closeConnectionResources(connection, preparedStatement, resultSet);
		}
	}

	private final static String SQL_QUERY_UPDATE_USER = "UPDATE users SET login=?, password=?, email=? WHERE id=?";
	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws DaoException{
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);

			preparedStatement = connection.prepareStatement(SQL_QUERY_UPDATE_USER);
			preparedStatement.setString(1, userInfo.getLogin());
			preparedStatement.setString(2, userInfo.getPassword());
			preparedStatement.setString(3, userInfo.getEmail());
			preparedStatement.setInt(4, userInfo.getUserId());
			preparedStatement.executeUpdate();
		}
		catch(SQLException e){
			throw new DaoException(e);
		}
		finally {
			helper.closeConnectionResources(connection, preparedStatement);
		}
	}
}
