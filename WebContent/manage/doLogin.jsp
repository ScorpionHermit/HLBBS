<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");

String   uName     = request.getParameter("uName");             // ȡ�������еĵ�¼��
String   uPass     = request.getParameter("uPass");             // ȡ�������е�����
UserDao  userDao   = new UserDaoImpl();                         // �õ��û�Dao��ʵ��
User     user      = (User)session.getAttribute("user");        // ��session��ȡ�õ�¼�û�
String   msg       = "";
if( user==null ) {
	user = userDao.findUser(uName);                             // ��������ĵ�¼������������û�
	if( user!=null && user.getUPass().equals(uPass) ) {
		session.setAttribute("user", user);                     // ������ҵ��û���Ϊ�ղ���������ȷ�������û���Ϣ
		response.sendRedirect("../index.jsp");                  // ת��������ҳ
		return;
	} else {
		msg = "�û������������";
	}
} else {
	msg = "�ظ���¼";
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>
