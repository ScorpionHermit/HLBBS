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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
	String action =request.getParameter("option").toString();
	int      boardId   = Integer.parseInt( request.getParameter("boardid") ); // ȡ�ð��id
	int      topicId   = Integer.parseInt( request.getParameter("topicid") ); // ȡ������id
	if(action.equals("newreply"))
	{
		if(newreply(request, response))
		{
			response.sendRedirect(request.getContextPath()+"/DetailControl?page=1&boardid="+boardId+"&topicid="+topicId+"&action=show"); // ��ת
			return;
		}
		else {
			request.getRequestDispatcher(request.getContextPath()+"/error.jsp?msg=��δ��¼").forward(request,response);
		}
	}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
private boolean newreply(HttpServletRequest request, HttpServletResponse response)
{
	
	String   title     = request.getParameter("title");              // ȡ�����۱���
	String   content   = request.getParameter("content");            // ȡ����������
	HttpSession session =request.getSession();
	int      boardId   = Integer.parseInt( request.getParameter("boardid") ); // ȡ�ð��id
	int      topicId   = Integer.parseInt( request.getParameter("topicid") ); // ȡ������id
	User     user      = (User)session.getAttribute("user");         // ��session��ȡ�õ�¼�û�
	if( user!=null ) {                                                // �ж��û��Ƿ��Ѿ���¼
		Comment comment =new Comment();
		comment.setTitle(title);
		comment.setContent(content);
		comment.setUserId(user.getId());
		comment.setPostsId(topicId);
		CommentDAO replyDao  = new CommentDAO(comment);                         // �õ�����Dao��ʵ��
	
		if(replyDao.addComment()>0)
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
}
