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
 * Servlet implementation class UpdateInfoControl
 */
@WebServlet("/UpdateInfo")
public class UpdateInfoControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateInfoControl() {
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
		
		String username = request.getParameter("username");
		String sex = request.getParameter("sex");
		String email = request.getParameter("email");
		String PersonalizedSignature = request.getParameter("PersonalizedSignature");
		
		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("user");
		
		User user = new User();
		UserDAO uDAO = new UserDAO(user);
		
		user.setId(u.getId());
		user.setName(username);
		user.setSex(sex);
		user.setEmailAddress(email);
		user.setPersonalizedSignature(PersonalizedSignature);
		
		boolean isSuccess = false;
		isSuccess = uDAO.updateUserInf();
		
		if (isSuccess) {
			uDAO.findUserById();
			session.setAttribute("user", user);
			out.print("<script>alert('修改成功');window.location.href='user.jsp';</script>");
		} else {
			out.print("<script>alert('修改失败');window.location.href='user.jsp';</script>");
		}
	}

}
