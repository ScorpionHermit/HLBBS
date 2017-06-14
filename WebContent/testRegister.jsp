<%@page contentType="text/html; charset=UTF-8" pageEncoding="utf-8"%>
<%@page language="java" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>用户注册提交信息</title>
<style type="text/css">
body{
	font-family: Verdana, Tahoma, "宋体";
	font-size: 18px;
	width:70%;
	margin: 20px auto;
}
</style>
</head>
<body>
<%
request.setCharacterEncoding("utf-8");
response.setHeader("iso-8859-1","utf-8");
response.setCharacterEncoding("utf-8");
%>
<%
String username = request.getParameter("username"); 
String password = request.getParameter("password");
String sex = request.getParameter("sex");
String[] hobbyArray = request.getParameterValues("hobby");
String major = request.getParameter("major");
String remark = request.getParameter("remark");
String authimg = request.getParameter("authimg");
if(authimg.equalsIgnoreCase((String)session.getAttribute("authCode"))){
	out.print("你的注册信息是:"+"<br>");
	out.print("用户名："+username+"<br>");
	out.print("密码:"+password+"<br>");
	out.print("性别:"+sex+"<br>");
	String hobbyString ="";
	out.print("hobby length: "+hobbyArray.length);
	for(int i = 0;i<hobbyArray.length;i++){
		if(i!=0)
			hobbyString+=","+hobbyArray[i];
		else
			hobbyString+=hobbyArray[i];
	}
	out.print("爱好:"+hobbyString+"<br>");
	out.print("院系:"+major+"<br>");
	out.print("备注:"+remark+"<br>");
	out.print("验证码:"+authimg+"<br>");
}else{
	out.print("您输入的验证码不匹配，请重新输入.");
}
%>

</body>
</html>