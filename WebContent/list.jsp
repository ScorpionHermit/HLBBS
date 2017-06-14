<%@ page language="java" pageEncoding="GBK" 
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");                                      // �����ַ���

TopicDao topicDao  = new TopicDaoImpl();                                  // �õ�����Dao��ʵ��
ReplyDao replyDao  = new ReplyDaoImpl();                                  // �õ��ظ�Dao��ʵ��
BoardDao boardDao  = new BoardDaoImpl();                                  // �õ����Dao��ʵ��
UserDao  userDao   = new UserDaoImpl();                                   // �õ��û�Dao��ʵ��

int      boardId   = Integer.parseInt( request.getParameter("boardId") );  // ȡ�ð��id
Board    board     = boardDao.findBoard( boardId );                        // ȡ�ð����Ϣ
int      p         = Integer.parseInt( request.getParameter("page") );     // ȡ��ҳ��
List     listTopic = topicDao.findListTopic( p, boardId );                 // ȡ�øð�������б�

int     prep       = p;                                                    // ��һҳ
int     nextp      = p;                                                    // ��һҳ
if(listTopic.size()==20) {
	nextp = p+1;
}
if( p>1 ){
	prep = p-1;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>����ѧԱ��̳--�����б�</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      �û���Ϣ����¼��ע��        -->
<%
if(session.getAttribute("user") == null){
%>
<DIV class="h">
	����δ��<a href="login.jsp">��¼</a>
	&nbsp;| &nbsp; <A href="reg.jsp">ע��</A> |
</DIV>
<%
} else {
User loginUser = (User)session.getAttribute("user");
%>
<DIV class="h">
	���ã���<%=loginUser.getUName() %>
	&nbsp;| &nbsp; <A href="manage/doLogout.jsp">�ǳ�</A> |
</DIV>
<%
}
%>

<!--      ����        -->
<DIV>
<!--      ����        -->
	<br/>
	<DIV>
		&gt;&gt;<B><a href="index.jsp">��̳��ҳ</a></B>&gt;&gt;
		<B><a href="list.jsp?page=1&boardId=<%=boardId %>"><%=board.getBoardName() %></a></B>
	</DIV>
	<br/>
<!--      ����        -->
	<DIV>
		<A href="post.jsp?post=newtopic&boardId=<%=boardId %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
<!--         �� ҳ         -->
	<DIV>
		<a href="list.jsp?page=<%=prep%>&boardId=<%=boardId %>">��һҳ</a>|
		<a href="list.jsp?page=<%=nextp%>&boardId=<%=boardId %>">��һҳ</a>
	</DIV>

	<DIV class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">		
			<TR>
				<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
			</TR>
<!--       �� ͷ           -->
			<TR class="tr2">
				<TD>&nbsp;</TD>
				<TD style="WIDTH: 80%" align="center">����</TD>
				<TD style="WIDTH: 10%" align="center">����</TD>
				<TD style="WIDTH: 10%" align="center">�ظ�</TD>
			</TR>
<!--         �� �� �� ��        -->
			<%
			for( int i=0; i<listTopic.size(); i++ ) {
			Topic    topic   = (Topic)listTopic.get(i);             // ѭ��ȡ���������
			User     user    = userDao.findUser( topic.getUid() );  // ȡ�ø�����ķ����û�
			%>
			<TR class="tr3">
				<TD><IMG src="image/topic.gif" border=0></TD>
				<TD style="FONT-SIZE: 15px">
					<A href="detail.jsp?page=1&boardId=<%=boardId %>&topicId=<%=topic.getTopicId() %>"><%=topic.getTitle() %></A>
				</TD>
				<TD align="center"><%=user.getUName() %></TD>
				<TD align="center"><%=replyDao.findCountReply( topic.getTopicId() )%></TD>
			</TR>
			<%
			}
			%>
		</TABLE>
	</DIV>
<!--            �� ҳ          -->
	<DIV>
		<a href="list.jsp?page=<%=prep%>&boardId=<%=boardId %>">��һҳ</a>|
		<a href="list.jsp?page=<%=nextp%>&boardId=<%=boardId %>">��һҳ</a>
	</DIV>
</DIV>
<!--             �� ��          -->
<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd ��Ȩ����</CENTER>

</BODY>
</HTML>
