package com.hlbbs.Modal;

public class Comment { 
	private int id; //id
	private int userId; //������id
	private int postsId; //����id
	private String content; //��������
	private String comTime; //����ʱ��
	private int buildingNum; //¥��
	private String title;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostsId() {
		return postsId;
	}
	public void setPostsId(int postsId) {
		this.postsId = postsId;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getComTime() {
		return comTime;
	}
	public void setComTime(String comTime) {
		this.comTime = comTime;
	}
	public int getBuildingNum() {
		return buildingNum;
	}
	public void setBuildingNum(int buildingNum) {
		this.buildingNum = buildingNum;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	

}
