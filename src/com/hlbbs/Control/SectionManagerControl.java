package com.hlbbs.Control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.SectionDAO;
import com.hlbbs.Modal.Section;

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
			msg.append("°å¿éÌí¼Ó");
			if(AddSection(request))
				isSuccess = true;
		}
		else
		{
			msg.append("°å¿éÐÞ¸Ä");
			if(ModifySection(request))
				isSuccess = true;
		}
		
		if(isSuccess)
			msg.append("³É¹¦");
		else
			msg.append("Ê§°Ü");
		
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
		String secName = request.getParameter("sectionname");
		int secManager = Integer.parseInt(request.getParameter("sectionmanager"));
		int secID = Integer.parseInt(request.getParameter("sectionid"));
		Section section = new Section();
		section.setNvcSectionName(secName);
		section.setIntModerator(secManager);
		section.setID(secID);
		
		SectionDAO sDAO = new SectionDAO(section);
		return sDAO.ModifySection();
	}
	

}
