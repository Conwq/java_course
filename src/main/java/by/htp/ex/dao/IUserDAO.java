package by.htp.ex.dao;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;

public interface IUserDAO {
<<<<<<< HEAD
	void registration(NewUserInfo user) throws DaoException;
	NewUserInfo authorization(String login, String password) throws DaoException;
=======
	boolean registration(NewUserInfo user) throws DaoException;
	NewUserInfo authorization(String login) throws DaoException;
>>>>>>> 919920c756abc7c1d7136076a909183def9aec5a
	boolean isExistUser(NewUserInfo user) throws DaoException;

}
