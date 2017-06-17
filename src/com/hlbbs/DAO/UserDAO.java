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
	private PreparedStatement pstmt = null;       // 锟斤拷锟斤拷执锟斤拷SQL锟斤拷锟�
    private ResultSet         rs    = null;       // 锟矫伙拷锟斤拷锟斤拷锟窖拷锟斤拷锟斤拷   
	
    /**
     * 锟斤拷锟斤拷锟矫伙拷
     * @param user
     * @return 锟斤拷锟斤拷锟斤拷锟斤拷
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
     * 锟睫革拷锟矫伙拷锟斤拷锟斤拷
     * @param user
     * @return 锟斤拷锟斤拷锟斤拷锟斤拷
     */
    public boolean updateUserPwd(){
    	boolean isUpdate = false;
    	try{
        String   sql  = "update t_hlbbs_user set nvcPass=? where nvcNickName=?";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setString(1,user.getPassWord() );
        pstmt.setString(2, user.getName());
        int result = pstmt.executeUpdate();
		if(result!=0)
			isUpdate = true;
    	}catch(SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally
    	{
    		this.ClosePreStatement(pstmt);
    	}
    	return isUpdate;
    }
    
    public boolean updateUserIntegral(){
    	boolean isUpdate = false;
    	try{
        String   sql  = "update t_hlbbs_user set intIntegral=? where nvcNickName=?";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setInt(1,user.getIntegral());
        pstmt.setString(2, user.getName());
        int result = pstmt.executeUpdate();
		if(result!=0)
			isUpdate = true;
    	}catch(SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally
    	{
    		this.ClosePreStatement(pstmt);
    	}
    	return isUpdate;
    }
    /**
     * 锟睫革拷锟矫伙拷锟斤拷锟斤拷
     * @param user
     * @return 锟斤拷锟斤拷锟斤拷锟斤拷
     */
    public boolean updateUserInf(){
    	boolean isUpdate = false;
    	try{
        String   sql  = "update t_hlbbs_user set nvcNickName=?,nvcSex=?,nvcEmailAddress=?,nvcPersonalizedSignature=? where id=?";
        pstmt=m_con.prepareStatement(sql);
        pstmt.setString(1,user.getName() );
        pstmt.setString(2, user.getSex());
        pstmt.setString(3, user.getEmailAddress());
        pstmt.setString(4, user.getPersonalizedSignature());
        pstmt.setString(5, Integer.toString(user.getId()));
        int result = pstmt.executeUpdate();
        if(result!=0)
			isUpdate = true;
    	}catch(SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}finally
    	{
    		this.ClosePreStatement(pstmt);
    	}
    	return isUpdate;
    }
    
    
    /**
     * 锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷锟斤拷锟矫伙拷
     * @param user
     * @return 锟斤拷锟斤拷锟矫伙拷锟斤拷锟斤拷询锟斤拷锟矫伙拷锟斤拷锟斤拷
     */
    public void findUserByName() {
        String sql  = "select * from t_hlbbs_user where nvcNickName=?";
        try {
            pstmt = m_con.prepareStatement(sql);    // 取锟斤拷PreparedStatement锟斤拷锟斤拷
            pstmt.setString(1, user.getName());             // 锟斤拷锟矫诧拷锟斤拷
            rs    = pstmt.executeQuery();          // 执锟斤拷SQL取锟矫斤拷锟斤拷锟�
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
            e.printStackTrace();                   // 锟斤拷锟斤拷锟届常
        } finally {
          this.CloseResultSet(rs);
          this.ClosePreStatement(pstmt);
        }
        
    }
    
    /**
     * 锟斤拷锟斤拷锟矫伙拷id锟斤拷锟斤拷锟矫伙拷
     * @param user
     * @return 锟斤拷锟斤拷uid锟斤拷询锟斤拷锟矫伙拷锟斤拷锟斤拷
     */
    public void findUserById() {
        String sql  = "select * from t_hlbbs_user where id=?";
        try {
            pstmt = m_con.prepareStatement(sql);      //取锟斤拷PreparedStatement锟斤拷锟斤拷
            pstmt.setInt(1, user.getId());            //锟斤拷锟矫诧拷锟斤拷
            rs    = pstmt.executeQuery();             //执锟斤拷SQL取锟矫斤拷锟斤拷锟�
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
            e.printStackTrace();                     // 锟斤拷锟斤拷锟届常
        } finally {
            this.CloseResultSet(rs);         // 锟酵凤拷锟斤拷源
            this.ClosePreStatement(pstmt);
        }        
    }
    
    /**
     * 查询一个用户的等级
     * @return
     */
    public String getLevel() {
    	String level = "菜鸟";
    	String sql = "select nvcLevelName from t_hlbbs_level where intLevel=?";
    	
    	try {
    		pstmt = m_con.prepareStatement(sql);
    		pstmt.setInt(1, user.getLevel());
    		rs = pstmt.executeQuery();
    		
    		if (rs.first()) {
    			level = rs.getString("nvcLevelName");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		this.CloseResultSet(rs);
    	}
    	
    	return level;
    }
    
    /**
     * 查询一个用户的积分
     * @return
     */
    public int getIntegral() {
    	int integral = 0;
    	String sql = "select intIntegral from t_hlbbs_level where intLevel=?";
    	
    	try {
    		pstmt = m_con.prepareStatement(sql);
    		pstmt.setInt(1, user.getId());
    		rs = pstmt.executeQuery();
    		
    		if (rs.first()) {
    			integral = rs.getInt("intIntegral");
    		}
    	} catch (SQLException e) {
    		e.printStackTrace();
    	} finally {
    		this.CloseResultSet(rs);
    	}
    	return integral;
    }
}
