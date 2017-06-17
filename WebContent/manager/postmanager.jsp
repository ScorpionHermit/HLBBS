<%@page import="com.hlbbs.DAO.PostDAO"%>
<%@page import="com.hlbbs.Modal.Post"%>
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
			$('#delpostmodal').on('show.bs.modal', function (event) 
			{
  				var button = $(event.relatedTarget);
  				var secID = button.data('postid');
 				var modal = $(this);
  				modal.find(".modal-body input[name='postid']").val(secID);
			})
		});
		</script>
	</head>
	<body style="background-color:#f7f7f7;font-family:'Microsoft Yahei'">
	
	<!-- Dialog -->
	<div id="delpostmodal" class="modal fade">
		<div class="modal-dialog"  style="width:200px">
			<div class="modal-content">
			<form action="<%=request.getContextPath() %>/PostManagerControl" method="POST">
				<input type="hidden" name="operation" value="modify" />
				<div class="modal-header">
					<span>删帖</span>
					<button data-dismiss="modal" type="button" class="close">&times;</button>
				</div>
				<div class="modal-body">
						<span>帖子ID</span>
						<input name="postid" class="form-control" style="width:150px" readonly="readonly" type="text"/>
						<br>
				</div>
				<div class="modal-footer">
					<input type="submit" class="btn btn-primary" style="width:100%" value="确认删除"/>
				</div>
			</form>
			</div>
		</div>
	</div>
	<!-- Dialog -->
	
	<!-- Header -->
		<jsp:include page="topnavbar.jsp">
			<jsp:param value="帖子管理" name="title"/>
		</jsp:include>
	<!-- Header -->
	
	<!-- Content -->
	<div class="container" style="margin-top:20px">
	<!-- Left-Navbar -->
		<div style="background-color:white;width:30%;display:inline-block;">
			<jsp:include page="leftnavbar.jsp">
				<jsp:param value="<%=LeftNavbar.PostManager.Post_Manager %>" name="active"/>
			</jsp:include>
		</div>
	<!-- Left-Navbar -->

<%
	Post post = new Post();
	PostDAO pDAO = new PostDAO(post);
	
	ArrayList<Post> posts =  pDAO.getAllPost();
	
%>
		<div style="padding-left:20px;background-color:#fff;border:1px solid #e6e6e6;width:69%;display:inline-block;float:right">
				<table class="table table-striped table-hover">
				<thead>
					<tr>
						<td>ID</td>
						<td>主题</td>
						<td>回复</td>
						<td>发帖人</td>
						<td>操作</td>
					</tr>
				</thead>
				
				<tbody>
				<%
				for(Post p : posts)
				{
					post.setId(p.getId());
					int count = pDAO.commentCount();
				%>
					<tr>
						<td><%=p.getId() %></td>
						<td><%=p.getTitle() %></td>
						<td><%=count %></td>
						<td><%=p.getPostMan() %></td>
						<td><button data-toggle="modal" data-target="#delpostmodal" data-postid="<%=p.getId() %>" class="btn btn-primary">删帖</button></td>
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