<%@ page language="java" pageEncoding="GBK"%>
<%
if( session.getAttribute("user") != null ) {
	session.removeAttribute("user");
}
response.sendRedirect("../index.jsp");
%>