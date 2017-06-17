package com.hlbbs.Control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hlbbs.DAO.CommentDAO;
import com.hlbbs.DAO.PostDAO;
import com.hlbbs.DAO.SectionDAO;
import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.Comment;
import com.hlbbs.Modal.Post;
import com.hlbbs.Modal.Section;
import com.hlbbs.Modal.User;

@WebServlet("/DetailControl")
public class DetailControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DetailControl() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
String action =request.getParameter("action");
if(action.equals("show"))
{
	showDetail(request, response);
	request.getRequestDispatcher("detail.jsp").forward(request, response);
	return;
	}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void showDetail(HttpServletRequest request,HttpServletResponse response)
	{
		Post topic =new Post();
		Comment comment=new Comment();
		Section board =new Section();
		User user =new User();

		int boardId= Integer.parseInt(request.getParameter("boardid").toString());  // ȡ�ð��id
		int  topicId= Integer.parseInt(request.getParameter("topicid").toString());  // ȡ������id
		int pageNo = Integer.parseInt(request.getParameter("page").toString());
		board.setID(boardId);
		topic.setId(topicId);
		comment.setPostsId(topicId);
		PostDAO postdao =new PostDAO(topic);
		CommentDAO commentDao  = new CommentDAO(comment);                                 // �õ�����Dao��ʵ��
		SectionDAO boardDao  = new SectionDAO(board);                                  // �õ����Dao��ʵ��
		                                 
		boardDao.findBoardByID();                     // ȡ�ð����Ϣ
		
		 postdao.searchByPostId();                    // ȡ��������Ϣ
		user.setId(topic.getPostMan());
		UserDAO  userDao   = new UserDAO(user);      // �õ��û�Dao��ʵ��
		userDao.findUserById();              // ȡ����������
		//System.out.println(topicuser.getName());
		int pagecount =commentDao.getPageCount(5);
	//	ArrayList<Comment>  listReply = commentDao.searchByPostsId();           // ȡ�ø�����Ļظ��б�
		ArrayList<Comment> listReply =commentDao.getPageComment(pageNo, 5);
		
		 int pagePrev=pageNo>1?pageNo-1:1;//��һҳ
         int pageNext=pageNo<pagecount?pageNo+1:pagecount;//��һҳ
		System.out.println("��ǰҳ:"+pageNo+" ��������:"+listReply.size()+"��ҳ��:"+pagecount);
		
		request.setAttribute("topicuser", user);
		request.setAttribute("topic",topic);
		request.setAttribute("board", board);
		request.setAttribute("listReply", listReply);
		request.setAttribute("prep", pagePrev);
		request.setAttribute("nextp", pageNext);
		request.setAttribute("page", pageNo);
	}

}
