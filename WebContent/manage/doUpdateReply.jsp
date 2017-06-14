<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
ReplyDao replyDao  = new ReplyDaoImpl();                        // 得到主题Dao的实例
int      boardId   = Integer.parseInt( request.getParameter("boardId") ); // 取得版块id
int      replyId   = Integer.parseInt( request.getParameter("replyId") ); // 取得回复id
String   title     = request.getParameter("title");             // 取得帖子标题
String   content   = request.getParameter("content");           // 取得帖子内容
User     user      = (User)session.getAttribute("user");        // 从session中取得登录用户
String   msg       = "";

if( user!=null && user.getUId()==replyDao.findReply(replyId).getUid()) {  // 判断用户权限                                               // 判断用户是否已经登录
	Reply reply = new Reply();
	reply.setReplyId(replyId);
	reply.setTitle(title);
	reply.setContent(content);
	// 修改时间由Dao类生成
	replyDao.updateReply(reply);                                  // 修改主题
	response.sendRedirect("../list.jsp?page=1&boardId="+boardId);    // 跳转
	return;
} else {
	msg = "您无此权限";
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>