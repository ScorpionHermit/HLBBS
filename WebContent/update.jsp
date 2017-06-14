<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");

BoardDao boardDao   = new BoardDaoImpl();                                     // 得到版块Dao的实例
ReplyDao replyDao   = new ReplyDaoImpl();                                     // 得到回复Dao的实例

int      boardId    = Integer.parseInt( request.getParameter("boardId") );    // 取得版块id
int      replyId    = Integer.parseInt( request.getParameter("replyId") );    // 取得回复id
//int      topicId    = Integer.parseInt( request.getParameter("topicId") );    // 取得主题id
Board    board      = boardDao.findBoard( boardId );                           // 取得版块信息
Reply    reply      = replyDao.findReply(replyId);                            // 取的回复信息
String   formAction = request.getParameter("tipType").equals("reply")? "manage/doUpdateReply.jsp":"manage/doUpdatePost.jsp";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>青鸟学员论坛--修改帖子</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript">
function check(){
	if(document.updateForm.title.value=="") {
		alert("标题不能为空");
		return false;
	}
	if(document.updateForm.content.value=="") {
		alert("内容不能为空");
		return false;
	}
	if(document.updateForm.content.value.length>1000) {
		alert("长度不能大于1000");
		return false;
	}
}
</script>
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      用户信息、登录、注册        -->
<%
if(session.getAttribute("user") == null){
%>
	<DIV class="h">
		您尚未　<a href="login.jsp">登录</a>
		&nbsp;| &nbsp; <A href="reg.jsp">注册</A> |
	</DIV>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
	<DIV class="h">
		您好：　<%=loginUser.getUName() %>
		&nbsp;| &nbsp; <A href="manage/doLogout.jsp">登出</A> |
	</DIV>

<%
}
%>
<!--      主体        -->
<DIV><BR/>
	<!--      导航        -->
	<DIV>
		&gt;&gt;<B><a href="index.jsp">论坛首页</a></B>&gt;&gt;
		<B><a href="list.jsp?page=1&boardId=<%=boardId %>"><%=board.getBoardName() %></a></B>
	</DIV><BR/>
	<DIV>
		<FORM name="updateForm" onSubmit="return check();" action="<%=formAction%>" method="post">
			<INPUT type="hidden" name="boardId" value="<%=boardId%>"/>
			<INPUT type="hidden" name="replyId" value="<%=replyId%>"/>
			<DIV class="t">
				<TABLE cellSpacing="0" cellPadding="0" align="center">
				 
				    <TR>
					    <TD class="h" colSpan="3"><B>&#20462;&#25913;&#24086;&#23376;</B></TD>
				    </TR>
				
				    <TR class="tr3">
					    <TH width="20%"><B>标题</B></TH>
					    <TH><INPUT name="title" value="<%=reply.getTitle()%>" class="input" style="PADDING-LEFT: 2px; FONT: 14px Tahoma" tabIndex="1" size="60"></TH>
				    </TR>
				 
				    <TR class="tr3">
					    <TH vAlign="top">
					      <DIV><B>内容</B></DIV>
					    </TH>
					    <TH colSpan="2">
					        <DIV>
						        <span><textarea  name="content" style="WIDTH: 500px;" rows="20" cols="90" tabIndex="2" ><%=reply.getContent()%></textarea></span>
						    </DIV>
					      (不能大于:<FONT color="blue">1000</FONT>字)
					    </TH>
					</TR>
				</TABLE>
			</DIV>

			<DIV style="MARGIN: 15px 0px; TEXT-ALIGN: center">
				<INPUT class="btn" tabIndex="3" type="submit" value="修 改"> 
			</DIV>
		</FORM>
	
	</DIV>
	<!--      声明        -->
	<BR/>
</DIV>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd 版权所有</CENTER>

</BODY>
</HTML>
