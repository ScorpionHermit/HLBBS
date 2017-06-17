package com.hlbbs.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import com.hlbbs.Modal.Sign;

public class SignDAO extends DAO {
	public Sign sign =new Sign();
	   
	   private PreparedStatement pstmt = null;     
	   private ResultSet         rs    = null;
	   
	   public SignDAO(Sign s){
		   sign = s;
	   }
	   
	   /*
	    * 插入签到记录
	    */
	   public boolean addSign()
		{
			boolean isSuccess = false;
			try
			{
				String sql = "insert into t_hlbbs_Sign(FinalSignTime,UserId) values(?,?)";
				pstmt = m_con.prepareStatement(sql);
				pstmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
				pstmt.setInt(2, sign.getUserId());
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
	   
	   
	   /*
	    * 更改签到记录
	    */
	   public boolean updateSign()
		{
			boolean isUpdate = false;
			try
			{
				String sql = "update t_hlbbs_Sign set FinalSignTime=? where UserId=?";
				pstmt = m_con.prepareStatement(sql);
				pstmt.setTimestamp(1, new java.sql.Timestamp(new Date().getTime()));
				pstmt.setInt(2, sign.getUserId());
				int result = pstmt.executeUpdate();
				if (result != 0)
					isUpdate = true;
			} catch (SQLException e)
			{
				e.printStackTrace();
			} finally
			{
				this.ClosePreStatement(pstmt);
			}
			return isUpdate;
		}
	   /*
	    * 获得签到记录
	    */
	   public  void findSign()
	   {
		   String sql  = "select * from t_hlbbs_Sign where UserId=?";
	        try {
	            pstmt = m_con.prepareStatement(sql);    
	            pstmt.setInt(1,sign.getUserId());             
	            rs    = pstmt.executeQuery();          
	            while( rs.next() ) {
	                sign.setFinalSignTime(rs.getString("FinalSignTime"));
	            }
	            
	        } catch (Exception e) {
	            e.printStackTrace();                 
	        } finally {
	          this.CloseResultSet(rs);
	          this.ClosePreStatement(pstmt);
	        }
	   }
}
