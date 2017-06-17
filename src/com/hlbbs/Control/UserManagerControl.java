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
 * Servlet implementation class UserManagerControl
 */
@WebServlet("/UserManagerControl")
public class UserManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManagerControl() {
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
		User u = new User();
		u.setId(userID);
		UserDAO uDAO = new UserDAO(u);
		
		if(uDAO.delUser())
			msg = "É¾³ý³É¹¦";
		else
			msg = "É¾³ýÊ§°Ü";
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("/manager/usermanager.jsp").forward(request, response);
	}

}
