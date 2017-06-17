package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class RoleManagerControl
 */
@WebServlet("/RoleManagerControl")
public class RoleManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoleManagerControl() {
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
		int userID = Integer.parseInt(request.getParameter("userid"));
		int roleType = Integer.parseInt(request.getParameter("roletype"));
		
		User user  = new User();
		UserDAO uDAO = new UserDAO(user);
		user.setId(userID);
		uDAO.findUserById();
		int role = user.getRoleType() & 4;
		role |= roleType;
		user.setRoleType(role);
		
		if(uDAO.updateUserRoleType())
			msg = "权限修改成功";
		else
			msg = "权限修改失败";
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/manager/rolemanager.jsp").forward(request, response);
	}

}
