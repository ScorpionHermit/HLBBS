<%@ page language="java" pageEncoding="GBK" 
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
String   title     = request.getParameter("title");             // ȡ�����ӱ���
String   content   = request.getParameter("content");           // ȡ����������
TopicDao topicDao  = new TopicDaoImpl();                        // �õ�����Dao��ʵ��
User     user      = (User)session.getAttribute("user");        // ��session��ȡ�õ�¼�û�
int     boardId    = Integer.parseInt( request.getParameter("boardId") ); // ȡ�ð��id

if( user!=null ) {                                                 // �ж��û��Ƿ��Ѿ���¼
	Topic topic = new Topic();
	topic.setTitle(title);
	topic.setContent(content);
	topic.setBoardId(boardId);
	topic.setUid(user.getUId());
	// ����ʱ����޸�ʱ�佫��Dao������
	topicDao.addTopic(topic);                                        // ������������
	response.sendRedirect("../list.jsp?page=1&boardId="+boardId);    // ��ת
	return;
}
request.getRequestDispatcher("/error.jsp?msg=��δ��¼").forward(request,response);
%>