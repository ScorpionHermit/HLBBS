/**
 * @filename 	PostDAO.java
 * @author 		ScorpionHermit
 * @email 		1583801169@qq.com
 * @date 		2017年6月15日
 */

package com.hlbbs.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.hlbbs.Modal.Post;

public class PostDAO extends DAO {
	private Post post;
	private PreparedStatement pStatement=null;
	private ResultSet rSet=null;
	
	public PostDAO(Post post) {
		this.post = post;
	}
	
	/**
	 * 发表帖子
	 * @return
	 */
	public int addPost() {
		int result=0;
		String sql ="insert into t_hlbbs_posts(nvcTitle, intPostman, nvcContent,"
				+ " dtmPostTime, intReplyCount, intSectionId, intIsBoutique) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setString(1, post.getTitle());
			pStatement.setInt(2, post.getPostMan());
			pStatement.setString(3, post.getContent());
			pStatement.setTimestamp(4, new java.sql.Timestamp(new Date().getTime()));
			pStatement.setInt(5, post.getReplyCount());
			pStatement.setInt(6, post.getSectionID());
			pStatement.setInt(7, post.getIsBoutique());
			result= pStatement.executeUpdate();	
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			this.ClosePreStatement(pStatement);
		}
		
		return result;
	}
	
	/**
	 * 修改帖子
	 * @return
	 */
	public int editComment() {
		int result=0;
		String sql ="update t_hlbbs_posts set nvcTitle=?, nvcContent=? where id =?";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setString(1, post.getTitle());
			pStatement.setString(2, post.getContent());
			pStatement.setInt(3, post.getId());
			result=pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
		}
		
		return result;
	}
	
	/**
	 * 删除帖子
	 * @return
	 */
	public boolean deletePost() {
		int result =0;
		boolean isSuccess = false;
		String sql="delete from t_hlbbs_posts where id= ? ";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getId());
			result=pStatement.executeUpdate();
			if(result > 0)
				isSuccess = true;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
		}
		
		return isSuccess;
	}
	
	/**
	 * 查询某个版块下的帖子数
	 * @return
	 */
	public int postsCount() {
		int count = 0;
		String sql = "select count(*) from t_hlbbs_posts where intSectionId=?";
		
		try {
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getSectionID());
			rSet = pStatement.executeQuery();
			
			if (rSet.next()) {
				count = rSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
		}
		
		return count;
	}
	
	/**
	 * 查询所有帖子
	 * @return
	 */
	public ArrayList<Post> getAllPost()
	{
		String sql="select * from t_hlbbs_posts";
		ArrayList<Post> list =new ArrayList<Post>();
		
		try {
			pStatement=m_con.prepareStatement(sql);
			rSet =pStatement.executeQuery();
			
			while (rSet.next()) 
			{
				Post p = new Post();
				p.setId(rSet.getInt("id"));
				p.setTitle(rSet.getString("nvcTitle"));
				p.setPostMan(rSet.getInt("intPostman"));
				p.setContent(rSet.getString("nvcContent"));
				p.setPostTime(rSet.getString("dtmPostTime"));
				p.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				p.setReplyCount(rSet.getInt("intReplyCount"));
				p.setSectionID(rSet.getInt("intSectionId"));
				p.setIsBoutique(rSet.getInt("intIsBoutique"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return list;
	}
	
	/**
	 * 根据版块ID查询最新回复的帖子
	 * @return
	 */
	public boolean searchNewById() {
		boolean isSuccess = false;
		String sql = "select * from t_hlbbs_posts where intSectionId=? "
				+ "order by dtmFinalReplyTime desc limit 1";
		try {
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getSectionID());
			rSet = pStatement.executeQuery();
			
			if (rSet.next()) {
				isSuccess = true;
				post.setId(rSet.getInt("id"));
				post.setTitle(rSet.getString("nvcTitle"));
				post.setPostMan(rSet.getInt("intPostman"));
				post.setContent(rSet.getString("nvcContent"));
				post.setPostTime(rSet.getString("dtmPostTime"));
				post.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				post.setReplyCount(rSet.getInt("intReplyCount"));
				post.setSectionID(rSet.getInt("intSectionId"));
				post.setIsBoutique(rSet.getInt("intIsBoutique"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
		}
		return isSuccess;
	}
	
	/**
	 * 查询某个版块下的所有帖子
	 * @return
	 */
	public ArrayList<Post> searchById() {
		String sql="select * from t_hlbbs_posts where intSectionId=? order by "
				+ "dtmFinalReplyTime desc";
		ArrayList<Post> list =new ArrayList<Post>();
		
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getSectionID());
			rSet =pStatement.executeQuery();
			
			while (rSet.next()) 
			{
				Post p = new Post();
				p.setId(rSet.getInt("id"));
				p.setTitle(rSet.getString("nvcTitle"));
				p.setPostMan(rSet.getInt("intPostman"));
				p.setContent(rSet.getString("nvcContent"));
				p.setPostTime(rSet.getString("dtmPostTime"));
				p.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				p.setReplyCount(rSet.getInt("intReplyCount"));
				p.setSectionID(rSet.getInt("intSectionId"));
				p.setIsBoutique(rSet.getInt("intIsBoutique"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return list;
	}
	
	/**
	 * 更新最新回复时间
	 * @return
	 */
	public int updateFinalReplyTime() {
		String sql = "update t_hlbbs_posts set dtmFinalReplyTime=? where id=?";
		int result =0;
		
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setString(1, post.getFinalReplyTime());
			pStatement.setInt(2, post.getId());
			result = pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		
		return result;
	}
	/**
	 * 根据帖子id查询帖子
	 * @return
	 */
	public boolean searchByPostId()
	{
		String sql ="select * from t_hlbbs_posts where id=?";
		boolean issuccess=false;
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getId());
			rSet=pStatement.executeQuery();
			if(rSet.first())
			{
				post.setId(rSet.getInt("id"));
				post.setTitle(rSet.getString("nvcTitle"));
				post.setPostMan(rSet.getInt("intPostman"));
				post.setContent(rSet.getString("nvcContent"));
				post.setPostTime(rSet.getString("dtmPostTime"));
				post.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				post.setReplyCount(rSet.getInt("intReplyCount"));
				post.setSectionID(rSet.getInt("intSectionId"));
				post.setIsBoutique(rSet.getInt("intIsBoutique"));
				issuccess=true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return issuccess;
	}
	
	/**
	 * 查询一个帖子的评论数
	 * @return
	 */
	public int commentCount()
	{

		String sql = "select count(*) from t_hlbbs_comment where intPostsId=?";

		int rowCount = 0;   
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getId());
			rSet=pStatement.executeQuery();    
			 
			if(rSet.next())    
			{    

			    rowCount=rSet.getInt(1);    

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return rowCount;
	}
	
	/**
	 * 根据用户ID查询该用户发的全部帖子
	 * @return
	 */
	public ArrayList<Post> searchByPostsMan() {
		String sql="select * from t_hlbbs_posts where intPostman=? order by "
				+ "dtmFinalReplyTime desc";
		ArrayList<Post> list =new ArrayList<Post>();
		
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getPostMan());
			rSet =pStatement.executeQuery();
			
			while (rSet.next()) 
			{
				Post p = new Post();
				p.setId(rSet.getInt("id"));
				p.setTitle(rSet.getString("nvcTitle"));
				p.setPostMan(rSet.getInt("intPostman"));
				p.setContent(rSet.getString("nvcContent"));
				p.setPostTime(rSet.getString("dtmPostTime"));
				p.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				p.setReplyCount(rSet.getInt("intReplyCount"));
				p.setSectionID(rSet.getInt("intSectionId"));
				p.setIsBoutique(rSet.getInt("intIsBoutique"));
				list.add(p);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return list;
	}
}
