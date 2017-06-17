<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.hlbbs.Modal.Role"%>
<%@page import="com.hlbbs.DAO.UserDAO"%>
<%@page import="com.hlbbs.DAO.PostDAO"%>
<%@page import="com.hlbbs.Modal.Post"%>
<%@page import="com.hlbbs.Modal.User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hlbbs.DAO.SectionDAO"%>
<%@page import="com.hlbbs.Modal.Section"%>
<%@ page language="java" pageEncoding="utf-8"
	contentType="text/html; charset=utf-8"%>
<%
	request.setCharacterEncoding("utf-8"); // 设置字符集

	Section section = new Section();
	SectionDAO secDAO = new SectionDAO(section);
	ArrayList<Section> secs = secDAO.getAllSection();
%>
<<script type="text/javascript">
  function sign(){
	  
  }
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<html>
<head>
<title>荟灵BBS-安徽工业大学</title>
<meta http-equiv=content-type content="text/html; charset=gbk">
<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>

	<div>
		<img style="width:250px" src="image/logo.png">
	</div>
	<!--      用户信息、登录、注册        -->
	<%
		if (session.getAttribute("user") == null)
		{
	%>
	<div class="h">
		您尚未 <a href="login.jsp">登录</a> &nbsp;| &nbsp; <a href="reg.jsp">注册</a>
		|
	</div>
	<%
		} else
		{
			User user = (User) session.getAttribute("user");
	%>
	<div class="h">
		您好：
		<a href="user.jsp"><%=user.getName() %></a>
		&nbsp;|&nbsp; <a href="LoginoutControl">登出</a> |
		&nbsp;&nbsp;  <a href="user.jsp">我的空间</a> |
		&nbsp;&nbsp; <a href="SignControl" onclick="return false;alert('签到成功')">签到</a> |
	<%
		if(user.getRoleType() == Role.WebManager)
		{
	%>
		&nbsp; <a href="manager/sectionmanager.jsp">论坛管理</a> |
	<%
		}
	%>
	</div>
	<%
		}
	%>

	<div>
		<!--      主体        -->
		<div class="t">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class="tr2" align="center">
					<td colspan="2">论坛</td>
					<td style="width: 5%;">主题帖数</td>
					<td style="width: 25%">最后发表</td>
				</tr>
				<!--    版块       -->
				<%
					for (Section s : secs)
					{
						int lastPostID = -1;
						int postCount = 0;
						String lastPostTitle = "";
						String lastPostTime = "";
						String lastUserName = "";
						
						Post post = new Post();
						User user = new User();
						int sectionID = s.getID();
						PostDAO pDAO = new PostDAO(post);
						post.setSectionID(sectionID);
						boolean isFinded =  pDAO.searchNewById();
						if(isFinded)
						{
							postCount = pDAO.postsCount();
							UserDAO uDAO = new UserDAO(user);
							user.setId(post.getPostMan());
							uDAO.findUserById();
							lastPostID = post.getId();
							lastPostTitle = post.getTitle();
							lastPostTime = post.getPostTime();
							lastUserName = user.getName();
						}
						else
						{
							post = null;
							user = null;
						}
				%>
				<tr class="tr3">
					<td width="5%">&nbsp;</td>
					<th align="left"><img src="image/board.gif"> <a
						href="list.jsp?page=1&boardid=<%=sectionID%>"><%=s.getNvcSectionName() %></a>
					</th>
					<td align="center"><%=postCount %></td>
					<th>
						<span> 
							<a href="DetailControl?page=1&boardid=<%=sectionID%>&topicid=<%=lastPostID %>&action=show"><%=lastPostTitle %></a>
						</span> 
						<br/>
						<span><%=lastUserName %></span>
						<span class="gray">[<%=lastPostTime %> ]</span></th>
				</tr>
				<%
					}
				
				%>
			</table>
		</div>
	</div>

	<br />
	<center class="gray">2017-安徽工业大学ZXZ6工作室-版权所有</center>
</body>
</html>