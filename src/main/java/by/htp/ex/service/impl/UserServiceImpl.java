package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;

public class UserServiceImpl implements IUserService {

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();


	@Override
	public String signIn(String login, String password) throws ServiceException {

		try {
			NewUserInfo userDao = userDAO.authorization(login);
			if (userDao != null && userDao.getPassword().equals(password))
				return userDao.getRole().toString().toLowerCase();
			return Role.GUEST.toString().toLowerCase();
		}

		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public boolean registration(NewUserInfo user) throws ServiceException {
		try {
			if (userDAO.isExistUser(user)) {
				throw new DaoException("User with this email exists");
			}
			userDAO.registration(user);
			return true;
		} catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
