<%@ page language="java" pageEncoding="GBK"
	import="com.hlbbs.Modal.*"%>
<%
request.setCharacterEncoding("GBK");
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>����ѧԱ��̳--��¼</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="<%=path%>/style/style.css"/>
</HEAD>

<BODY>
<DIV>
	<IMG  style="width:250px" src="<%=path%>/image/logo.png">
</DIV>

<!--      �û���Ϣ����¼��ע��        -->
<%
if(session.getAttribute("user") == null){
%>
<DIV class="h">
	����δ��<a href="<%=path%>/login.jsp">��¼</a>
	&nbsp;| &nbsp; <A href="<%=path%>/reg.jsp">ע��</A> |
</DIV>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
<DIV class="h">
	���ã���<%=loginUser.getName() %>
	&nbsp;| &nbsp; <A href="<%=path%>/manage/doLogout.jsp">�ǳ�</A> |
</DIV>
<%
}
%>

<BR/>
<!--      ����        -->
<DIV>
	&gt;&gt;<B><a href="<%=path%>/index.jsp">��̳��ҳ</a></B>
</DIV><BR/>
<!--      ������Ϣ        -->
<DIV class="t" align="center">
	<BR/>
	<font color="red"><%=request.getParameter("msg") %></font>
	<BR/><BR/>
	<input type="button" value="�� ��" onclick="window.history.back();" class="btn"/>
	<BR/><BR/>
</DIV>
<!--      ����        -->
<BR/>
<CENTER class="gray">2017-���չ�ҵ��ѧZXZ6������-��Ȩ����</CENTER>
</BODY>
</HTML>
