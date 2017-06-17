<%@page import="com.hlbbs.Modal.Role"%>
<%@page import="com.hlbbs.DAO.UserDAO"%>
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
			$('#subusermanager').on('show.bs.collapse', function () {
				$('#usermanager span')[0].className = "glyphicon glyphicon-triangle-top";
			})
			$('#subusermanager').on('hide.bs.collapse', function () {
				$('#usermanager span')[0].className = "glyphicon glyphicon-triangle-bottom";
			})
			
			$('#modifyrolemodal').on('show.bs.modal', function (event) 
			{
  				var button = $(event.relatedTarget);
  				var secID = button.data('userid');
 				var modal = $(this);
  				modal.find(".modal-body input[name='userid']").val(secID);
			})
		});
		
		</script>
	</head>
	<body style="background-color:#f7f7f7;font-family:'Microsoft Yahei'">
	
	<!-- Dialog -->
	<div id="modifyrolemodal" class="modal fade">
		<div class="modal-dialog"  style="width:200px">
			<div class="modal-content">
			<form action="<%=request.getContextPath() %>/RoleManagerControl" method="POST">
				<input type="hidden" name="operation" value="modify" />
				<div class="modal-header">
					<span>修改用户角色</span>
					<button data-dismiss="modal" type="button" class="close">&times;</button>
				</div>
				<div class="modal-body">
						<span>用户ID</span>
						<input name="userid" class="form-control" style="width:150px" readonly="readonly" type="text"/>
						<br>
						<select name="roletype">
							<option value="2">普通用户</option>
							<option value="8">管理员</option>
						</select>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" style="width:100%" value="确认修改"/>
				</div>
			</form>
			</div>
		</div>
	</div>
	<!-- Dialog -->
	
	<!-- Header -->
		<jsp:include page="topnavbar.jsp">
			<jsp:param value="用户管理" name="title"/>
		</jsp:include>
	<!-- Header -->
	
	<!-- Content -->
	<div class="container" style="margin-top:20px">
	<!-- Left-Navbar -->
		<div style="background-color:white;width:30%;display:inline-block;">
			<jsp:include page="leftnavbar.jsp">
				<jsp:param value="<%=LeftNavbar.UserManager.Role_Manager %>" name="active"/>
			</jsp:include>
		</div>
	<!-- Left-Navbar -->

<%
	User user = new User();
	UserDAO uDAO = new UserDAO(user);
	
	ArrayList<User> users = uDAO.GetAllUser();
%>
		<div style="padding-left:20px;background-color:#fff;border:1px solid #e6e6e6;width:69%;display:inline-block;float:right">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<td>用户ID</td>
						<td>用户名</td>
						<td>邮箱</td>
						<td>等级</td>
						<td>积分</td>
						<td>角色</td>
						<td>操作</td>
					</tr>
				</thead>
				
				<tbody>
				<%
				for(User u : users)
				{
				%>
					<tr>
						<td><%=u.getId() %></td>
						<td><%=u.getName() %></td>
						<td><%=u.getEmailAddress() %></td>
						<td><%=u.getLevel() %></td>
						<td><%=u.getIntegral() %></td>
						<td><%=Role.toString(u.getRoleType()) %></td>
						<td><button data-toggle="modal" data-target="#modifyrolemodal" data-userid="<%=u.getId() %>" class="btn btn-primary">更改角色</button></td>
					</tr>
				<%
				}
				%>
				</tbody>
			</table>
		</div>	
	<!-- Content -->
	</div>
	</body>
</html>