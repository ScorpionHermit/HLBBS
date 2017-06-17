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
		String   title     = request.getParameter("title");             // ȡ�����ӱ���
		String   content   = request.getParameter("content");           // ȡ����������
		
		User     user      = (User)request.getSession().getAttribute("user");        // ��session��ȡ�õ�¼�û�
		int     boardId    = Integer.parseInt( request.getParameter("boardid") ); // ȡ�ð��id

		if( user!=null ) {                                                 // �ж��û��Ƿ��Ѿ���¼
			Post post = new Post();
			post.setTitle(title);
			post.setContent(content);
			post.setSectionID(boardId);
			post.setPostMan(user.getId());
			post.setIsBoutique(0);
			post.setReplyCount(0);
			PostDAO postDao  = new PostDAO(post);                        // �õ�����Dao��ʵ��
			postDao.addPost();                                        // ������������
			request.getRequestDispatcher("list.jsp?page=1&boardid="+boardId).forward(request,response);    // ��ת
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
