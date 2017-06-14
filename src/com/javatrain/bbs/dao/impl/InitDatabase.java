package com.javatrain.bbs.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class InitDatabase {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
		} catch (ClassNotFoundException ce) {
			System.out.println(ce);
		}
		try {
			String url = "jdbc:mysql://localhost:3306/JspBbs?useUnicode=true&characterEncoding=UTF-8";
			Connection con = DriverManager.getConnection(url, "root", "root");
			Statement stmt = con.createStatement();
			String sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(1,'ClientTech',0)";
			stmt.executeUpdate(sql);
			sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(2,'ServerTech',0)";
			stmt.executeUpdate(sql);
			sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(3,'Ajax',1)";
			stmt.executeUpdate(sql);
			sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(4,'ExtJs',1)";
			stmt.executeUpdate(sql);
			sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(5,'Spring',2)";
			stmt.executeUpdate(sql);
			sql = "insert into TBL_BOARD(boardId,boardName,parentId) value(6,'Hibernate',2)";
			stmt.executeUpdate(sql);
		}catch (SQLException ce) {
			System.out.println(ce);
		}
	}

}
