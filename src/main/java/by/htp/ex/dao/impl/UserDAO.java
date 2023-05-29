package by.htp.ex.dao.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.IUserDAO;

import java.util.HashMap;
import java.util.Map;

public class UserDAO implements IUserDAO{

	private static Map<String, String> data;

	{
		data = new HashMap<>();

		data.put("admin", "1");
		data.put("user", "1");
	}

	@Override
	public boolean logination(String login, String password) throws DaoException {
		
		/*
		 * Random rand = new Random(); int value = rand.nextInt(1000);
		 * 
		 * if(value % 3 == 0) {
		 * 		try {
		 * 			throw new SQLException("stub exception");
		 * 	}
		 * 	catch(SQLException e) {
		 * 		throw new DaoException(e);
		 * 	}
		 * }
		 * else if (value % 2 ==0) {
		 * 	return true;
		 * }
		 * else {
		 *  return false;
		 * }
		 */
		
		return true;
		
	}
	
	public String getRole(String login, String password) {

		return "user";
	}

	@Override
	public boolean registration(NewUserInfo user) throws DaoException  {
		return false;
	}

}
