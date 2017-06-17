package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.PostDAO;
import com.hlbbs.Modal.Post;

/**
 * Servlet implementation class PostMannagerControl
 */
@WebServlet("/PostManagerControl")
public class PostManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PostManagerControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msg = null;
		int postID = Integer.parseInt(request.getParameter("postid"));
		Post post = new Post();
		post.setId(postID);
		PostDAO pDAO = new PostDAO(post);
		if(pDAO.deletePost())
			msg = "É¾³ý³É¹¦";
		else
			msg = "É¾³ýÊ§°Ü";
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/manager/postmanager.jsp").forward(request, response);
	}

}
