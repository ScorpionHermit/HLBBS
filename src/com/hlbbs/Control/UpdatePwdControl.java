package com.hlbbs.Control;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class UpdatePwdControl
 */
@WebServlet("/UpdatePwd")
public class UpdatePwdControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePwdControl() {
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
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		
		String oldpwd = request.getParameter("oldpwd");
		String newpwd = request.getParameter("newpwd");
		
		HttpSession session = request.getSession();
		User u = new User();
		User user = (User) session.getAttribute("user");
		
		u.setName(user.getName());
		UserDAO uDAO = new UserDAO(u);
		
		if (user != null) {
			uDAO.findUserByName();
			if ( uDAO.user != null && !uDAO.user.getPassWord().equals(oldpwd) ) {
				out.print("<script>alert('旧密码错误');window.location.href='user.jsp';</script>");
			} else {
				u.setPassWord(newpwd);
				boolean isSuccess = false;
				isSuccess = uDAO.updateUserPwd();
				
				if (isSuccess) {
					session.removeAttribute("user");
					out.print("<script>alert('密码修改成功');window.location.href='login.jsp';</script>");
				}
			}
		}
	}

}
