<%@ page language="java" pageEncoding="GBK" 
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>

<%
request.setCharacterEncoding("GBK");
String   uName     = request.getParameter("uName");             // ȡ�������еĵ�¼��
String   uPass     = request.getParameter("uPass");             // ȡ�������е�����
String   head      = request.getParameter("head");              // ȡ��ͷ��ͼƬ��
int      gender    = Integer.parseInt(request.getParameter("gender"));// ȡ���Ա�
User     user      = (User)session.getAttribute("user");        // ��session��ȡ�õ�¼�û�
UserDao  userDao   = new UserDaoImpl();                         // �õ��û�Dao��ʵ��
String   msg       = "";

if( uName!=null && uPass!=null ) {
	user = new User();
	user.setUName(uName);
	user.setUPass(uPass);
	user.setHead(head);
	user.setGender(gender);
	int num = userDao.addUser(user);
	if(num==1){                                                  // �ж��û��Ƿ����
		response.sendRedirect("../index.jsp");
		return;
	}else if(num==0){
		msg = "���û��Ѵ���";
	}
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>