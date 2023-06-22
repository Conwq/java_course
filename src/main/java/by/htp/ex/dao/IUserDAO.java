package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;

public interface IUserDAO {

	void registration(NewUserInfo user) throws DaoException;
	NewUserInfo authorization(String login, String password) throws DaoException;
	boolean isExistUser(NewUserInfo user) throws DaoException;
}
