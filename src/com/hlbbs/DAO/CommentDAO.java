package com.hlbbs.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.hlbbs.Modal.Comment;

public class CommentDAO extends DAO
{
	private Comment comment;
	private PreparedStatement pStatement = null;
	private ResultSet rSet = null;

	public CommentDAO(Comment com)
	{
		this.comment = com;
	}

	public int addComment()
	{
		int result = 0;
		String sql = "insert into t_hlbbs_comment(intUserId,intPostsId,nvcContent,nvcTitle) values(?,?,?,?)";
		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getUserId());
			pStatement.setInt(2, comment.getPostsId());
			pStatement.setString(3, comment.getContent());
			pStatement.setString(4, comment.getTitle());
			result = pStatement.executeUpdate();

		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
		}
		return result;
	}

	public int editComment()
	{
		int result = 0;
		String sql = "update t_hlbbs_comment set nvcContent=?,nvcTitle=? where id =?";
		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setString(1, comment.getContent());
			pStatement.setString(2, comment.getTitle());
			pStatement.setInt(3, comment.getId());
			result = pStatement.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
		}
		return result;

	}

	public int deleteComment()
	{
		int result = 0;
		String sql = "delete from t_hlbbs_comment where id=?";
		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getId());
			result = pStatement.executeUpdate();
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
		}

		return result;
	}

	/**
	 * 查询一个帖子所有的评论
	 * 
	 * @return
	 */
	public ArrayList<Comment> searchByPostsId()
	{
		String sql = "select * from t_hlbbs_comment where intPostsId=?";
		ArrayList<Comment> list = new ArrayList<>();

		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getPostsId());
			rSet = pStatement.executeQuery();
			while (rSet.next())
			{
				Comment com = new Comment();
				com.setId(rSet.getInt("id"));
				com.setUserId(rSet.getInt("intUserId"));
				com.setPostsId(rSet.getInt("intPostsId"));
				com.setComTime(rSet.getString("dtmComTime"));
				com.setContent(rSet.getString("nvcContent"));
				com.setBuildingNum(rSet.getInt("intBuildingNum"));
				com.setTitle(rSet.getString("nvcTitle"));
				list.add(com);

			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return list;
	}

	public int getCountByPostId()
	{
		String sql = "SELECT COUNT(id) AS commentCount FROM t_hlbbs_comment WHERE intPostsId=?";
		int count = -1;
		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getPostsId());
			rSet = pStatement.executeQuery();
			if(rSet.first())
				count = rSet.getInt("commentCount");
				
		} catch (SQLException e)
		{
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return count;
	}
	/**
	 * 查询一个用户所有的评论
	 * 
	 * @return
	 */
	public ArrayList<Comment> searchByUserId()
	{
		String sql = "select * from t_hlbbs_comment where intUserId=?";
		ArrayList<Comment> list = new ArrayList<>();

		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getUserId());
			rSet = pStatement.executeQuery();
			while (rSet.next())
			{
				Comment com = new Comment();
				com.setId(rSet.getInt("id"));
				com.setUserId(rSet.getInt("intUserId"));
				com.setPostsId(rSet.getInt("intPostsId"));
				com.setComTime(rSet.getString("dtmComTime"));
				com.setContent(rSet.getString("nvcContent"));
				com.setBuildingNum(rSet.getInt("intBuildingNum"));
				com.setTitle(rSet.getString("nvcTitle"));
				list.add(com);
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
			this.CloseResultSet(rSet);
		}
		return list;
	}

	public boolean searchById()
	{
		String sql = "select * from t_hlbbs_comment where id=?";
		boolean issuccess=false;
		try
		{
			pStatement = m_con.prepareStatement(sql);
			pStatement.setInt(1, comment.getId());
			rSet = pStatement.executeQuery();
			if (rSet.first())
			{
				comment.setId(rSet.getInt("id"));
				comment.setUserId(rSet.getInt("intUserId"));
				comment.setPostsId(rSet.getInt("intPostsId"));
				comment.setComTime(rSet.getString("dtmComTime"));
				comment.setContent(rSet.getString("nvcContent"));
				comment.setBuildingNum(rSet.getInt("intBuildingNum"));
				comment.setTitle(rSet.getString("nvcTitle"));
				issuccess=true;
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally
		{
			this.ClosePreStatement(pStatement);
		}
		return issuccess;
	}

}
