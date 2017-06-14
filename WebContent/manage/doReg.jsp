<%@ page language="java" pageEncoding="GBK" 
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>

<%
request.setCharacterEncoding("GBK");
String   uName     = request.getParameter("uName");             // 取得请求中的登录名
String   uPass     = request.getParameter("uPass");             // 取得请求中的密码
String   head      = request.getParameter("head");              // 取得头像图片名
int      gender    = Integer.parseInt(request.getParameter("gender"));// 取得性别
User     user      = (User)session.getAttribute("user");        // 从session中取得登录用户
UserDao  userDao   = new UserDaoImpl();                         // 得到用户Dao的实例
String   msg       = "";

if( uName!=null && uPass!=null ) {
	user = new User();
	user.setUName(uName);
	user.setUPass(uPass);
	user.setHead(head);
	user.setGender(gender);
	int num = userDao.addUser(user);
	if(num==1){                                                  // 判断用户是否存在
		response.sendRedirect("../index.jsp");
		return;
	}else if(num==0){
		msg = "该用户已存在";
	}
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>