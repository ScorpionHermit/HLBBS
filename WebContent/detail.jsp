<%@ page language="java" pageEncoding="GBK"
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");

TopicDao topicDao  = new TopicDaoImpl();                                  // 得到主题Dao的实例
ReplyDao replyDao  = new ReplyDaoImpl();                                  // 得到回复Dao的实例
BoardDao boardDao  = new BoardDaoImpl();                                  // 得到版块Dao的实例
UserDao  userDao   = new UserDaoImpl();                                   // 得到用户Dao的实例
int      boardId   = Integer.parseInt( request.getParameter("boardId") );  // 取得版块id
int      topicId   = Integer.parseInt( request.getParameter("topicId") );  // 取得主题id
int      p         = Integer.parseInt(request.getParameter("page"));
Board    board     = boardDao.findBoard( boardId );                         // 取得版块信息
Topic    topic     = topicDao.findTopic( topicId );                        // 取得主题信息
User     topicUser = userDao.findUser( topic.getUid() );                   // 取得主题作者
List     listReply = replyDao.findListReply( p,topicId );                  // 取得该主题的回复列表

int     prep       = p;                                                   // 上一页
int     nextp      = p;                                                   // 下一页
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
<TITLE>青鸟学员论坛--看贴</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
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
		您好：　<%=loginUser.getUName() %>
		&nbsp;| &nbsp; <A href="manage/doLogout.jsp">登出</A> |
	</DIV>
<%
}
%>
<!--      主体        -->
<DIV><br/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
	<B><a href="list.jsp?page=1&boardId=<%=boardId %>"><%=board.getBoardName() %></a></B>
</DIV>
	<br/>
	<!--      回复、新帖        -->
	<DIV>
		<A href="post.jsp?post=newreply&topicId=<%=topicId %>&boardId=<%=boardId %>"><IMG src="image/reply.gif" border="0"></A> 
		<A href="post.jsp?post=newtopic&boardId=<%=boardId %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
	<!--         翻 页         -->
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=boardId %>&topicId=<%=topicId %>">上一页</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=boardId %>&topicId=<%=topicId %>">下一页</a>
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
	if(p==1){ 
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=topicUser.getUName() %></B><BR/>
					<img src="image/head/<%=topicUser.getHead()%>"/><BR/>
					注册:<%=topicUser.getRegTime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=topic.getTitle() %></H4>
					<DIV><pre><%=topic.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						发表：[<%=topic.getPublishTime().substring(0,16) %>] &nbsp;
						最后修改:[<%=topic.getModifyTime().substring(0,16) %>]
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	
	<!--      回复        -->
	<%
	}
	for( int i=0; i<listReply.size(); i++ ) {
		Reply reply     = (Reply)listReply.get(i);                   // 循环取得回复信息
		User  replyUser = (User)userDao.findUser( reply.getUid() );  // 取得回复的作者
	%>
	<DIV class="t">
		<TABLE style="BORDER-TOP-WIDTH: 0px; TABLE-LAYOUT: fixed" cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr1">
				<TH style="WIDTH: 20%">
					<B><%=replyUser.getUName() %></B><BR/><BR/>
					<img src="image/head/<%=replyUser.getHead()%>"/><BR/>
					注册:<%=topicUser.getRegTime().substring(0,10) %><BR/>
				</TH>
				<TH>
					<H4><%=reply.getTitle() %></H4>
					<DIV><pre><%=reply.getContent() %></pre></DIV>
					<DIV class="tipad gray">
						发表：[<%=reply.getPublishTime().substring(0,16) %>] &nbsp;
						最后修改:[<%=topic.getModifyTime().substring(0,16) %>]
						<A href="manage/doDeleteReply.jsp?boardId=<%=boardId%>&replyId=<%=reply.getReplyId()%>">[删除]</A>
						<A href="update.jsp?tipType=reply&boardId=<%=boardId%>&replyId=<%=reply.getReplyId()%>">[修改]</A>
					</DIV>
				</TH>
			</TR>
		</TABLE>
	</DIV>
	<%} %>
	<DIV>
		<a href="detail.jsp?page=<%=prep%>&boardId=<%=boardId %>&topicId=<%=topicId %>">上一页</a>|
		<a href="detail.jsp?page=<%=nextp%>&boardId=<%=boardId %>&topicId=<%=topicId %>">下一页</a>
	</DIV>
</DIV>

<!--      声明        -->
<BR>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd 版权所有</CENTER>
</BODY>
</HTML>
