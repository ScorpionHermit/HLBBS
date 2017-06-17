<%@page import="com.hlbbs.Modal.Comment"%>
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
	if (session.getAttribute("user") == null) {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	request.setCharacterEncoding("UTF-8");

	int id = Integer.parseInt(request.getParameter("boardid")); // 取得版块id
	Section section = new Section();
	section.setID(id);// 取得版块信息
	SectionDAO sectionDAO = new SectionDAO(section);
	sectionDAO.findBoardByID();// 得到版块Dao的实例

	String topicId = request.getParameter("topicid") == null ? "" : request.getParameter("topicid");
	String action = request.getParameter("post").equals("newreply") ? "回复帖子" : "发布帖子";
String action_update =request.getParameter("post");
	String formAction = request.getParameter("post").contains("reply") ? "ReplyControl" : "PostControl";
	
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>青鸟学员论坛--<%=action%></TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript">
function check(){
	if(document.postForm.title.value=="") {
		alert("标题不能为空");
		return false;
	}
	if(document.postForm.content.value=="") {
		alert("内容不能为空");
		return false;
	}
	if(document.postForm.content.value.length>1000) {
		alert("长度不能大于1000");
		return false;
	}
	var action="<%=formAction%>";
	var action_update="<%=action_update%>";
		document.postForm.action = action;
		if (action == "ReplyControl") {
			if(action_update!="updatereply")
				{
			document.postForm.option.value = "newreply";
				}
			else
				{
				document.postForm.option.value ="update";
				}
		}
	}
	function load()
	{
		<%
		if(action_update.equals("updatereply"))
		{
			Comment com =(Comment)request.getAttribute("updatereply");
			%>
			var form =document.postForm;
			form.replyid.value="<%=com.getId()%>";
			form.title.value="<%=com.getTitle()%>";
			form.content.value="<%=com.getContent()%>";
			<%
		}
		%>
	}
</script>
</HEAD>

<BODY onload="load()">
	<DIV>
		<IMG  style="width:250px" src="image/logo.png">
	</DIV>
	<!--      用户信息、登录、注册        -->
	<%
		if (request.getSession().getAttribute("user") == null) {
	%>
	<DIV class="h">
		您尚未 <a href="login.jsp">登录</a> &nbsp;| &nbsp; <A href="reg.jsp">注册</A>
		|
	</DIV>
	<%
		} else {
			User loginUser = (User) session.getAttribute("user");
	%>
	<DIV class="h">
		您好：
		<%=loginUser.getName()%>
		&nbsp;| &nbsp; <a href="LoginoutControl">登出</A> |
	</DIV>

	<%
		}
	%>
	<!--      主体        -->
	<DIV>
		<BR />
		<!--      导航        -->
		<DIV>
			&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt; <B><a
				href="list.jsp?page=1&boardid=<%=id%>"><%=section.getNvcSectionName()%></a></B>
		</DIV>
		<BR />
		<DIV>
			<FORM name="postForm" onSubmit="return check()" action=""
				method="POST">
				<INPUT type="hidden" name="boardid" value="<%=id%>" /> <INPUT
					type="hidden" name="topicid" value="<%=topicId%>" /> <input
					type="hidden" name="option" value="">
					<input type="hidden" name="replyid" value="">
				<DIV class="t">
					<TABLE cellSpacing="0" cellPadding="0" align="center">
						<TR>
							<TD class="h" colSpan="3"><B><%=action%></B></TD>
						</TR>

						<TR class="tr3">
							<TH width="20%"><B>标题</B></TH>
							<TH><INPUT class="input"
								style="PADDING-LEFT: 2px; FONT: 14px Tahoma" tabIndex="1"
								size="60" name="title"></TH>
						</TR>

						<TR class="tr3">
							<TH vAlign=top>
								<DIV>
									<B>内容</B>
								</DIV>
							</TH>
							<TH colSpan=2>
								<DIV>
									<span><textarea style="WIDTH: 500px;" name="content"
											rows="20" cols="90" tabIndex="2"></textarea></span>
								</DIV> (不能大于:<FONT color="blue">1000</FONT>字)
							</TH>
						</TR>
					</TABLE>
				</DIV>

				<DIV style="MARGIN: 15px 0px; TEXT-ALIGN: center">
					<INPUT class="btn" tabIndex="3" type="submit" value="提 交">
					<INPUT class="btn" tabIndex="4" type="reset" value="重 置">
				</DIV>
			</FORM>
		</DIV>
	</DIV>
	<!--      声明        -->
	<BR />
	<CENTER class="gray">2017-安徽工业大学zxz6工作室-版权所有</CENTER>

</BODY>
</HTML>
