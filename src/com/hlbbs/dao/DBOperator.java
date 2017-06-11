/**
 * @filename 	DBOperator.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月10日
 */

package com.hlbbs.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBOperator {
	private Connection conn = null;
	private Statement stmt = null;
	private ResultSet rs = null;
	
	/**
	 * 构造函数
	 * 初始化数据库连接
	 */
	public DBOperator() {
		DBInit db = new DBInit();
		conn = db.getConnection();
		try {
			stmt = conn.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean select(String sql) {
		
		int count = 0;	// 查询结果集的行数
		
		try {
			rs = stmt.executeQuery(sql);
			count = rs.getRow();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean insert() {
		return true;
	}
	
	public boolean delete() {
		return true;
	}
	
	public boolean update(String sql) {
		
		int rowCount = 0;
		
		try {
			rowCount = stmt.executeUpdate(sql);		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if (rowCount == 1) {
			return true;
		} else {
			return false;
		}
	}
}
