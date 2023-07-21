package by.htp.ex.service;

import by.htp.ex.bean.News;
import by.htp.ex.service.exception.ServiceException;

import java.util.List;
import java.util.Locale;

public interface INewsService {
  void save(News news) throws ServiceException;
  void update(News news) throws ServiceException;
  List<News> latestList(int count, Locale locale)  throws ServiceException;
  List<News> list(Locale locale)  throws ServiceException;
  News findById(String id, Locale locale) throws ServiceException;
  void delete(String newsId) throws ServiceException;
  void deleteNewses(String[] newses) throws ServiceException;
}
