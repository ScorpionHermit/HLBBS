/**
 * @filename 	UserService.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月10日
 */

package com.hlbbs.service;

import com.hlbbs.dao.DBOperator;;

public class UserService {
	private DBOperator db = null;
	
	/**
	 * 构造函数
	 */
	public UserService() {
		db = new DBOperator();
	}
	
	
	public boolean modPassword(String newPassword, String username) {
		String sql = "update t_hlbbs_user set nvcPass='" + newPassword
				+ "' where nvcNickName='" + username + "'";
		boolean isSuccess = db.update(sql);
		
		if (isSuccess) {
			return true;
		} else {
			return false;
		}
	}


	public boolean queryUser(String username, String oldPassword) {
		String sql = "select username from t_hlbbs_user where nvcNickName='"
				+ username + "' and nvcPass='" + oldPassword + "'";
		boolean isExist = db.select(sql);
		
		if (isExist) {
			return true;
		} else {
			return false;
		}
	}
}
