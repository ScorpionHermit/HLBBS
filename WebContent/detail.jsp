<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
    <%@page import="java.util.*" %>
    <%@page import="com.hlbbs.Modal.*"%>
    <%@page import="com.hlbbs.DAO.*" %>
    <%
Section board =(Section)request.getAttribute("board");
    Post topic =(Post)request.getAttribute("topic");
    User user =(User)request.getAttribute("topicuser");
    ArrayList<Comment> listReply =(ArrayList)request.getAttribute("listReply");
    int prep=Integer.parseInt(request.getAttribute("prep").toString());
    int nextp=Integer.parseInt(request.getAttribute("nextp").toString());
    int p=Integer.parseInt(request.getAttribute("page").toString());
    
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>����BBS--����</title>
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
		���ã���<%=loginUser.getName() %>
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
	<B><a href="list.jsp?page=1&boardId=<%=board.getID() %>"><%=board.getNvcSectionName() %></a></B>
</DIV>
	<br/>
	<!--      �ظ�������        -->
	<DIV>
		<A href="post.jsp?post=newreply&topicId=<%=topic.getId() %>&boardId=<%=board.getID()%>"><IMG src="image/reply.gif" border="0"></A> 
		<A href="post.jsp?post=newtopic&boardId=<%=board.getID() %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
	<!--         �� ҳ         -->
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=board.getID() %>&topicId=<%=topic.getId()%>">��һҳ</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=board.getID() %>&topicId=<%=topic.getId() %>">��һҳ</a>
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
					<B><%=user.getName() %></B><BR/>
					<img src="image/head/<%=user.getHeadPortrait()%>"/><BR/>
					ע��:<%=user.getRegistertime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=topic.getTitle() %></H4>
					<DIV><pre><%=topic.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						����[<%=topic.getPostTime().substring(0,16) %>] &nbsp;
						<%if(topic.getFinalReplyTime()!=null)
						{
							%>
						����޸�:[<%=topic.getFinalReplyTime().substring(0,16) %>]
						<%} %>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      �ظ�        -->
	<%
	}
	if(listReply!=null)
	{
	for( int i=0; i<listReply.size(); i++ ) {
		Comment reply    = (Comment)listReply.get(i);                   // ѭ��ȡ�ûظ���Ϣ
        User replyUser =new User();
		replyUser.setId(reply.getUserId());
		UserDAO uDao =new UserDAO(replyUser);
		 uDao.findUserById(); // ȡ�ûظ�������
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=replyUser.getName() %></B><BR/><BR/>
				<img src="image/head/<%=replyUser.getHeadPortrait()%>">
				<BR/>
					ע��ʱ��:<%=user.getRegistertime().substring(0,10)%><BR/>
				</TH>
				<TH>
					<H4><%=reply.getTitle() %></H4>
					<DIV><pre><%=reply.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						����[<%=reply.getComTime().substring(0,16) %>] &nbsp;
						����޸�:[<%=topic.getFinalReplyTime().substring(0,16) %>]
						
						<A href="manage/doDeleteReply.jsp?boardId=<%=board.getID()%>&replyId=<%=reply.getId()%>">[ɾ��]</A>
						<A href="update.jsp?tipType=reply&boardId=<%=board.getID()%>&replyId=<%=reply.getId()%>">[�޸�]</A>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	<%} }%>
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=board.getID() %>&topicId=<%=topic.getId() %>">��һҳ</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=board.getID() %>&topicId=<%=topic.getId() %>">��һҳ</a>
	</DIV>
</DIV>

<!--      ����        -->
<BR>
<CENTER class="gray">��Ȩ����</CENTER>
</BODY>
</html>