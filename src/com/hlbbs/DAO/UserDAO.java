package com.hlbbs.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hlbbs.Modal.User;





public class UserDAO extends DAO {
	User user;
	private PreparedStatement pstmt = null;       // ����ִ��SQL���
    private ResultSet         rs    = null;       // �û������ѯ�����   
	
    /**
     * �����û�
     * @param user
     * @return ��������
     */
    public UserDAO(User u){
    	user = u;
    }
    public void addUser() {
    	try{
        String   sql  = "insert into t_hlbbs_user(nvcNickName,nvcPass,nvcEmailAddress,intRoleType,nvcSex,nvcHeadPortrait,intIntegral,nvcPersonalizedSignature,intLevel) values(?,?,?,?,?,?,?,?,?)";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setString(1,user.getName());
        pstmt.setString(2,user.getPassWord());
        pstmt.setString(3,user.getEmailAddress());
        pstmt.setInt(4,user.getRoleType());
        pstmt.setString(5,user.getSex());
        pstmt.setString(6,user.getHeadPortrait());
        pstmt.setInt(7,user.getIntegral());
        pstmt.setString(8,user.getPersonalizedSignature());
        pstmt.setInt(9,user.getLevel());
        int result = pstmt.executeUpdate();
		System.out.println("add result="+result);
    	}catch(SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally
    	{
    		this.ClosePreStatement(pstmt);
    	}
    }    
    
    /**
     * �޸��û�����
     * @param user
     * @return ��������
     */
    public void updateUser(){
    	try{
        String   sql  = "update t_hlbbs_user set nvcPass=? where nvcNickName=?";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setString(1,user.getPassWord() );
        pstmt.setString(2, user.getName());
        int result = pstmt.executeUpdate();
		System.out.println("update result="+result);
    	}catch(SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally
    	{
    		this.ClosePreStatement(pstmt);
    	}
    }
    
    /**
     * �����û��������û�
     * @param user
     * @return �����û�����ѯ���û�����
     */
    public User findUserByName() {
        String sql  = "select * from t_hlbbs_user where nvcNickName=?";
        User   user1 = null;
        try {
            pstmt = m_con.prepareStatement(sql);    // ȡ��PreparedStatement����
            pstmt.setString(1, user.getName());             // ���ò���
            rs    = pstmt.executeQuery();          // ִ��SQLȡ�ý����
            while( rs.next() ) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("nvcNickName"));
                user1.setEmailAddress(rs.getString("nvcEmailAddress"));
                user1.setPassWord(rs.getString("nvcPass"));
                user1.setRoleType(rs.getInt("intRoleType"));
                user1.setSex(rs.getString("nvcSex"));
                user1.setHeadPortrait(rs.getString("nvcHeadPortrait"));
                user1.setIntegral(rs.getInt("intIntegral"));
                user1.setPersonalizedSignature(rs.getString("nvcPersonalizedSignature"));
                user1.setLevel(rs.getInt("intLevel"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();                   // �����쳣
        } finally {
          this.CloseResultSet(rs);
        }
        return user1;
    }
    
    /**
     * �����û�id�����û�
     * @param user
     * @return ����uid��ѯ���û�����
     */
    public User findUserById() {
        String sql  = "select * from t_hlbbs_user where nvcPass=?";
        User   user1 = null;
        try {
            pstmt = m_con.prepareStatement(sql);       //ȡ��PreparedStatement����
            pstmt.setInt(1, user.getId());                     //���ò���
            rs    = pstmt.executeQuery();             //ִ��SQLȡ�ý����
            while( rs.next() ) {
                user1 = new User();
                user1.setId(rs.getInt("id"));
                user1.setName(rs.getString("nvcNickName"));
                user1.setEmailAddress(rs.getString("nvcEmailAddress"));
                user1.setPassWord(rs.getString("nvcPass"));
                user1.setRoleType(rs.getInt("intRoleType"));
                user1.setSex(rs.getString("nvcSex"));
                user1.setHeadPortrait(rs.getString("nvcHeadPortrait"));
                user1.setIntegral(rs.getInt("intIntegral"));
                user1.setPersonalizedSignature(rs.getString("nvcPersonalizedSignature"));
                user1.setLevel(rs.getInt("intLevel"));
            }
        } catch (Exception e) {
            e.printStackTrace();                     // �����쳣
        } finally {
            this.CloseResultSet(rs);         // �ͷ���Դ
        }        
        return user1;
    }

}
