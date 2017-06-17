<%@page import="com.hlbbs.Utility.LeftNavbar"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%!
	private String isActive(String active, String param)
	{
		return active.equals(param) ? "class='active'" : "";
	}

	private String isCollapse(String active, String ...params)
	{
		final String uncollapse = "class='nav nav-pills nav-stacked'";
		final String collapse = "class='nav nav-pills nav-stacked collapse'";
		for(String param : params)
			if(active.equals(param))
				return uncollapse;
		return collapse;
	}
%>
   <%
   String active = request.getParameter("active");
   %>
		<ul class="nav nav-pills nav-stacked">
			<li id="sectionmanager" data-parent="sectionmanager" data-toggle="collapse" data-target="#subsectionmanager" <%=isActive(active, LeftNavbar.SectionManager.Section_Manager) %> >
				<a href="sectionmanager.jsp">板块管理</a>
			</li>
			<li id="usermanager" data-parent="usermanager" data-toggle="collapse" data-target="#subusermanager">
				<a href="#">用户管理<span style="float:right" class="glyphicon glyphicon-triangle-bottom"></span></a>
			</li>
				<ul id="subusermanager" style="padding-left:20px" <%=isCollapse(active, LeftNavbar.UserManager.Role_Manager, LeftNavbar.UserManager.User_Manager) %>>
					<li <%=isActive(active, LeftNavbar.UserManager.User_Manager) %>><a href="<%=request.getContextPath() %>/manager/usermanager.jsp">删除用户</a></li>
					<li <%=isActive(active, LeftNavbar.UserManager.Role_Manager) %>><a href="<%=request.getContextPath() %>/manager/rolemanager.jsp">修改权限</a></li>
				</ul>
			<li id="postmanager" data-parent="postmanager" data-toggle="collapse" data-target="#subsectionmanager" <%=isActive(active, LeftNavbar.PostManager.Post_Manager) %>>
				<a href="postmanager.jsp">帖子管理<span style="float:right"></span></a>
			</li>
		</ul>