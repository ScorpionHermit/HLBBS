/**
 * @filename 	DBInit.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月9日
 */

package com.hlbbs.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBInit {
	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String URL = "jdbc:mysql://118.89.105.161:3306/hlbbs";
	private final String USERNAME = "root";
	private final String PASSWORD = "zxz6";
	
	/**
	 * 构造函数
	 */
	public DBInit() {
	
	}
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName(DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
	
	/**
	 * 释放数据库连接
	 * @param conn 数据库连接
	 * @throws SQLException
	 */
	public void close(Connection conn) throws SQLException {
		if (conn != null) {
			conn.close();
		}
	}
}
