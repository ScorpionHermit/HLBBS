<%@ page language="java" pageEncoding="GBK" 
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");                                      // 设置字符集

TopicDao topicDao  = new TopicDaoImpl();                                  // 得到主题Dao的实例
ReplyDao replyDao  = new ReplyDaoImpl();                                  // 得到回复Dao的实例
BoardDao boardDao  = new BoardDaoImpl();                                  // 得到版块Dao的实例
UserDao  userDao   = new UserDaoImpl();                                   // 得到用户Dao的实例

int      boardId   = Integer.parseInt( request.getParameter("boardId") );  // 取得版块id
Board    board     = boardDao.findBoard( boardId );                        // 取得版块信息
int      p         = Integer.parseInt( request.getParameter("page") );     // 取得页数
List     listTopic = topicDao.findListTopic( p, boardId );                 // 取得该板块主题列表

int     prep       = p;                                                    // 上一页
int     nextp      = p;                                                    // 下一页
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
<TITLE>青鸟学员论坛--帖子列表</TITLE>
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
<DIV>
<!--      导航        -->
	<br/>
	<DIV>
		&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
		<B><a href="list.jsp?page=1&boardId=<%=boardId %>"><%=board.getBoardName() %></a></B>
	</DIV>
	<br/>
<!--      新帖        -->
	<DIV>
		<A href="post.jsp?post=newtopic&boardId=<%=boardId %>"><IMG src="image/post.gif" border="0"></A>
	</DIV>
<!--         翻 页         -->
	<DIV>
		<a href="list.jsp?page=<%=prep%>&boardId=<%=boardId %>">上一页</a>|
		<a href="list.jsp?page=<%=nextp%>&boardId=<%=boardId %>">下一页</a>
	</DIV>

	<DIV class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">		
			<TR>
				<TH class="h" style="WIDTH: 100%" colSpan="4"><SPAN>&nbsp;</SPAN></TH>
			</TR>
<!--       表 头           -->
			<TR class="tr2">
				<TD>&nbsp;</TD>
				<TD style="WIDTH: 80%" align="center">文章</TD>
				<TD style="WIDTH: 10%" align="center">作者</TD>
				<TD style="WIDTH: 10%" align="center">回复</TD>
			</TR>
<!--         主 题 列 表        -->
			<%
			for( int i=0; i<listTopic.size(); i++ ) {
			Topic    topic   = (Topic)listTopic.get(i);             // 循环取得主题对象
			User     user    = userDao.findUser( topic.getUid() );  // 取得该主题的发布用户
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
<!--            翻 页          -->
	<DIV>
		<a href="list.jsp?page=<%=prep%>&boardId=<%=boardId %>">上一页</a>|
		<a href="list.jsp?page=<%=nextp%>&boardId=<%=boardId %>">下一页</a>
	</DIV>
</DIV>
<!--             声 明          -->
<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd 版权所有</CENTER>

</BODY>
</HTML>
