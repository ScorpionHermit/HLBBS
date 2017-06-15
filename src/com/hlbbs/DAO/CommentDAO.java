package com.hlbbs.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hlbbs.Modal.Comment;

public class CommentDAO extends DAO{
	private Comment comment;
	private 	PreparedStatement pStatement=null;
	private ResultSet rSet=null;
	public CommentDAO(Comment com)
	{
		this.comment=com;
	}
	public int addComment()
	{
		int result=0;
		String sql ="insert into t_hlbbs_comment(intUserId,intPostsId,nvcContent) values(?,?,?)";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getUserId());
			pStatement.setInt(2, comment.getPostsId());
			pStatement.setString(3, comment.getContent());
			result= pStatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			this.ClosePreStatement(pStatement);
		}
		return result;
	}
	public int editComment()
	{
		int result=0;
		String sql ="update t_hlbbs_comment set nvcContent=? where id =?";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setString(1, comment.getContent());
			pStatement.setInt(2, comment.getId());
			result=pStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return result;
		
	}
public int deleteComment()
{
	int result =0;
	String sql="delete from t_hlbbs_comment where id=?";
	try {
		pStatement=m_con.prepareStatement(sql);
		pStatement.setInt(1, comment.getId());
		result=pStatement.executeUpdate();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		this.ClosePreStatement(pStatement);
	}

	return result;
}
	public ArrayList<Comment> searchAll()
	{
		String sql ="select * from t_hlbbs_comment";
		ArrayList<Comment> list =new ArrayList<>();

		try {
			pStatement =m_con.prepareStatement(sql);
			rSet =pStatement.executeQuery();
			while(rSet.next())
			{
				comment.setId(rSet.getInt("id"));
				comment.setUserId(rSet.getInt("intUserId"));
				comment.setPostsId(rSet.getInt("intPostsId"));
		        comment.setContent(rSet.getString("dtmComTime"));
		        comment.setContent(rSet.getString("nvcContent"));
		        comment.setBuildingNum(rSet.getInt("intBuildingNum"));
		        list.add(comment);
		        
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return list;
	}
	
	public Comment searchById()
	{
		String sql="select * from t_hlbbs_comment where id=?";
		try {
			pStatement=m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getId());
			rSet =pStatement.executeQuery();
			if(rSet.next())
			{
				comment.setId(rSet.getInt("id"));
				comment.setUserId(rSet.getInt("intUserId"));
				comment.setPostsId(rSet.getInt("intPostsId"));
		        comment.setContent(rSet.getString("dtmComTime"));
		        comment.setContent(rSet.getString("nvcContent"));
		        comment.setBuildingNum(rSet.getInt("intBuildingNum"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			this.ClosePreStatement(pStatement);
		}
		return comment;
	}

}
