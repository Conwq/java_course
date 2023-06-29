package by.htp.ex.util;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.exception.DaoException;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseHelper {

	private final static DatabaseHelper instance = new DatabaseHelper();

	private DatabaseHelper(){

	}

	public static DatabaseHelper getInstance(){
		return instance;
	}

	public void exceptionSQLHandler(Connection connection, SQLException e) throws DaoException {
		if (connection != null){
			try {
				connection.rollback();
			}
			catch (SQLException ex){
				throw new DaoException(ex);
			}
		}
		throw new DaoException(e);
	}

	public void closeConnectionResources(Connection connection, PreparedStatement preparedStatement) throws DaoException{

		if (preparedStatement != null) {
			try {
				preparedStatement.close();
			}
			catch (SQLException e) {
				throw new DaoException(e);
			}
		}

		if (connection != null) {
			try {
				connection.close();
			}
			catch (SQLException e) {
				throw new DaoException(e);
			}
		}
	}

	public void closeConnectionResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) throws DaoException{

		if (resultSet != null) {
			try {
				resultSet.close();
			} catch (SQLException e) {
				throw new DaoException(e);
			}
		}
		closeConnectionResources(connection, preparedStatement);
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
	
	public NewUserInfo parseUserInfo(ResultSet resultSet, String password) throws SQLException, DaoException{
		if(BCrypt.checkpw(password, resultSet.getString("password"))) {
			return parseUserInfo(resultSet);
		}
		else {
			throw new DaoException("Not valid password");
		}
	}
}
