package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
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
<<<<<<< HEAD
		String SQL = "SELECT * FROM users WHERE email = ?";
=======
		String SQL = "SELECT * FROM book WHERE email = ?";
>>>>>>> 919920c756abc7c1d7136076a909183def9aec5a

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
<<<<<<< HEAD
	public NewUserInfo authorization(String login, String password) throws DaoException {
		String SQL = "SELECT * FROM users WHERE login = ? AND password = ?";
		NewUserInfo newUserInfo = null;

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
			preparedStatement.setString(1, login);
			preparedStatement.setString(2, password);
			ResultSet resultSet = preparedStatement.executeQuery();
			newUserInfo = new NewUserInfo();
			newUserInfo.setLogin(resultSet.getString("login"));
			newUserInfo.setPassword(resultSet.getString("password"));
			newUserInfo.setEmail(resultSet.getString("email"));
			newUserInfo.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
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
			e.printStackTrace();
=======
	public NewUserInfo authorization(String login) throws DaoException {
		String SQL = "SELECT * FROM user WHERE login = ? AND password = ?";


		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			return null;
		}
		catch (SQLException e) {
>>>>>>> 919920c756abc7c1d7136076a909183def9aec5a
			throw new DaoException(e);
		}
	}

<<<<<<< HEAD
//	public NewUserInfo getNewUserInfo(ResultSet resultSet) throws SQLException{
//		if (resultSet == null) {
//			return null;
//		}
//		resultSet.next();
//
//
//
//		NewUserInfo newUserInfo = new NewUserInfo();
//		newUserInfo.setLogin(resultSet.getString("login"));
//		newUserInfo.setPassword(resultSet.getString("password"));
//		newUserInfo.setEmail(resultSet.getString("email"));
//		newUserInfo.setRole(Role.valueOf(resultSet.getString("role").toUpperCase()));
//
//
//		return newUserInfo;
//	}
=======
	@Override
	public boolean registration(NewUserInfo user) throws DaoException {
		String SQL = "INSERT INTO user (login, password) VALUES (?, ?)";

		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			preparedStatement.setString(1, user.getLogin());
			preparedStatement.setString(2, user.getPassword());
			int countRow = preparedStatement.executeUpdate();

			return countRow > 0;
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}
>>>>>>> 919920c756abc7c1d7136076a909183def9aec5a
}
