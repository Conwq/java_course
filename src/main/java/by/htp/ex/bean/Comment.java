package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public final class Comment implements Serializable {
	private final static long serialVersionUID = 1L;
	private int commentId;
	private NewUserInfo newUserInfo;
	private News news;
	private String date;
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
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Comment comment = (Comment) o;
		return Objects.equals(newUserInfo, comment.newUserInfo) && Objects.equals(news, comment.news) && Objects.equals(date, comment.date) && Objects.equals(text, comment.text);
	}

	@Override
	public int hashCode() {
		return Objects.hash(newUserInfo, news, date, text);
	}
}
