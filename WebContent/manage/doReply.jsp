<%@ page language="java" pageEncoding="GBK" 
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
String   title     = request.getParameter("title");              // ȡ�����ӱ���
String   content   = request.getParameter("content");            // ȡ����������
ReplyDao replyDao  = new ReplyDaoImpl();                         // �õ�����Dao��ʵ��
User     user      = (User)session.getAttribute("user");         // ��session��ȡ�õ�¼�û�
int      boardId   = Integer.parseInt( request.getParameter("boardId") ); // ȡ�ð��id
int      topicId   = Integer.parseInt( request.getParameter("topicId") ); // ȡ������id

if( user!=null ) {                                                // �ж��û��Ƿ��Ѿ���¼
	Reply reply = new Reply();
	reply.setTitle(title);
	reply.setContent(content);
	reply.setTopicId(topicId);
	reply.setUid(user.getUId());
	// ����ʱ����޸�ʱ�佫��Dao������
	replyDao.addReply(reply);                                     // ������������
	response.sendRedirect("../detail.jsp?page=1&boardId="+boardId+"&topicId="+topicId); // ��ת
	return;
}
request.getRequestDispatcher("/error.jsp?msg=��δ��¼").forward(request,response);
%>
