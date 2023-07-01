package by.htp.ex.bean;

import java.util.Date;

public final class Comment {
	private int commentId;
	private NewUserInfo newUserInfo;
	private News news;
	private Date date;
	private String text;
	
	public Comment() {
		
	}

	public Comment(NewUserInfo newUserInfo, News news, String text) {
		this.newUserInfo = newUserInfo;
		this.news = news;
		this.text = text;
	}
	
	public int getCommentId() {
		return commentId;
	}
	
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	
	public NewUserInfo getNewUserInfo() {
		return newUserInfo;
	}
	
	public void setNewUserInfo(NewUserInfo user) {
		this.newUserInfo = user;
	}
	
	public News getNews() {
		return news;
	}
	
	public void setNews(News news) {
		this.news = news;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}

	@Override
	public String toString() {
		return getClass().getName() +"{" +
				"commentId=" + commentId +
				", user=" + newUserInfo +
				", news=" + news +
				", date=" + date +
				", text='" + text + '\'' +
				'}';
	}
}
