package by.htp.ex.service.impl;

import java.util.List;
import java.util.Locale;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;

public final class NewsServiceImpl implements INewsService{
	private final static INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

	@Override
	public void save(News news) throws ServiceException{
		try{
			newsDAO.addNews(news);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	@Override
	public void update(News news) throws ServiceException {
		try{
			newsDAO.updateNews(news);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	//TODO ЧТО ТО СДЕЛАТЬ С COUNT

	@Override
	public List<News> latestList(int count, Locale locale) throws ServiceException {
		try {
			return newsDAO.getLatestList(5, locale);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public List<News> list(Locale locale) throws ServiceException {
		try {
			return newsDAO.getList(locale);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public News findById(int id, Locale locale) throws ServiceException {
		try {
			return newsDAO.fetchById(id, locale);
		}
		catch (DaoException e) {
			throw new ServiceException(e);
		}
	}

	@Override
	public void delete(int id) throws ServiceException {
		try {
			newsDAO.deleteNews(id);
		}
		catch (DaoException e){
			throw new ServiceException(e);
		}
	}

	public void deleteNewses(String[] newses) throws ServiceException{
		int[] newsesId = new int[newses.length];
		for (int i = 0; i < newses.length; i++){
			newsesId[i] = Integer.parseInt(newses[i]);
		}
		try {
			newsDAO.deleteNewses(newsesId);
		}
		catch(DaoException e){
			throw new ServiceException(e);
		}
	}
}
