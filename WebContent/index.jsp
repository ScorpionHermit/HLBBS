<%@ page language="java" pageEncoding="GBK" 
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");       // 设置字符集

BoardDao boardDao = new BoardDaoImpl();    // 得到版块Dao的实例
TopicDao topicDao = new TopicDaoImpl();    // 得到主题Dao的实例
UserDao  userDao  = new UserDaoImpl();     // 得到用户Dao的实例
Map      mapBoard = boardDao.findBoard();  // 取得Map形式的版块信息
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>欢迎访问青鸟学员论坛</TITLE>
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

<DIV>

<!--      主体        -->
	<DIV class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr2" align="center">
				<TD colSpan="2">论坛</TD>
				<TD style="WIDTH: 5%;">主题</TD>
				<TD style="WIDTH: 25%">最后发表</TD>
			</TR>
		<!--       主版块       -->
			<%
			List listMainBoard = (List)mapBoard.get(new Integer(0));
			for( int i=0; i<listMainBoard.size(); i++ ) {
				Board mainBoard = ((Board)listMainBoard.get(i));             // 循环取得主版块
			%>
			<TR class="tr3">
				<TD colspan="4"><%=mainBoard.getBoardName() %></TD>
			</TR>
		<!--       子版块       -->
			<%
				List listSonBoard = (List)mapBoard.get( new Integer(mainBoard.getBoardId()) );
				for( int j=0; j<listSonBoard.size(); j++ ) {
					Board sonBoard  = (Board)listSonBoard.get(j);            // 循环取得子版块
					Topic topic     = new Topic();                           // 最后发表的主题
					User  user      = new User();                            // 最后发表的主题的作者
					int   boardId   = sonBoard.getBoardId();
					List  listTopic = topicDao.findListTopic( 1, boardId );  // 取得该板块主题列表
					if( listTopic!=null && listTopic.size()>0 ) {
						topic = (Topic)listTopic.get(0);                     // 取得最后发表的帖子
						user  = userDao.findUser( topic.getUid() );
			}
			%>
			<TR class="tr3">
				<TD width="5%">&nbsp;</TD>
				<TH align="left">
					<IMG src="image/board.gif">
					<A href="list.jsp?page=1&boardId=<%=boardId %>"><%=sonBoard.getBoardName() %></A>
				</TH>
				<TD align="center"><%=topicDao.findCountTopic(boardId) %></TD>
				<TH>
					<SPAN>
						<A href="detail.jsp?page=1&boardId=<%=boardId %>&topicId=<%=topic.getTopicId() %>"><%=topic.getTitle() %></A>
					</SPAN>
					<BR/>
					<SPAN><%=user.getUName() %></SPAN>
					<SPAN class="gray">[ <%=topic.getPublishTime().substring(0,16) %> ]</SPAN>
				</TH>
			</TR>
			<%
				}
			}
			%>
		</TABLE>
	</DIV>
</DIV>

<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd 版权所有</CENTER>
</BODY>
</HTML>