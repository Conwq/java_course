package by.htp.ex.service.impl;

import by.htp.ex.bean.NewUserInfo;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.IUserDAO;
import by.htp.ex.service.exception.ServiceException;
import by.htp.ex.service.IUserService;

public class UserServiceImpl implements IUserService{

	private final IUserDAO userDAO = DaoProvider.getInstance().getUserDao();
//	private final UserDataValidation userDataValidation = ValidationProvider.getIntsance().getUserDataVelidation();
	
	@Override
	public String signIn(String login, String password) throws ServiceException {
		
		/*
		 * if(!userDataValidation.checkAUthData(login, password)) { throw new
		 * ServiceException("login ...... "); }
		 */
		
		try {
			return userDAO.getRole(login, password);

		}catch(DaoException e) {
			throw new ServiceException(e);
		}
		
	}

	@Override
	public boolean registration(NewUserInfo user) {
		// TODO Auto-generated method stub
		return false;
	}

}
