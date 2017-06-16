<%@page import="java.util.ArrayList"%>
<%@page import="com.hlbbs.DAO.SectionDAO"%>
<%@page import="com.hlbbs.Modal.Section"%>
<%@page import="com.hlbbs.Utility.LeftNavbar"%>
<%@page import="com.hlbbs.Modal.User"%>
<%@page import="com.hlbbs.Modal.BBS"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	
<% request.setCharacterEncoding("utf-8"); %>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/css/bootstrap.min.css">
		<script src="http://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
		<script src="<%=request.getContextPath() %>/js/bootstrap.min.js"></script> 
		<title><%=BBS.BBS_Title %></title>
		<style type="text/css">
			.ordertitle{
				font-size: 25px;
    			font-family: Youyuan;
			}
			
			.ordercount{
				color: cornflowerblue;
   				font-size: 55px;
    			font-family: CURSIVE;
			}
			
			.ordermoney{
				color: gold;
   				font-size: 55px;
    			font-family: CURSIVE;
			}
		</style>
		<script type="text/javascript">
		<%
			String msg = (String)request.getAttribute("msg");
			if(msg != null)
			{
		%>
				alert("<%=msg %>");
		<%
				request.removeAttribute("msg");
			}
		%>
		$(function () 
		{ 
			$('#modifymodal').on('show.bs.modal', function (event) 
			{
  				var button = $(event.relatedTarget);
  				var secID = button.data('sectionid');
  				var secName = button.data('sectionname');
  				var secManager = button.data('sectionmanager');
 				var modal = $(this);
  				modal.find(".modal-body input[name='sectionid']").val(secID);
  				modal.find(".modal-body input[name='sectionname']").val(secName);
  				modal.find(".modal-body input[name='sectionmanager']").val(secManager);
			})
		});
		</script>
	</head>
	<body style="background-color:#f7f7f7;font-family:'Microsoft Yahei'">
	
	<!-- Dialog -->
	<div id="modifymodal" class="modal fade">
		<div class="modal-dialog"  style="width:200px">
			<div class="modal-content">
			<form action="<%=request.getContextPath() %>/SectionManagerControl" method="POST">
				<input type="hidden" name="operation" value="modify" />
				<div class="modal-header">
					<span>板块修改</span>
					<button data-dismiss="modal" type="button" class="close">&times;</button>
				</div>
				<div class="modal-body">
						<span>板块ID</span>
						<input name="sectionid" class="form-control" style="width:150px" readonly="readonly" type="text"/>
						<br>
						<span>板块名称</span>
						<input name="sectionname" class="form-control" style="width:150px" type="text"/>
						<br>
						<span>版主</span>
						<input name="sectionmanager" class="form-control" style="width:150px" type="text"/>
						<br>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" style="width:100%" value="确认"/>
				</div>
			</form>
			</div>
		</div>
	</div>
	<!-- Dialog -->
	
	<!-- Dialog -->
	<div id="addmodal" class="modal fade">
		<div class="modal-dialog"  style="width:200px">
			<div class="modal-content">
			<form action="<%=request.getContextPath() %>/SectionManagerControl" method="POST">
				<input type="hidden" name="operation" value="add" />
				<div class="modal-header">
					<span>板块添加</span>
					<button data-dismiss="modal" type="button" class="close">&times;</button>
				</div>
				<div class="modal-body">
						<span>板块名称</span>
						<input name="sectionname" class="form-control" style="width:150px" type="text"/>
						<br>
						<span>版主</span>
						<input name="sectionmanager" class="form-control" style="width:150px" type="text"/>
						<br>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" style="width:100%" value="确认"/>
				</div>
			</form>
			</div>
		</div>
	</div>
	<!-- Dialog -->
	
	<!-- Header -->
		<jsp:include page="topnavbar.jsp">
			<jsp:param value="板块管理" name="title"/>
		</jsp:include>
	<!-- Header -->
	
	<!-- Content -->
	<div class="container" style="margin-top:20px">
	<!-- Left-Navbar -->
		<div style="background-color:white;width:30%;display:inline-block;">
			<jsp:include page="leftnavbar.jsp">
				<jsp:param value="<%=LeftNavbar.SectionManager.Section_Manager %>" name="active"/>
			</jsp:include>
		</div>
	<!-- Left-Navbar -->

<%
	User user =(User)session.getAttribute("user");
	String userName = user.getName();
	
	SectionDAO secDAO = new SectionDAO(null);
	ArrayList<Section> secs = secDAO.getAllSection();
	
%>
		<div style="padding-left:20px;background-color:#fff;border:1px solid #e6e6e6;width:69%;display:inline-block;float:right">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<td>板块ID</td>
						<td>板块名称</td>
						<td>版主</td>
						<td>总帖数</td>
						<td>操作</td>
					</tr>
				</thead>
				
				<tbody>
				<%
				for(Section s : secs)
				{
				%>
					<tr>
						<td><%=s.getID() %></td>
						<td><%=s.getNvcSectionName() %></td>
						<td><%=s.getIntModerator() %></td>
						<td><%=s.getIntPosts() %></td>
						<td><button data-toggle="modal" data-target="#modifymodal" data-sectionname="<%=s.getNvcSectionName() %>" data-sectionmanager="<%=s.getIntModerator() %>" data-sectionid="<%=s.getID() %>" class="btn btn-primary">板块修改</button></td>
					</tr>
				<%
				}
				%>
				</tbody>
				<tfoot>
					<tr>
						<td></td>
						<td></td>
						<td></td>
						<td></td>
						<td><button data-toggle="modal" data-target="#addmodal" class="btn btn-primary">添加板块</button></td>
					</tr>
				</tfoot>
			</table>
		</div>	
	<!-- Content -->
	</div>
	</body>
</html>