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
	@Override
	public boolean isExistUser(NewUserInfo user) throws DaoException {
		String SQL = "SELECT * FROM users WHERE email = ?";

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, user.getEmail());
			ResultSet resultSet = preparedStatement.executeQuery();

			return resultSet.next();
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public NewUserInfo authorization(String login, String password) throws DaoException {
		String SQL = "SELECT * FROM users WHERE login = ? AND password = ?";
		NewUserInfo newUserInfo = null;

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next())
				return parseUserInfo(resultSet);

			else
				throw new DaoException("No user found with this login and password");
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public void registration(NewUserInfo user) throws DaoException {
		String SQL = "INSERT INTO users (login, password, email) VALUES (?, ?, ?)";

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL)) {
			preparedStatementSQL.setString(1, user.getLogin());
			preparedStatementSQL.setString(2, user.getPassword());
			preparedStatementSQL.setString(3, user.getEmail());
			preparedStatementSQL.executeUpdate();
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

	@Override
	public List<NewUserInfo> getUsers() throws DaoException {
		String SQL = "SELECT * FROM users";
		List<NewUserInfo> usersInfo = new ArrayList<>();

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL)) {
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

	@Override
	public NewUserInfo getUser(int id) throws DaoException {
		String SQL = "SELECT * FROM users WHERE id = ?";

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL)) {
			preparedStatementSQL.setInt(1, id);
			ResultSet resultSet = preparedStatementSQL.executeQuery();

			if (resultSet.next())
				return parseUserInfo(resultSet);

			else
				throw new DaoException("User with current data not exist");
		}
		catch (SQLException e){
			throw new DaoException(e);
		}
	}

	@Override
	public void updateUserInfo(NewUserInfo userInfo) throws DaoException{
		String SQL = "UPDATE users SET login=?, password=?, email=? WHERE id=?";

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatementSQL = connection.prepareStatement(SQL)) {
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
