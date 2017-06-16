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
			<li id="sectionmanager" data-parent="sectionmanager" data-toggle="collapse" data-target="#subsectionmanager" >
				<a href="##">板块管理<span style="float:right" class="glyphicon glyphicon-triangle-bottom"></span></a>
			</li>
		</ul>