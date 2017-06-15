/**
 * @filename 	Post.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月15日
 */

package com.hlbbs.Modal;

public class Post {
	private int id;					// ID
	private String title; 			// 标题
	private String postMan; 		// 发贴人
	private String content;			// 内容
	private String postTime;		// 发布时间
	private String finalReplyTime; 	// 最后回复时间
	private int replyCount;			// 回复数
	private int sectionID;			// 版块ID
	private int isBoutique;			//是否为精品
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPostMan() {
		return postMan;
	}
	public void setPostMan(String postMan) {
		this.postMan = postMan;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPostTime() {
		return postTime;
	}
	public void setPostTime(String postTime) {
		this.postTime = postTime;
	}
	public String getFinalReplyTime() {
		return finalReplyTime;
	}
	public void setFinalReplyTime(String finalReplyTime) {
		this.finalReplyTime = finalReplyTime;
	}
	public int getReplyCount() {
		return replyCount;
	}
	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}
	public int getSectionID() {
		return sectionID;
	}
	public void setSectionID(int sectionID) {
		this.sectionID = sectionID;
	}
	public int getIsBoutique() {
		return isBoutique;
	}
	public void setIsBoutique(int isBoutique) {
		this.isBoutique = isBoutique;
	}
}
