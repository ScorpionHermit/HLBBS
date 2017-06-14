<%@ page language="java" pageEncoding="GBK" 
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
String   title     = request.getParameter("title");              // 取得帖子标题
String   content   = request.getParameter("content");            // 取得帖子内容
ReplyDao replyDao  = new ReplyDaoImpl();                         // 得到主题Dao的实例
User     user      = (User)session.getAttribute("user");         // 从session中取得登录用户
int      boardId   = Integer.parseInt( request.getParameter("boardId") ); // 取得版块id
int      topicId   = Integer.parseInt( request.getParameter("topicId") ); // 取得主题id

if( user!=null ) {                                                // 判断用户是否已经登录
	Reply reply = new Reply();
	reply.setTitle(title);
	reply.setContent(content);
	reply.setTopicId(topicId);
	reply.setUid(user.getUId());
	// 发表时间和修改时间将由Dao类生成
	replyDao.addReply(reply);                                     // 保存主题帖子
	response.sendRedirect("../detail.jsp?page=1&boardId="+boardId+"&topicId="+topicId); // 跳转
	return;
}
request.getRequestDispatcher("/error.jsp?msg=您未登录").forward(request,response);
%>
