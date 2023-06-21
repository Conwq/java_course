package by.htp.ex.bean;

import java.io.Serializable;

public final class News implements Serializable {
	private static final long serialVersionUID = 1L;
	private Integer idNews;
	private String title;
	private String briefNews;
	private String content;
	private String newsDate;
	private String photoPath;

	public News() {
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate, String photoPath) {
		super();
		this.photoPath = photoPath;
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public News(int idNews, String title, String briefNews, String content, String newsDate) {
		super();
		this.photoPath = null;
		this.idNews = idNews;
		this.title = title;
		this.briefNews = briefNews;
		this.content = content;
		this.newsDate = newsDate;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Integer getIdNews() {
		return idNews;
	}

	public void setIdNews(Integer idNews) {
		this.idNews = idNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBriefNews() {
		return briefNews;
	}

	public void setBriefNews(String briefNews) {
		this.briefNews = briefNews;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getNewsDate() {
		return newsDate;
	}

	public void setNewsDate(String newsDate) {
		this.newsDate = newsDate;
	}
}
