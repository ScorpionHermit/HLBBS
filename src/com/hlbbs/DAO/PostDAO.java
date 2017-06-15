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
		String sql ="insert into t_hlbbs_posts(ncvTitle, nvcPostman, ncvContent,"
				+ " dtmPostTime, intReplyCount, nvcSectionId, intIsBoutique) "
				+ "values(?, ?, ?, ?, ?, ?, ?)";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setString(1, post.getTitle());
			pStatement.setString(2, post.getPostMan());
			pStatement.setString(3, post.getContent());
			pStatement.setString(4, post.getPostTime());
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
	public int deleteComment() {
		int result =0;
		String sql="delete from t_hlbbs_posts where id=?";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getId());
			result=pStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
		}
		
		return result;
	}
	
	/**
	 * 查询全部帖子
	 * @return
	 */
	public ArrayList<Post> searchAll()
	{
		String sql ="select * from t_hlbbs_posts";
		ArrayList<Post> list =new ArrayList<>();

		try {
			pStatement =m_con.prepareStatement(sql);
			rSet =pStatement.executeQuery();
			while (rSet.next()) {
				post.setId(rSet.getInt("id"));
				post.setTitle(rSet.getString("nvcTitle"));
				post.setPostMan(rSet.getString("nvcPostman"));
				post.setContent(rSet.getString("nvcContent"));
				post.setPostTime(rSet.getString("dtmPostTime"));
				post.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				post.setReplyCount(rSet.getInt("intReplyCount"));
				post.setSectionID(rSet.getInt("nvcSectionId"));
				post.setIsBoutique(rSet.getInt("intIsBoutique"));
		        list.add(post);
		        
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		
		return list;
	}
	
	/**
	 * 根据版块ID查询帖子
	 * @return
	 */
	public Post searchById() {
		String sql="select * from t_hlbbs_posts where intSectionId=?";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, post.getSectionID());
			rSet =pStatement.executeQuery();
			
			if (rSet.next()) {
				post.setId(rSet.getInt("id"));
				post.setTitle(rSet.getString("nvcTitle"));
				post.setPostMan(rSet.getString("intPostman"));
				post.setContent(rSet.getString("nvcContent"));
				post.setPostTime(rSet.getString("dtmPostTime"));
				post.setFinalReplyTime(rSet.getString("dtmFinalReplyTime"));
				post.setReplyCount(rSet.getInt("intReplyCount"));
				post.setSectionID(rSet.getInt("intSectionId"));
				post.setIsBoutique(rSet.getInt("intIsBoutique"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return post;
	}
}
