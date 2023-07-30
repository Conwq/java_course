package by.htp.ex.service.impl;

import by.htp.ex.bean.News;
import by.htp.ex.dao.DaoProvider;
import by.htp.ex.dao.INewsDAO;
import by.htp.ex.dao.exception.DaoException;
import by.htp.ex.service.INewsService;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public final class NewsServiceImpl implements INewsService{
	private static final INewsDAO newsDAO = DaoProvider.getInstance().getNewsDAO();

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
	public News findById(String newsId, Locale locale) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			return newsDAO.fetchById(id, locale);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("News with this id not found", e);
		}
	}

	@Override
	public void delete(String newsId) throws ServiceException {
		try {
			int id = Integer.parseInt(newsId);
			newsDAO.deleteNews(id);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("News with this id not found", e);
		}
	}

	public void deleteNewses(String[] newses) throws ServiceException{

		try {
			int[] newsesId = new int[newses.length];
			for (int i = 0; i < newses.length; i++){
				newsesId[i] = Integer.parseInt(newses[i]);
			}
			newsDAO.deleteNewses(newsesId);
		}
		catch(DaoException e) {
			throw new ServiceException(e);
		}
		catch (NumberFormatException e){
			throw new ServiceException("News with this id not found", e);
		}
	}
}
