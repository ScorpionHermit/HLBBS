package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.SectionDAO;
import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.Section;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class SectionManagerControl
 */
@WebServlet("/SectionManagerControl")
public class SectionManagerControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SectionManagerControl() {
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
		request.setCharacterEncoding("utf-8");
		String operation = request.getParameter("operation");
		StringBuffer msg = new StringBuffer("");
		boolean isSuccess = false;
		if(operation.equals("add"))
		{
			msg.append("板块添加");
			if(AddSection(request))
				isSuccess = true;
		}
		else
		{
			msg.append("板块修改");
			if(ModifySection(request))
				isSuccess = true;
		}
		
		if(isSuccess)
			msg.append("成功");
		else
			msg.append("失败");
		
		request.setAttribute("msg", msg.toString());
		
		request.getRequestDispatcher("/manager/sectionmanager.jsp").forward(request, response);
	}
	
	private boolean AddSection(HttpServletRequest request)
	{
		String secName = request.getParameter("sectionname");
		int secManager = Integer.parseInt(request.getParameter("sectionmanager"));
		Section section = new Section();
		section.setNvcSectionName(secName);
		section.setIntModerator(secManager);
		
		SectionDAO sDAO = new SectionDAO(section);
		return sDAO.InsertSection();
	}
	
	private boolean ModifySection(HttpServletRequest request)
	{
		boolean isSuccess = false;
		String secName = request.getParameter("sectionname");
		int secManager = Integer.parseInt(request.getParameter("sectionmanager"));
		int secID = Integer.parseInt(request.getParameter("sectionid"));
		Section section = new Section();
		
		
		section.setID(secID);
		
		SectionDAO sDAO = new SectionDAO(section);
		sDAO.findBoardByID();
		int oldManager = section.getIntModerator();		//找到原来版主
		
		section.setNvcSectionName(secName);
		section.setIntModerator(secManager);
		isSuccess = sDAO.ModifySection();				//更新板块信息
		
		if(!isSuccess)
			return isSuccess;
		
		if(oldManager != secManager)
		{
			User user = new User();
			user.setId(oldManager);
			UserDAO uDAO = new UserDAO(user);
			uDAO.findUserById();
			user.setRoleType(user.getRoleType() & (~4));
			uDAO.updateUserRoleType();
			
			user.setId(secManager);
			uDAO.findUserById();
			user.setRoleType(user.getRoleType() | 4);
			uDAO.updateUserRoleType();
		}
		return isSuccess;
	}
	

}
