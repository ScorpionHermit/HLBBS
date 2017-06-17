<%@ page language="java" pageEncoding="GBK"
	import="com.hlbbs.Modal.*"%>
<%
request.setCharacterEncoding("GBK");
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>青鸟学员论坛--登录</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="<%=path%>/style/style.css"/>
</HEAD>

<BODY>
<DIV>
	<IMG  style="width:250px" src="<%=path%>/image/logo.png">
</DIV>

<!--      用户信息、登录、注册        -->
<%
if(session.getAttribute("user") == null){
%>
<DIV class="h">
	您尚未　<a href="<%=path%>/login.jsp">登录</a>
	&nbsp;| &nbsp; <A href="<%=path%>/reg.jsp">注册</A> |
</DIV>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
<DIV class="h">
	您好：　<%=loginUser.getName() %>
	&nbsp;| &nbsp; <A href="<%=path%>/manage/doLogout.jsp">登出</A> |
</DIV>
<%
}
%>

<BR/>
<!--      导航        -->
<DIV>
	&gt;&gt;<B><a href="<%=path%>/index.jsp">论坛首页</a></B>
</DIV><BR/>
<!--      错误信息        -->
<DIV class="t" align="center">
	<BR/>
	<font color="red"><%=request.getParameter("msg") %></font>
	<BR/><BR/>
	<input type="button" value="返 回" onclick="window.history.back();" class="btn"/>
	<BR/><BR/>
</DIV>
<!--      声明        -->
<BR/>
<CENTER class="gray">2017-安徽工业大学ZXZ6工作室-版权所有</CENTER>
</BODY>
</HTML>
