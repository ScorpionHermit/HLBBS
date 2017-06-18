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
request.setCharacterEncoding("utf-8");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>荟灵BBS-安徽工业大学</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<Link rel="stylesheet" type="text/css" href="style/style.css"/>
<Link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<style>
	table tr
	{
		display:block;
		margin:30px auto;
		font-family:"Youyuan"
	}
</style>
<script language="javascript">
function check() {
 if(document.loginForm.uName.value==""){
    alert("用户名不能为空");
    return false;
 }
 if(document.loginForm.uPass.value==""){
    alert("密码不能为空");
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
if(request.getSession().getAttribute("user") == null){
%>
	<DIV class="h">
		您尚未　<a href="login.jsp">登录</a>
		&nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
	</DIV>
<%
} else {
	User loginUser = (User)request.getSession().getAttribute("user");
%>
	<DIV class="h">
		您好：　<%=loginUser.getName() %>
		&nbsp;| &nbsp; <a href="LoginoutControl">登出</A> |
	</DIV>

<%
}
%>

<br/>
<!--      导航        -->
<div>
	&gt;&gt;<b><a href="index.jsp">论坛首页</a></b>
</div>
<!--      用户登录表单        -->
<div class="t" style="margin-top: 15px" align="center">
	<form name="loginform" onsubmit="return check()" action="DoLogin" method="post">
		<div style="border:1px solid black;border-radius:5px;width:400px;margin:20px auto;padding:30px 40px">
			<table>
			<tr>
				<td><span>用户名:</span></td>
				<td><input class="form-control" tabindex="1"  type="text" style="width:250px" maxlength="20" name="name"></td>
			</tr>
			<tr>
				<td><span>密　码:</span></td>
				<td><input class="form-control" tabindex="2"  type="password" style="width:250px"  maxlength="20" name="pass"></td>
			</tr>
			<tr>
				<td><span>验证码:</span></td>
				<td>
					<input class="form-control" tabindex="3"  type="text" style="width:150px;display:inline-block"  maxlength="10" name="authimg">
					<img alt="验证码图片" src="authimg" style="margin-left:20px" />
				</td>
			</tr>
			</table>
			<input class="btn btn-primary"  tabindex="4" style="width:100%"  type="submit" value="登 录">
		</div>
	</form>
</div>
<!--      声明        -->
<br/>
<center class="gray">2017-安徽工业大学ZXZ6工作室-版权所有</center>
</body>
</HTML>
