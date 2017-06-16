package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.Level;
import com.hlbbs.Modal.Role;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class RegisterControl
 */
@WebServlet("/RegisterControl")
public class RegisterControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterControl() {
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		request.setCharacterEncoding("utf-8");
		String   uName     = request.getParameter("name");             // 取得请求中的登录名
		String   uPass     = request.getParameter("password");             // 取得请求中的密码
		String   head      = request.getParameter("head");              // 取得头像图片名
		String   gender    = request.getParameter("gender");			// 取得性别
		String email = request.getParameter("email");
		User     user      = new User();
		UserDAO  userDao   = new UserDAO(user);                         // 得到用户Dao的实例

		if( uName!=null && uPass!=null ) 
		{
			user.setName(uName);
			user.setEmailAddress(email);
			user.setRoleType(Role.NormalUser);
			user.setLevel(Level.Level_0);
			user.setPassWord(uPass);
			user.setHeadPortrait(head);
			user.setSex(gender);
			boolean isSuccess = userDao.addUser();
			if(isSuccess)
			{                                                  // 判断用户是否存在
				response.sendRedirect("index.jsp");
				return;
			}
		}
		request.setAttribute("status", false);
		request.getRequestDispatcher("reg.jsp").forward(request,response);
	}
}
