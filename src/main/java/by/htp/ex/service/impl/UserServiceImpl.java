package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.bean.Role;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.IUserService;
import by.htp.ex.service.exception.ServiceException;

public final class UserServiceImpl implements IUserService {
	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();

	@Override
	public String signIn(String login, String password) throws ServiceException {

		try {
			NewUserInfo userDao = userDAO.authorization(login, password);

			if (userDao != null) {
				//TODO
				return userDao.getRole().getRole();
			}
			return Role.GUEST.getRole();
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void registration(NewUserInfo user) throws ServiceException {

		try {
			if (userDAO.isExistUser(user))
				throw new ServiceException("User with this email exists");

			userDAO.registration(user);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}
}
