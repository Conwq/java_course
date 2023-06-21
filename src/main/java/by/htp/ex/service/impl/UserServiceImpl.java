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

			System.out.println(userDao);

			if (userDao != null) {
				System.out.println(userDao.getRole().toString().toLowerCase());
				return userDao.getRole().toString().toLowerCase();
			}
			System.out.println(Role.GUEST.toString().toLowerCase());
			return Role.GUEST.toString().toLowerCase();
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
			e.printStackTrace();
			throw new ServiceException(e);
		}
	}
}
