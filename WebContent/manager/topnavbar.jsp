<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>

<%
	String title = request.getParameter("title");
%>
<div style="box-shadow:5px 5px 15px -10px;height:60px">
	<a href="<%=request.getContextPath() %>">
		<img style="margin-left:80px;margin-top:5px;width:170px" src="<%=request.getContextPath() %>/image/logo.png"/>
	</a>
	<div style="display:inline-block;vertical-align:middle">
		<span style="border-left:1px solid"></span>
		<span style="margin-left:30px;font-size:30px;font-family:Youyuan"><%=title %></span>
	</div>
</div>