package com.hlbbs.Control;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.PostDAO;
import com.hlbbs.Modal.Post;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class PostControl
 */
@WebServlet("/PostControl")
public class PostControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String   title     = request.getParameter("title");             // 取得帖子标题
		String   content   = request.getParameter("content");           // 取得帖子内容
		
		User     user      = (User)request.getSession().getAttribute("user");        // 从session中取得登录用户
		int     boardId    = Integer.parseInt( request.getParameter("boardid") ); // 取得版块id

		if( user!=null ) {                                                 // 判断用户是否已经登录
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setSectionID(boardId);
			post.setPostMan(user.getId());
			post.setIsBoutique(0);
			post.setReplyCount(0);
			PostDAO postDao  = new PostDAO(post);                        // 得到主题Dao的实例
			postDao.addPost();                                        // 保存主题帖子
			request.getRequestDispatcher("list.jsp?page=1&boardid="+boardId).forward(request,response);    // 跳转
			return;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
