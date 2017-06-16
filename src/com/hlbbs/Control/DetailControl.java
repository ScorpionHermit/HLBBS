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
		request.setCharacterEncoding("GBK");
String action =request.getParameter("action");
if(action.equals("show"))
{
	showDetail(request, response);
	request.getRequestDispatcher("detail.jsp").forward(request, response);
	
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

		int boardId= Integer.parseInt(request.getParameter("boardid").toString());  // 取得版块id
		int  topicId= Integer.parseInt(request.getParameter("topicid").toString());  // 取得帖子id
		int p = Integer.parseInt(request.getParameter("page").toString());
		board.setID(boardId);
		topic.setId(topicId);
		comment.setPostsId(topicId);
		PostDAO postdao =new PostDAO(topic);
		CommentDAO commentDao  = new CommentDAO(comment);                                 // 得到评论Dao的实例
		SectionDAO boardDao  = new SectionDAO(board);                                  // 得到版块Dao的实例
		                                 
		boardDao.findBoardByID();                     // 取得版块信息
		
		 postdao.searchByPostId();                    // 取得主题信息
		user.setId(topic.getPostMan());
		UserDAO  userDao   = new UserDAO(user);      // 得到用户Dao的实例
		userDao.findUserById();              // 取得主题作者
		//System.out.println(topicuser.getName());
		ArrayList<Comment>  listReply = commentDao.searchByPostsId();           // 取得该主题的回复列表
		int     prep       = p;                                                   // 上一页
		int     nextp      = p;                                                   // 下一页
		if(listReply.size()==10) {
			nextp = p+1;
		}
		if( p>1 ){
			prep = p-1;
		}
		
		request.setAttribute("topicuser", user);
		request.setAttribute("topic",topic);
		request.setAttribute("board", board);
		request.setAttribute("listReply", listReply);
		request.setAttribute("prep", prep);
		request.setAttribute("nextp", nextp);
		request.setAttribute("page", p);
	}

}
