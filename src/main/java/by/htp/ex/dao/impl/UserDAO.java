package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class UserDAO implements IUserDAO {

	static {
		try {
			Class.forName(ConstantsName.DB_DRIVER);
		}
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//Если пользователь есть, то возвращает true инчае false
	private final static String SQL_QUERY_GET_USER_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
	@Override
	public boolean isExistUser(NewUserInfo user) throws DaoException {

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_EMAIL)) {

			preparedStatement.setString(1, user.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();

			return resultSet.next();
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD = "SELECT * FROM users WHERE login = ? AND password = ?";
	@Override
	public NewUserInfo authorization(String login, String password) throws DaoException {

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL_QUERY_GET_USER_BY_LOGIN_AND_PASSWORD)) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				return parseUserInfo(resultSet);
			}
			else {
				throw new DaoException("No user found with this login and password");
			}
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_ADD_USER = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";
	@Override
	public void registration(NewUserInfo user) throws DaoException {

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL_QUERY_ADD_USER)) {
			preparedStatementSQL.setString(1, user.getLogin());
			preparedStatementSQL.setString(2, user.getPassword());
			preparedStatementSQL.setString(3, user.getEmail());
			preparedStatementSQL.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_GET_LIST_USERS = "SELECT * FROM users";
	@Override
	public List<NewUserInfo> getUsers() throws DaoException {
		List<NewUserInfo> usersInfo = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL_QUERY_GET_LIST_USERS)) {
			ResultSet resultSet = preparedStatementSQL.executeQuery();

			while (resultSet.next()){
				usersInfo.add(parseUserInfo(resultSet));
			}

			return usersInfo;
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_GET_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
	@Override
	public NewUserInfo getUser(int id) throws DaoException {

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL_QUERY_GET_USER_BY_ID)) {
			preparedStatementSQL.setInt(1, id);
			ResultSet resultSet = preparedStatementSQL.executeQuery();

			if (resultSet.next()) {
				return parseUserInfo(resultSet);
			}
			else {
				throw new DaoException("User with current data not exist");
			}
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	private final static String SQL_QUERY_UPDATE_USER = "UPDATE users SET login=?, password=?, email=? WHERE id=?";
	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws DaoException{

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL_QUERY_UPDATE_USER)) {
			preparedStatementSQL.setString(1, userInfo.getLogin());
			preparedStatementSQL.setString(2, userInfo.getPassword());
			preparedStatementSQL.setString(3, userInfo.getEmail());
			preparedStatementSQL.setInt(4, userInfo.getUserId());

			preparedStatementSQL.executeUpdate();
		}
		catch(SQLException e){
			throw new DaoException(e);
		}
	}

	public NewUserInfo parseUserInfo(ResultSet resultSet) throws SQLException{
		NewUserInfo newUserInfo = new NewUserInfo();
		newUserInfo.setUserId(resultSet.getInt("id"));
		newUserInfo.setLogin(resultSet.getString("login"));
		newUserInfo.setPassword(resultSet.getString("password"));
		newUserInfo.setEmail(resultSet.getString("email"));
		newUserInfo.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
		return newUserInfo;
	}
}
