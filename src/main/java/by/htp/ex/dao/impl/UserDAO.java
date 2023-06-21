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
		String SQL = "SELECT * FROM book WHERE email = ?";

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
	public NewUserInfo authorization(String login) throws DaoException {
		String SQL = "SELECT * FROM user WHERE login = ? AND password = ?";


		try (Connection connection = DriverManager.getConnection(ConstantsName.DB_URL, ConstantsName.DB_USERNAME, ConstantsName.DB_PASSWORD);
			 PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {

			ResultSet resultSet = preparedStatement.executeQuery();

			return null;
		}
		catch (SQLException e) {
			throw new DaoException(e);
		}
	}

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
}
