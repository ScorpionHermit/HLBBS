<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="com.hlbbs.DAO.UserDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hlbbs.Modal.User"%>
<%@page import="com.hlbbs.DAO.PostDAO"%>
<%@page import="com.hlbbs.Modal.Post"%>
<%@page import="com.hlbbs.Modal.Comment" %>
<%@page import="com.hlbbs.DAO.CommentDAO" %>

<%request.setCharacterEncoding("utf-8"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="style/style.css" />
<title>个人中心</title>
<script>
	function info() {
		var id1 = document.getElementById("id1");
		var id2 = document.getElementById("id2");
		var id3 = document.getElementById("id3");
		var id4 = document.getElementById("id4");
		
		id1.style.display = "block";
		id2.style.display = "none";
		id3.style.display = "none";
		id4.style.display = "none";
	}
	
	function modifypwd() {
		var id1 = document.getElementById("id1");
		var id2 = document.getElementById("id2");
		var id3 = document.getElementById("id3");
		var id4 = document.getElementById("id4");
		
		id1.style.display = "none";
		id2.style.display = "block";
		id3.style.display = "none";
		id4.style.display = "none";
	}
	
	function posts() {
		var id1 = document.getElementById("id1");
		var id2 = document.getElementById("id2");
		var id3 = document.getElementById("id3");
		var id4 = document.getElementById("id4");
		
		id1.style.display = "none";
		id2.style.display = "none";
		id3.style.display = "block";
		id4.style.display = "none";
	}
	
	function reply() {
		var id1 = document.getElementById("id1");
		var id2 = document.getElementById("id2");
		var id3 = document.getElementById("id3");
		var id4 = document.getElementById("id4");
		
		id1.style.display = "none";
		id2.style.display = "none";
		id3.style.display = "none";
		id4.style.display = "block";
	}
</script>
</head>
<body>
	<div>
		<img style="width:250px" src="image/logo.png">
	</div>
	<!--      用户信息、登录、注册        -->
	<%
		if (session.getAttribute("user") == null) {
	%>
	<script>
		alert("您未登录，请先登录！");
		window.location.href = "index.jsp";
	</script>
	<%
		} else {
			User u = new User();
			UserDAO uDAO = new UserDAO(u);
			User user = (User)session.getAttribute("user");

			Comment comment = new Comment();
			comment.setUserId(user.getId());
			CommentDAO cDAO = new CommentDAO(comment);
			ArrayList<Comment> comments = cDAO.searchByUserId();

			Post post = new Post();
			post.setPostMan(user.getId());
			PostDAO pDAO = new PostDAO(post);
			ArrayList<Post> posts = pDAO.searchByPostsMan();
	%>
	<div class="h">
		您好：
		<a href="user.jsp"><%=user.getName() %></a>
		&nbsp;| &nbsp; <a href="LoginoutControl">登出</a> |
	</div>
	
	<div>
		<!-- 主体   -->
		<!--      导航        -->
		<br/>
		<div>
			&gt;&gt;<b><a href="index.jsp">论坛首页</a></b>&gt;&gt;个人中心
		</div>
		<br/>
		
		<div class="t">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr class="tr3" align="center">
					<th style="width:10%">
						<img src="image/head/<%=user.getHeadPortrait() %>" style="margin-left:20px" />	
					</th>
					<th style="width:15%">
						<b>用户名：</b>
						<b><%=user.getName() %></b><br>
						<b>等级：</b>
						<%
							u.setLevel(user.getLevel());
							String levelName = uDAO.getLevel();
						%>
						<b><%=levelName %></b><br>
						<b>个性签名：</b>
						<b><%=user.getPersonalizedSignature() %></b><br>
						<b>积分：</b>
						<%
							u.setId(user.getId());
						%>
						<b><%=uDAO.getIntegral() %></b>
					</th>
					<th>
						<a href="javascript:void(0);" onclick="info()">个人信息修改</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="modifypwd()">密码修改</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="posts()">查看自己发布的帖子</a>
						&nbsp;&nbsp;&nbsp;&nbsp;
						<a href="javascript:void(0);" onclick="reply()">查看自己回复的帖子</a>
					</th>
				</tr>
			</table>
		</div>
		
		<div class="t" id="id1" style="display:none">
			<form method="post" action="UpdateInfo">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr class="tr3" align="center">
						<td style="width:10%"><b>用户名：</b></td>
						<td align="left"><input type="text" name="username" value=<%=user.getName() %> /></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td style="width:10%"><b>性&nbsp;别：</b></td>
						<td align="left">
						<%
							if (user.getSex().equals("男")) {
						%>
								<input type="radio" name="sex" value="男" checked />男
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="sex" value="女" />女
						<%
							} else if (user.getSex().equals("女")) {
						%>
								<input type="radio" name="sex" value="男" />男
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="sex" value="女" checked />女
						<%
							} else {
						%>
								<input type="radio" name="sex" value="男" />男
								&nbsp;&nbsp;&nbsp;&nbsp;
								<input type="radio" name="sex" value="女" />女
						<%
							}
						%>
						</td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td style="width:10%"><b>邮&nbsp;箱：</b></td>
						<td align="left"><input type="text" name="email" value=<%=user.getEmailAddress() %> /></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td style="width:10%"><b>个性签名：</b></td>
						<td align="left"><textarea rows="3" cols="20" name="PersonalizedSignature"><%=user.getPersonalizedSignature() %></textarea></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td style="width:10%"><b>注册时间：</b></td>
						<td align="left"><b><%=user.getRegistertime() %></b></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td>
							<input type="submit" value="提交" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="t" id="id2" style="display:none">
			<form method="post" action="UpdatePwd">
				<table cellspacing="0" cellpadding="0" width="100%">
					<tr class="tr3" align="center">
						<td style="width:10%"><b>旧密码：</b></td>
						<td align="left"><input type="password" name="oldpwd" /></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td style="width:10%"><b>新密码：</b></td>
						<td align="left"><input type="password" name="newpwd" /></td>	
					</tr>
					
					<tr class="tr3" align="center">
						<td>
							<input type="submit" value="提交" />
						</td>
					</tr>
				</table>
			</form>
		</div>
		
		<div class="t" id="id3" style="display:none">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h" style="width: 100%" colspan="4"><span>&nbsp;</span></th>
				</tr>
				
				<!-- 表 头   -->
				<tr class="tr2">
					<td>&nbsp;</td>
					<td style="width: 80%" align="center">标题</td>
					<td style="width: 5%" align="center">回复数</td>
					<td style="width: 15%" align="center">发表时间</td>
				</tr>
				
				<!-- 帖子列表   -->
				<%
				for (Post p : posts) {
					u.setId(p.getPostMan());
					post.setId(p.getId());
					uDAO.findUserById();
					int sectionID = p.getSectionID();
					int commentCount = pDAO.commentCount();
					String postTime = p.getPostTime();
				%>
				<tr class="tr3">
					<td><img src="image/topic.gif" border=0></td>
					<td style="font-size: 15px">
						<a href="detail.jsp?page=1&boardid=<%=sectionID %>&topicid=<%=p.getId() %>"><%=p.getTitle() %></a>
					</td>
					<td align="center"><%=commentCount %></td>
					<td align="center"><%=postTime %></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
		
		<div class="t" id="id4" style="display:none">
			<table cellspacing="0" cellpadding="0" width="100%">
				<tr>
					<th class="h" style="width: 100%" colspan="4"><span>&nbsp;</span></th>
				</tr>
				
				<!-- 表 头   -->
				<tr class="tr2">
					<td>&nbsp;</td>
					<td style="width: 35%" align="center">标题</td>
					<td style="width: 45%" align="center">回复内容</td>
					<td style="width: 20%" align="center">发表时间</td>
				</tr>
				
				<!-- 帖子列表   -->
				<%
				for (Comment c : comments) {
					Post p = new Post();
					p.setId(c.getPostsId());
					
					PostDAO pDao = new PostDAO(p);
					
					int sectionID = 0;
					int postsID = 0;
					String title = "";
					
					if (pDao.searchByPostId()) {
						sectionID = p.getSectionID();
						postsID = p.getId();
						title = p.getTitle();
					}
					
					String replyContent = c.getContent();
					String replyTime = c.getComTime();
				%>
				<tr class="tr3">
					<td><img src="image/topic.gif" border=0></td>
					<td style="font-size: 15px">
						<a href="detail.jsp?page=1&boardid=<%=sectionID %>&topicid=<%=postsID %>"><%=title %></a>
					</td>
					<td align="center"><%=replyContent %></td>
					<td align="center"><%=replyTime %></td>
				</tr>
				<%
				}
				%>
			</table>
		</div>
	</div>
	<%
		}
	%>
	
	<br>
	<center class="gray">2017-安徽工业大学ZXZ6工作室-版权所有</center>
</body>
</html>