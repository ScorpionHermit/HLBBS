package com.hlbbs.Modal;

public class User {
     private int id;
     private String Name;
     private String PassWord;
     private String EmailAddress;
     private int RoleType;
     private String Sex;
     private String HeadPortrait;         //头像
     private int Integral;             //积分
     private String PersonalizedSignature;//个性签名
     private int Level;
     private String registertime ;//注册时间
	
	
	
	public User(int id, String name, String passWord, String emailAddress, int roleType, String sex,
			String headPortrait, int integral, String personalizedSignature, int level,String regtime) {
		super();
		this.id = id;
		Name = name;
		PassWord = passWord;
		EmailAddress = emailAddress;
		RoleType = roleType;
		Sex = sex;
		HeadPortrait = headPortrait;
		Integral = integral;
		PersonalizedSignature = personalizedSignature;
		Level = level;
		registertime =regtime;
	}

	public User(){}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassWord() {
		return PassWord;
	}
	public void setPassWord(String passWord) {
		PassWord = passWord;
	}
	public String getEmailAddress() {
		return EmailAddress;
	}
	public void setEmailAddress(String emailAddress) {
		EmailAddress = emailAddress;
	}
	public int getRoleType() {
		return RoleType;
	}
	public void setRoleType(int roleType) {
		RoleType = roleType;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getHeadPortrait() {
		return HeadPortrait;
	}
	public void setHeadPortrait(String headPortrait) {
		HeadPortrait = headPortrait;
	}
	public int getIntegral() {
		return Integral;
	}
	public void setIntegral(int integral) {
		Integral = integral;
	}
	public String getPersonalizedSignature() {
		return PersonalizedSignature;
	}
	public void setPersonalizedSignature(String personalizedSignature) {
		PersonalizedSignature = personalizedSignature;
	}
	public int getLevel() {
		return Level;
	}

	public void setLevel(int level) {
		Level = level;
	}

	public String getRegistertime() {
		return registertime;
	}

	public void setRegistertime(String registertime) {
		this.registertime = registertime;
	}


}
