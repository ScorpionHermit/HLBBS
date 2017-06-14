package com.javatrain.web.servlet;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class HelloServlet extends HttpServlet // ��һ���� ��չ HttpServlet �����ࡣ
{
	/**
	 *                
	 */
	private static final long serialVersionUID = 1L;

	// �ڶ���������doGet()����
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		// ����������ȡHTTP �����еĲ�����Ϣ
		String clientName = request.getParameter("clientName");
		if (clientName != null)
			clientName = new String(clientName.getBytes("ISO-8859-1"), "GB2312");
		else
			clientName = "�ҵ�����";

		// ���Ĳ������� HTTP ��Ӧ�����

		PrintWriter out;
		String title = "HelloServlet";
		String heading1 = "This is output from HelloServlet by doGet:";
		// set content type.
		response.setContentType("text/html;charset=GB2312");
		// write html page.
		out = response.getWriter();
		out.print("<HTML><HEAD><TITLE>" + title + "</TITLE>");
		out.print("</HEAD><BODY>");
		out.print(heading1);
		out.println("<h1><P> " + clientName + " : ����</h1>");
		out.print("</BODY></HTML>");
		// close out.
		out.close();

	}
}
