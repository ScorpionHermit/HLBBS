<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
ReplyDao replyDao  = new ReplyDaoImpl();                                  // 得到回复Dao的实例
int      boardId   = Integer.parseInt( request.getParameter("boardId") );  // 取得版块id
int      replyId   = Integer.parseInt( request.getParameter("replyId") );  // 取得回复id
User     user      = (User)session.getAttribute("user");                    // 从session中取得登录用户
String   msg       = "";
if( user!=null && user.getUId()==replyDao.findReply(replyId).getUid()) {   // 判断用户权限
	replyDao.deleteReply( replyId );							            // 根据回复id删除回复
	response.sendRedirect("../list.jsp?page=1&boardId="+boardId);           // 跳转
	return;
} else {
	msg = "您无此权限";
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>
