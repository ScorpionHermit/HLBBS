package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hlbbs.DAO.CommentDAO;
import com.hlbbs.Modal.Comment;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class ManagerControl
 */
@WebServlet("/ReplyControl")
public class ReplyControl extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReplyControl() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		String action = request.getParameter("option").toString();
		Integer boardId = Integer.parseInt(request.getParameter("boardid")); // 取得版块id
		Integer topicId = Integer.parseInt(request.getParameter("topicid")); // 取得主题id
		if (boardId == null || topicId == null) {
			request.getRequestDispatcher(request.getContextPath() + "/error.jsp?msg=参数错误").forward(request, response);
			return;
		}

		if (action.equals("newreply")) {

			if (newReply(request, response)) {

				response.sendRedirect(request.getContextPath() + "/DetailControl?page=1&boardid=" + boardId
						+ "&topicid=" + topicId + "&action=show"); // 跳转

				return;

			} else {
				request.getRequestDispatcher(request.getContextPath() + "/error.jsp?msg=您未登录").forward(request,
						response);
				return;
			}
		} else if (action.equals("deletereply")) {
			if (deleteReply(request, response)) {
				response.sendRedirect(request.getContextPath() + "/DetailControl?page=1&boardid=" + boardId
						+ "&topicid=" + topicId + "&action=show"); // 跳转
				return;
			} else {
				request.getRequestDispatcher(request.getContextPath() + "/error.jsp?msg=刪除评论失败").forward(request,
						response);
				return;
			}
		}
		else if(action.equals("updatereply"))
		{
			Comment comment=searchReply(request, response);
			if(comment!=null)
			{
				request.setAttribute("updatereply", comment);
				request.getRequestDispatcher("post.jsp?boardid="+boardId+"&topicid="+topicId+"&post=updatereply").forward(request, response);
				return;
			}
			else {
				request.getRequestDispatcher(request.getContextPath() + "/error.jsp?msg=更新失败").forward(request,
						response);
				return;
			}
		}
		else if(action.equals("update"))
		{
			if(updateReply(request, response)){
				
				response.sendRedirect(request.getContextPath() + "/DetailControl?page=1&boardid=" + boardId
						+ "&topicid=" + topicId + "&action=show"); // 跳转

				return;
			}
			else {
				request.getRequestDispatcher(request.getContextPath() + "/error.jsp?msg=修改出错").forward(request,
						response);
				return;
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	private boolean newReply(HttpServletRequest request, HttpServletResponse response) {

		String title = request.getParameter("title"); // 取得评论标题
		String content = request.getParameter("content"); // 取得评论内容
		HttpSession session = request.getSession();
		int boardId = Integer.parseInt(request.getParameter("boardid")); // 取得版块id
		int topicId = Integer.parseInt(request.getParameter("topicid")); // 取得主题id
		User user = (User) session.getAttribute("user"); // 从session中取得登录用户
		if (user != null) { // 判断用户是否已经登录
			Comment comment = new Comment();
			comment.setTitle(title);
			comment.setContent(content);
			comment.setUserId(user.getId());
			comment.setPostsId(topicId);
			CommentDAO replyDao = new CommentDAO(comment); // 得到主题Dao的实例

			if (replyDao.addComment() > 0) {
				return true;

			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	private boolean deleteReply(HttpServletRequest request, HttpServletResponse response) {
		Integer id = Integer.parseInt(request.getParameter("id"));
		if (id == null) {
			return false;
		} else {
			Comment comment = new Comment();
			comment.setId(id);
			CommentDAO dao = new CommentDAO(comment);
			if (dao.deleteComment() > 0) {
				return true;
			} else {
				return false;
			}
		}
	}
	private Comment searchReply(HttpServletRequest request, HttpServletResponse response)
	{
		Integer id = Integer.parseInt(request.getParameter("id"));
		if (id == null) {
			return null;
		}
		else {
			Comment comment =new Comment();
			comment.setId(id);
			CommentDAO dao =new CommentDAO(comment);
			if(dao.searchById())
			{
				return comment;
			}
			else {
				return null;
			}
		}
		
	}
	private boolean updateReply(HttpServletRequest request, HttpServletResponse response)
	{
		String title = request.getParameter("title"); // 取得评论标题
		String content = request.getParameter("content"); // 取得评论内容
		Integer replyid =Integer.parseInt(request.getParameter("replyid"));
		HttpSession session =request.getSession();
		User user = (User) session.getAttribute("user"); // 从session中取得登录用户
		if(user!=null)
		{
			if(title!=null&&content!=null&&replyid!=null)
			{
				Comment comment =new Comment();
				comment.setId(replyid);
				comment.setTitle(title);
				comment.setContent(content);
				CommentDAO dao =new CommentDAO(comment);
				if(dao.editComment()>0)
				{
					return true;
				}
				else {
					return false;
				}
			}
			else {
				return false;
			}
			
			
		}
		else {
			return false;
		}
	}
}
