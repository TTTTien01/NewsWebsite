package com.laptrinhjavaweb.model;

public class CommentModel extends AbstractModel<CommentModel> {

	private String content;
	private Long userId;
	private Long newsId;
	private String userName;
	private int invalid;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getNewsId() {
		return newsId;
	}

	public void setNewsId(Long newsId) {
		this.newsId = newsId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	/*public int isInvalid() {
		return invalid;
	}*/
	
	

	public int getInvalid() {
		return invalid;
	}

	public void setInvalid(int invalid) {
		this.invalid = invalid;
	}

	
}
