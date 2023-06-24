package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;

import java.util.List;

public interface IUserDAO {
	void registration(NewUserInfo user) throws DaoException;
	NewUserInfo authorization(String login, String password) throws DaoException;
	boolean isExistUser(NewUserInfo user) throws DaoException;
	List<NewUserInfo> getUsers() throws DaoException;

	NewUserInfo getUser(int id) throws DaoException;
}
