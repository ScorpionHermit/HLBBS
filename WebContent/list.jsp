<%@page import="com.hlbbs.DAO.CommentDAO"%>
<%@page import="com.hlbbs.Modal.Comment"%>
<%@page import="com.hlbbs.DAO.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hlbbs.Modal.User"%>
<%@page import="com.hlbbs.DAO.PostDAO"%>
<%@page import="com.hlbbs.Modal.Post"%>
<%@page import="com.hlbbs.DAO.SectionDAO"%>
<%@page import="com.hlbbs.Modal.Section"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%
request.setCharacterEncoding("utf-8");

int      sectionID   = Integer.parseInt(request.getParameter("boardid") );
Section  section = new Section();
section.setID(sectionID);
SectionDAO secDAO  = new SectionDAO(section);
secDAO.findBoardByID();

Post post = new Post();
post.setSectionID(sectionID);
PostDAO pDAO = new PostDAO(post);
ArrayList<Post> posts = pDAO.searchById();

User u = new User();
UserDAO uDAO = new UserDAO(u);

Comment comment = new Comment();
CommentDAO cDAO = new CommentDAO(comment);

%>

<!doctype html public "-//w3c//dtd html 4.01 transitional//en" "http://www.w3c.org/tr/1999/rec-html401-19991224/loose.dtd">
<html>
<head>
<title>青鸟学员论坛--帖子列表</title>
<meta http-equiv=content-type content="text/html; charset=gbk">
<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>
<div>
	<img src="image/logo.gif">
</div>
<!--      用户信息、登录、注册        -->
<%
if(session.getAttribute("user") == null){
%>
<div class="h">
	您尚未　<a href="login.jsp">登录</a>
	&nbsp;| &nbsp; <a href="reg.jsp">注册</a> |
</div>
<%
} else {
User loginUser = (User)session.getAttribute("user");
%>
<div class="h">
	您好：　<%=loginUser.getName() %>
	&nbsp;| &nbsp; <a href="manage/dologout.jsp">登出</a> |
</div>
<%
}
%>

<!--      主体        -->
<div>
<!--      导航        -->
	<br/>
	<div>
		&gt;&gt;<b><a href="index.jsp">论坛首页</a></b>&gt;&gt;
		<b><a href="list.jsp?page=1&boardid=<%=sectionID %>"><%=section.getNvcSectionName() %></a></b>
	</div>
	<br/>
<!--      新帖        -->
	<div>
		<a href="post.jsp?post=newtopic&boardid=<%=sectionID %>"><img src="image/post.gif" border="0"></a>
	</div>
<!--         翻 页         -->
	<div>
		<a href="list.jsp?page=<%=1%>&boardid=<%=sectionID %>">上一页</a>|
		<a href="list.jsp?page=<%=1%>&boardid=<%=sectionID %>">下一页</a>
	</div>

	<div class="t">
		<table cellspacing="0" cellpadding="0" width="100%">		
			<tr>
				<th class="h" style="width: 100%" colspan="4"><span>&nbsp;</span></th>
			</tr>
<!--       表 头           -->
			<tr class="tr2">
				<td>&nbsp;</td>
				<td style="width: 80%" align="center">文章</td>
				<td style="width: 10%" align="center">作者</td>
				<td style="width: 10%" align="center">回复</td>
			</tr>
<!--         帖子列表        -->
			<%
			for(Post p : posts) 
			{
				u.setId(p.getPostMan());
				uDAO.findUserById();
				comment.setPostsId(p.getId());
				int commentCount = 5;
			%>
			<tr class="tr3">
				<td><img src="image/topic.gif" border=0></td>
				<td style="font-size: 15px">
					<a href="detail.jsp?page=1&boardid=<%=sectionID %>&topicid=<%=p.getId() %>"><%=p.getTitle() %></a>
				</td>
				<td align="center"><%=u.getName() %></td>
				<td align="center"><%=commentCount %></td>
			</tr>
			<%
			}
			%>
		</table>
	</div>
<!--            翻 页          -->
	<div>
		<a href="list.jsp?page=<%=1%>&boardid=<%=sectionID %>">上一页</a>|
		<a href="list.jsp?page=<%=1%>&boardid=<%=sectionID %>">下一页</a>
	</div>
</div>
<!--             声 明          -->
<br/>
<center class="gray">2017-安徽工业大学zxz6工作室-版权所有</center>

</body>
</html>
