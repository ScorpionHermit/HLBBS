package com.hlbbs.Modal;

public class Sign {
  private String FinalSignTime;
  private int UserId;
  
public Sign(String finalSignTime, int userId) {
	super();
	FinalSignTime = finalSignTime;
	UserId = userId;
}
public Sign(){}
public String getFinalSignTime() {
	return FinalSignTime;
}
public void setFinalSignTime(String finalSignTime) {
	FinalSignTime = finalSignTime;
}
public int getUserId() {
	return UserId;
}
public void setUserId(int userId) {
	UserId = userId;
}
  
}
