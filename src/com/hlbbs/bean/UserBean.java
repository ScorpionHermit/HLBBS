/**
 * @filename 	UserBean.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月10日
 */

package com.hlbbs.bean;

public class UserBean {
	private String nickname;				// 昵称
	private String password;				// 密码
	private String email;					// 邮箱
	private String role;					// 角色
	private char sex;						// 性别
	private String headportrait;			// 头像
	private int integral;					// 积分
	private String personalizedsignature;	// 个性签名
	private String level;					// 等级
	
	/**
	 * 构造函数
	 */
	public UserBean() {
		
	}
	
	public String getNickname() {
		return nickname;
	}
	
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getRole() {
		return role;
	}
	
	public void setRole(String role) {
		this.role = role;
	}
	
	public char getSex() {
		return sex;
	}
	
	public void setSex(char sex) {
		this.sex = sex;
	}
	
	public String getHeadportrait() {
		return headportrait;
	}
	
	public void setHeadportrait(String headportrait) {
		this.headportrait = headportrait;
	}
	
	public int getIntegral() {
		return integral;
	}
	
	public void setIntegral(int integral) {
		this.integral = integral;
	}
	
	public String getPersonalizedsignature() {
		return personalizedsignature;
	}
	
	public void setPersonalizedsignature(String personalizedsignature) {
		this.personalizedsignature = personalizedsignature;
	}
	
	public String getLevel() {
		return level;
	}
	
	public void setLevel(String level) {
		this.level = level;
	}
	
}
