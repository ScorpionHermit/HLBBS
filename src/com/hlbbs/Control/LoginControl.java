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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
request.setCharacterEncoding("utf-8");
		
		User u = new User();
		String name = request.getParameter("name");
		String pwd = request.getParameter("pass");
		u.setName(name);
		
		String msg = null;
		User user = (User)request.getSession().getAttribute("user");        // ��session��ȡ�õ�¼�û�
		UserDAO ud = new UserDAO(u);
		
		String S1=(String)request.getSession().getAttribute("authCode");
		String S2=request.getParameter("authimg");
		
		if(user==null && S2.equals(S1))
		{
			
			if( ud.findUserByName() && ud.user.getPassWord().equals(pwd) ) 
			{
				request.getSession().setAttribute("user", ud.user);                     // ������ҵ��û���Ϊ�ղ���������ȷ�������û���Ϣ
				response.sendRedirect("index.jsp");                  // ת��������ҳ
				return;
			} else {
				msg = "�û������������";
			}
		} 
		else
			msg = "�ظ���¼";
		
		request.setAttribute("msg", msg);
		request.getRequestDispatcher("login.jsp").forward(request,response);
	}

}
