<%@page import="com.hlbbs.Modal.User"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	request.setCharacterEncoding("utf-8");
%>

<!doctype html public "-//w3c//dtd html 4.01 transitional//en" "http://www.w3c.org/tr/1999/rec-html401-19991224/loose.dtd">
<html>
	<head>
		<title>青鸟学员论坛--注册</title>
		<meta http-equiv=content-type content="text/html; charset=gbk">
		<link rel="stylesheet" type="text/css" href="style/style.css"/>
		<script type="text/javascript">
		<%
			Object obj = request.getAttribute("status");
			if(obj != null)
			{
		%>
			alert("账号已存在!");
		<%
			request.removeAttribute("status");
			}
		%>
		function check() 
		{
 			if(document.regForm.name.value=="")
 			{
    			alert("用户名不能为空");
    			return false;
 			}
 			else if(document.regForm.email.value=="")
 			{
    			alert("邮箱不能为空");
    			return false;
 			}
 			else if(document.regForm.password.value=="")
 			{
    			alert("密码不能为空");
    			return false;
 			}
 			else if(document.regForm.password.value != document.regForm.repetitivepassword.value)
 			{
    			alert("2次密码不一样");
    			return false;
 			}
		}
		</script>
	</head>
	<body>
		<div>
			<img style="width:250px" src="image/logo.png">
		</div>
<!--      用户信息、登录、注册        -->
<%
	if(session.getAttribute("user") == null){
%>
		<div class="h">
			您尚未　<a href="login.jsp">登录</a>
			&nbsp;| &nbsp; <a href="reg.jsp">注册</a> |
		</div>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
		<div class="h">
			您好：　<%=loginUser.getName() %>
			&nbsp;| &nbsp; <a href="manage/dologout.jsp">登出</a> |
		</div>

<%
}
%>

		<br/>
<!--      导航        -->
		<div>
			&gt;&gt;<b><a href="index.jsp">论坛首页</a></b>
		</div>
<!--      用户注册表单        -->
		<div  class="t" style="margin-top: 15px" align="center">
			<form name="regform" onsubmit="return check()" action="RegisterControl" method="post">
				<div style="width:400px">
				<table>
					<tbody>
						<tr>
							<td><span>用户名 </span></td>
							<td><input class="input" tabindex="1" type="text" maxlength="20" size="35" name="name"></td>
						</tr>
						<tr>
							<td><span>邮箱</span></td>
							<td><input class="input" tabindex="2" type="text" maxlength="20" size="35" name="email"></td>
						</tr>
						<tr>
							<td><span>密码</span></td> 
							<td><input class="input" tabindex="3" type="password" maxlength="20" size="40" name="password"></td>
						</tr>
						<tr>
							<td><span>重复密码 </span></td>
							<td><input class="input" tabindex="4" type="password" maxlength="20" size="40" name="repetitivepassword"></td>
						</tr>
						<tr>
							<td><span>性别 </span></td>
							<td>
								<input type="radio" name="gender" value="女">女
								<input type="radio" name="gender" value="男" checked="checked" />男
							</td>
						</tr>
					</tbody>				
				</table>
				</div>
				<span>请选择头像</span>
				<br/>
				<img src="image/head/1.gif"/><input type="radio" name="head" value="1.gif" checked="checked">
				<img src="image/head/2.gif"/><input type="radio" name="head" value="2.gif">
				<img src="image/head/3.gif"/><input type="radio" name="head" value="3.gif">
				<img src="image/head/4.gif"/><input type="radio" name="head" value="4.gif">
				<img src="image/head/5.gif"/><input type="radio" name="head" value="5.gif">
				<br/>
				<img src="image/head/6.gif"/><input type="radio" name="head" value="6.gif">
				<img src="image/head/7.gif"/><input type="radio" name="head" value="7.gif">
				<img src="image/head/8.gif"/><input type="radio" name="head" value="8.gif">
				<img src="image/head/9.gif"/><input type="radio" name="head" value="9.gif">
				<img src="image/head/10.gif"/><input type="radio" name="head" value="10.gif">
				<br/>
				<img src="image/head/11.gif"/><input type="radio" name="head" value="11.gif">
				<img src="image/head/12.gif"/><input type="radio" name="head" value="12.gif">
				<img src="image/head/13.gif"/><input type="radio" name="head" value="13.gif">
				<img src="image/head/14.gif"/><input type="radio" name="head" value="14.gif">
				<img src="image/head/15.gif"/><input type="radio" name="head" value="15.gif">
				<br/>
					<input class="btn" tabindex="4" type="submit" value="注 册">
			</form>
		</div>
<!--      声明        -->
		<br>
		<center class="gray">2017-安徽工业大学ZXZ6工作室-版权所有</center>
	</body>
</html>
