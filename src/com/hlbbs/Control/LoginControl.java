package com.hlbbs.Control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class DoLogin
 */
@WebServlet("/DoLogin")
public class LoginControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		User u = new User();
		String name = request.getParameter("uName");
		String pwd = request.getParameter("uPass");
		u.setName(name);
		
		String msg = "";
		User     user      = (User)request.getSession().getAttribute("user");        // 从session中取得登录用户
		UserDAO ud = new UserDAO(u);
		
		if(user==null){
			ud.findUserByName();
			if( ud.user!=null && ud.user.getPassWord().equals(pwd) ) {
				request.getSession().setAttribute("user", ud.user);                     // 如果查找的用户不为空并且密码正确，保存用户信息
				response.sendRedirect("index.jsp");                  // 转发请求到首页
				return;
			} else {
				msg = "用户名或密码错误";
			}
		} else {
			msg = "重复登录";
		}
		
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
