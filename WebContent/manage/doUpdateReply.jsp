<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");
ReplyDao replyDao  = new ReplyDaoImpl();                        // �õ�����Dao��ʵ��
int      boardId   = Integer.parseInt( request.getParameter("boardId") ); // ȡ�ð��id
int      replyId   = Integer.parseInt( request.getParameter("replyId") ); // ȡ�ûظ�id
String   title     = request.getParameter("title");             // ȡ�����ӱ���
String   content   = request.getParameter("content");           // ȡ����������
User     user      = (User)session.getAttribute("user");        // ��session��ȡ�õ�¼�û�
String   msg       = "";

if( user!=null && user.getUId()==replyDao.findReply(replyId).getUid()) {  // �ж��û�Ȩ��                                               // �ж��û��Ƿ��Ѿ���¼
	Reply reply = new Reply();
	reply.setReplyId(replyId);
	reply.setTitle(title);
	reply.setContent(content);
	// �޸�ʱ����Dao������
	replyDao.updateReply(reply);                                  // �޸�����
	response.sendRedirect("../list.jsp?page=1&boardId="+boardId);    // ��ת
	return;
} else {
	msg = "���޴�Ȩ��";
}
String forward="/error.jsp?msg="+msg;
request.getRequestDispatcher(forward).forward(request,response);
%>