package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.util.ConstantsName;

import java.sql.*;

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

			if (resultSet.next()) {
				newUserInfo = new NewUserInfo();
				newUserInfo.setLogin(resultSet.getString("login"));
				newUserInfo.setPassword(resultSet.getString("password"));
				newUserInfo.setEmail(resultSet.getString("email"));
				newUserInfo.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
			}
			else
				throw new DaoException("No user found with this login and password");
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
		return newUserInfo;
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
}
