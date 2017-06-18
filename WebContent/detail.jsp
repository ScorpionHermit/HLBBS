<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
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
<title>荟灵BBS--看贴</title>
<Link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript">
function del(){ 
	if(!confirm("确认要删除？")){ 
	//window.event.returnValue = false; 
	return false;
	} 
	} 
</script>

</HEAD>


<BODY>
<DIV>
	<IMG style="width:250px" src="image/logo.png">
</DIV>

<!--      用户信息、登录、注册        -->
<%
if(session.getAttribute("user") == null){
%>
	<DIV class="h">
		您尚未　<a href="login.jsp">登录</a>
		&nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
	</DIV>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
	<DIV class="h">
		您好：　<%=loginUser.getName() %>
		&nbsp;| &nbsp; <a href="LoginoutControl">登出</A> |
	</DIV>
<%
}
%>
<!--      主体        -->
<DIV><br/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
	<B><a href="list.jsp?page=1&boardid=<%=board.getID() %>"><%=board.getNvcSectionName() %></a></B>
</DIV>
	<br/>
	<!--      回复、新帖        -->
	<DIV>
		<A href="post.jsp?post=newreply&topicid=<%=topic.getId() %>&boardid=<%=board.getID()%>"><IMG src="image/reply.gif" border="0"></A> 
		<A href="post.jsp?post=newtopic&boardid=<%=board.getID() %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
	
	<!--      本页主题的标题        -->
	<DIV>
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR>
				<TH class="h">本页主题: <%=topic.getTitle() %></TH>
			</TR>
			<TR class="tr2">
				<TD>&nbsp;</TD>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      主题        -->
	<%
	if(p==0){ 
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=user.getName() %></B><BR/>
					<img src="image/head/<%=user.getHeadPortrait()%>"/><BR/>
					注册时间:<%=user.getRegistertime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=topic.getTitle() %></H4>
					<DIV><pre><%=topic.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						发表：[<%=topic.getPostTime().substring(0,16) %>] &nbsp;
						<%if(topic.getFinalReplyTime()!=null)
						{
							%>
						最后回复时间:[<%=topic.getFinalReplyTime().substring(0,16) %>]
						<%} %>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      回复        -->
	<%
	}
	if(listReply!=null)
	{
	for( int i=0; i<listReply.size(); i++ ) {
		Comment reply    = (Comment)listReply.get(i);                   // 循环取得回复信息
        User replyUser =new User();
		replyUser.setId(reply.getUserId());
		UserDAO uDao =new UserDAO(replyUser);
		 uDao.findUserById(); // 取得回复的作者
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=replyUser.getName() %></B><BR/><BR/>
				<img src="image/head/<%=replyUser.getHeadPortrait()%>">
				<BR/>
					注册时间:<%=user.getRegistertime().substring(0,10)%><BR/>
				</TH>
				<TH>
					<H4><%=reply.getTitle() %></H4>
					<DIV><pre><%=reply.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						发表：[<%=reply.getComTime().substring(0,16) %>] &nbsp;
						
						
						<A href="ReplyControl?boardid=<%=board.getID()%>&topicid=<%=topic.getId()%>&id=<%=reply.getId()%>&option=deletereply" onclick="return del();">[删除]</A>
						<A href="ReplyControl?boardid=<%=board.getID()%>&topicid=<%=topic.getId()%>&id=<%=reply.getId()%>&option=updatereply">[修改]</A>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	<%} }%>
	<DIV>
		<a href="DetailControl?page=<%=prep%>&boardid=<%=board.getID()%>&topicid=<%=topic.getId()%>&action=show">上一页</a>|
		<a href="DetailControl?page=<%=nextp%>&boardid=<%=board.getID() %>&topicid=<%=topic.getId()%>&action=show">下一页</a>
	</DIV>
</DIV>

<!--      声明        -->
<BR>
<CENTER class="gray">版权所有</CENTER>
</BODY>
</html>