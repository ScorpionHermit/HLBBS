<%@ page language="java" pageEncoding="GBK"
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");

TopicDao topicDao  = new TopicDaoImpl();                                  // �õ�����Dao��ʵ��
ReplyDao replyDao  = new ReplyDaoImpl();                                  // �õ��ظ�Dao��ʵ��
BoardDao boardDao  = new BoardDaoImpl();                                  // �õ����Dao��ʵ��
UserDao  userDao   = new UserDaoImpl();                                   // �õ��û�Dao��ʵ��
int      boardId   = Integer.parseInt( request.getParameter("boardId") );  // ȡ�ð��id
int      topicId   = Integer.parseInt( request.getParameter("topicId") );  // ȡ������id
int      p         = Integer.parseInt(request.getParameter("page"));
Board    board     = boardDao.findBoard( boardId );                         // ȡ�ð����Ϣ
Topic    topic     = topicDao.findTopic( topicId );                        // ȡ��������Ϣ
User     topicUser = userDao.findUser( topic.getUid() );                   // ȡ����������
List     listReply = replyDao.findListReply( p,topicId );                  // ȡ�ø�����Ļظ��б�

int     prep       = p;                                                   // ��һҳ
int     nextp      = p;                                                   // ��һҳ
if(listReply.size()==10) {
	nextp = p+1;
}
if( p>1 ){
	prep = p-1;
}
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>����ѧԱ��̳--����</TITLE>
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
<DIV><br/>
<!--      ����        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">��̳��ҳ</a></B>&gt;&gt;
	<B><a href="list.jsp?page=1&boardId=<%=boardId %>"><%=board.getBoardName() %></a></B>
</DIV>
	<br/>
	<!--      �ظ�������        -->
	<DIV>
		<A href="post.jsp?post=newreply&topicId=<%=topicId %>&boardId=<%=boardId %>"><IMG src="image/reply.gif" border="0"></A> 
		<A href="post.jsp?post=newtopic&boardId=<%=boardId %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
	<!--         �� ҳ         -->
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=boardId %>&topicId=<%=topicId %>">��һҳ</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=boardId %>&topicId=<%=topicId %>">��һҳ</a>
	</DIV>
	<!--      ��ҳ����ı���        -->
	<DIV>
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR>
				<TH class="h">��ҳ����: <%=topic.getTitle() %></TH>
			</TR>
			<TR class="tr2">
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      ����        -->
	<%
	if(p==1){ 
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=topicUser.getUName() %></B><BR/>
					<img src="image/head/<%=topicUser.getHead()%>"/><BR/>
					ע��:<%=topicUser.getRegTime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=topic.getTitle() %></H4>
					<DIV><pre><%=topic.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						����[<%=topic.getPublishTime().substring(0,16) %>] &nbsp;
						����޸�:[<%=topic.getModifyTime().substring(0,16) %>]
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      �ظ�        -->
	<%
	}
	for( int i=0; i<listReply.size(); i++ ) {
		Reply reply     = (Reply)listReply.get(i);                   // ѭ��ȡ�ûظ���Ϣ
		User  replyUser = (User)userDao.findUser( reply.getUid() );  // ȡ�ûظ�������
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=replyUser.getUName() %></B><BR/><BR/>
					<img src="image/head/<%=replyUser.getHead()%>"/><BR/>
					ע��:<%=topicUser.getRegTime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=reply.getTitle() %></H4>
					<DIV><pre><%=reply.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						����[<%=reply.getPublishTime().substring(0,16) %>] &nbsp;
						����޸�:[<%=topic.getModifyTime().substring(0,16) %>]
						<A href="manage/doDeleteReply.jsp?boardId=<%=boardId%>&replyId=<%=reply.getReplyId()%>">[ɾ��]</A>
						<A href="update.jsp?tipType=reply&boardId=<%=boardId%>&replyId=<%=reply.getReplyId()%>">[�޸�]</A>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	<%} %>
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=boardId %>&topicId=<%=topicId %>">��һҳ</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=boardId %>&topicId=<%=topicId %>">��һҳ</a>
	</DIV>
</DIV>

<!--      ����        -->
<BR>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd ��Ȩ����</CENTER>
</BODY>
</HTML>
