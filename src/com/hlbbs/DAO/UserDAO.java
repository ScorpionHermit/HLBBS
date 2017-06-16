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
	public User user;
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

	public boolean addUser()
	{
		boolean isSuccess = false;
		try
		{
			String sql = "insert into t_hlbbs_user(nvcNickName,nvcPass,nvcEmailAddress,intRoleType,nvcSex,nvcHeadPortrait,intIntegral,nvcPersonalizedSignature,intLevel) values(?,?,?,?,?,?,?,?,?)";
			pstmt = m_con.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassWord());
			pstmt.setString(3, user.getEmailAddress());
			pstmt.setInt(4, user.getRoleType());
			pstmt.setString(5, user.getSex());
			pstmt.setString(6, user.getHeadPortrait());
			pstmt.setInt(7, user.getIntegral());
			pstmt.setString(8, user.getPersonalizedSignature());
			pstmt.setInt(9, user.getLevel());
			int result = pstmt.executeUpdate();
			if (result != 0)
				isSuccess = true;
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pstmt);
		}
		return isSuccess;
	}
    
    /**
     * �޸��û�����
     * @param user
     * @return ��������
     */
    public void updateUserPwd(){
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
     * �޸��û�����
     * @param user
     * @return ��������
     */
    public void updateUserInf(){
    	try{
        String   sql  = "update t_hlbbs_user set nvcNickName=?,nvcSex=?,nvcEmailAddress=?,nvcPersonalizedSignature=? and  where id=?";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setString(1,user.getName() );
        pstmt.setString(2, user.getSex());
        pstmt.setString(3, user.getEmailAddress());
        pstmt.setString(4, user.getPersonalizedSignature());
        pstmt.setString(5, Integer.toString(user.getId()));
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
    public void findUserByName() {
        String sql  = "select * from t_hlbbs_user where nvcNickName=?";
        try {
            pstmt = m_con.prepareStatement(sql);    // ȡ��PreparedStatement����
            pstmt.setString(1, user.getName());             // ���ò���
            rs    = pstmt.executeQuery();          // ִ��SQLȡ�ý����
            while( rs.next() ) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nvcNickName"));
                user.setEmailAddress(rs.getString("nvcEmailAddress"));
                user.setPassWord(rs.getString("nvcPass"));
                user.setRoleType(rs.getInt("intRoleType"));
                user.setSex(rs.getString("nvcSex"));
                user.setHeadPortrait(rs.getString("nvcHeadPortrait"));
                user.setIntegral(rs.getInt("intIntegral"));
                user.setPersonalizedSignature(rs.getString("nvcPersonalizedSignature"));
                user.setLevel(rs.getInt("intLevel"));
                user.setRegistertime(rs.getString("dtmRegTime"));
            }
            
        } catch (Exception e) {
            e.printStackTrace();                   // �����쳣
        } finally {
          this.CloseResultSet(rs);
          this.ClosePreStatement(pstmt);
        }
        
    }
    
    /**
     * �����û�id�����û�
     * @param user
     * @return ����uid��ѯ���û�����
     */
    public void findUserById() {
        String sql  = "select * from t_hlbbs_user where id=?";
        try {
            pstmt = m_con.prepareStatement(sql);      //ȡ��PreparedStatement����
            pstmt.setInt(1, user.getId());            //���ò���
            rs    = pstmt.executeQuery();             //ִ��SQLȡ�ý����
            while( rs.next() ) {
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("nvcNickName"));
                user.setEmailAddress(rs.getString("nvcEmailAddress"));
                user.setPassWord(rs.getString("nvcPass"));
                user.setRoleType(rs.getInt("intRoleType"));
                user.setSex(rs.getString("nvcSex"));
                user.setHeadPortrait(rs.getString("nvcHeadPortrait"));
                user.setIntegral(rs.getInt("intIntegral"));
                user.setPersonalizedSignature(rs.getString("nvcPersonalizedSignature"));
                user.setLevel(rs.getInt("intLevel"));
                user.setRegistertime(rs.getString("dtmRegTime"));
            }
        } catch (Exception e) {
            e.printStackTrace();                     // �����쳣
        } finally {
            this.CloseResultSet(rs);         // �ͷ���Դ
            this.ClosePreStatement(pstmt);
        }        
    }

}
