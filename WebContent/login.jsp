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
request.setCharacterEncoding("GBK");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>青鸟学员论坛--登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<Link rel="stylesheet" type="text/css" href="style/style.css"/>
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

<BR/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>
</DIV>
<!--      用户登录表单        -->
<DIV class="t" style="MARGIN-TOP: 15px" align="center">
	<FORM name="loginForm" onSubmit="return check()" action="DoLogin" method="post">
		<br/><b>用户名:</b> &nbsp;<INPUT class="input" tabIndex="1"  type="text"      maxLength="20" size="35" name="uName"  style="text-align:center">
		<br/><b>密　码:</b> &nbsp;<INPUT class="input" tabIndex="2"  type="password"  maxLength="20" size="35" name="uPass"  style="text-align:center">
		<br/><b>验证码:</b> &nbsp;<INPUT class="input" tabIndex="3"  type="text"  maxLength="10" size="25" name="authimg"  style="text-align:center"><img alt="验证码图片" src="authimg" align="absmiddle" />
		<br/><br><INPUT class="btn"  tabIndex="6"  type="submit" value="登 录" style="background:#e0f0f9;">
	</FORM>
</DIV>
<!--      声明        -->
<BR/>
<CENTER class="gray">2017 MaAnShan AHUT JSJ ZXZ6 Working Team 版权所有</CENTER>
</BODY>
</HTML>
