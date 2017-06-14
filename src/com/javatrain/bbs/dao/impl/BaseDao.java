package com.javatrain.bbs.dao.impl;

import java.sql.*;

public class BaseDao {    
    public final static String DRIVER = "com.mysql.jdbc.Driver";                 // ���ݿ�����
    public final static String URL    = "jdbc:mysql://localhost:3306/JspBbs?useUnicode=true&characterEncoding=UTF-8";   // url
    public final static String DBNAME = "root";                                                           // ���ݿ��û���
    public final static String DBPASS = "root";                                                           // ���ݿ�����
    
    /**
     * �õ����ݿ�����
     * @throws ClassNotFoundException
     * @throws SQLException
     * @return ���ݿ�����
     */
    public Connection getConn() throws ClassNotFoundException, SQLException{
        Class.forName(DRIVER);                                                    //ע������
        Connection conn = DriverManager.getConnection(URL,DBNAME,DBPASS);        //������ݿ�����
        return conn ;                                                            //��������
    }
    
    /**
     * �ͷ���Դ
     * @param conn ���ݿ�����
     * @param pstmt PreparedStatement����
     * @param rs �����
     */
    public void closeAll( Connection conn, PreparedStatement pstmt, ResultSet rs ) {
        /*  ���rs���գ��ر�rs  */
        if(rs != null){
            try { rs.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���pstmt���գ��ر�pstmt  */
        if(pstmt != null){
            try { pstmt.close();} catch (SQLException e) {e.printStackTrace();}
        }
        /*  ���conn���գ��ر�conn  */
        if(conn != null){
            try { conn.close();} catch (SQLException e) {e.printStackTrace();}
        }
    }
    
    /**
     * ִ��SQL��䣬���Խ�������ɾ���ĵĲ���������ִ�в�ѯ
     * @param sql  Ԥ����� SQL ���
     * @param param  Ԥ����� SQL ����еġ������������ַ�������
     * @return Ӱ�������
     */
    public int executeSQL(String preparedSql,String[] param) {
        Connection        conn  = null;
        PreparedStatement pstmt = null;
        int               num   = 0;
        
        /*  ����SQL,ִ��SQL  */
        try {
            conn = getConn();                              // �õ����ݿ�����
            pstmt = conn.prepareStatement(preparedSql);    // �õ�PreparedStatement����
            if( param != null ) {
                for( int i = 0; i < param.length; i++ ) {
                    pstmt.setString(i+1, param[i]);         // ΪԤ����sql���ò���
                }
            }
            num = pstmt.executeUpdate();                    // ִ��SQL���
        } catch (ClassNotFoundException e) {
            e.printStackTrace();                            // ����ClassNotFoundException�쳣
        } catch (SQLException e) {
            e.printStackTrace();                            // ����SQLException�쳣
        } finally {
            closeAll(conn,pstmt,null);                     // �ͷ���Դ
        }
        return num;
    }

}
