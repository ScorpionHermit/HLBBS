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
	private PreparedStatement pstmt = null;       // 用于执行SQL语句
    private ResultSet         rs    = null;       // 用户保存查询结果集   
	
    /**
     * 增加用户
     * @param user
     * @return 增加条数
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
     * 修改用户密码
     * @param user
     * @return 更新条数
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
     * 修改用户密码
     * @param user
     * @return 更新条数
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
     * 根据用户名查找用户
     * @param user
     * @return 根据用户名查询的用户对象
     */
    public void findUserByName() {
        String sql  = "select * from t_hlbbs_user where nvcNickName=?";
        try {
            pstmt = m_con.prepareStatement(sql);    // 取得PreparedStatement对象
            pstmt.setString(1, user.getName());             // 设置参数
            rs    = pstmt.executeQuery();          // 执行SQL取得结果集
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
            e.printStackTrace();                   // 处理异常
        } finally {
          this.CloseResultSet(rs);
          this.ClosePreStatement(pstmt);
        }
        
    }
    
    /**
     * 根据用户id查找用户
     * @param user
     * @return 根据uid查询的用户对象
     */
    public void findUserById() {
        String sql  = "select * from t_hlbbs_user where id=?";
        try {
            pstmt = m_con.prepareStatement(sql);      //取得PreparedStatement对象
            pstmt.setInt(1, user.getId());            //设置参数
            rs    = pstmt.executeQuery();             //执行SQL取得结果集
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
            e.printStackTrace();                     // 处理异常
        } finally {
            this.CloseResultSet(rs);         // 释放资源
            this.ClosePreStatement(pstmt);
        }        
    }

}
