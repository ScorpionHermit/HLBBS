<%@ page language="java" pageEncoding="GBK"
	import="com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");

BoardDao boardDao   = new BoardDaoImpl();                                     // �õ����Dao��ʵ��
ReplyDao replyDao   = new ReplyDaoImpl();                                     // �õ��ظ�Dao��ʵ��

int      boardId    = Integer.parseInt( request.getParameter("boardId") );    // ȡ�ð��id
int      replyId    = Integer.parseInt( request.getParameter("replyId") );    // ȡ�ûظ�id
//int      topicId    = Integer.parseInt( request.getParameter("topicId") );    // ȡ������id
Board    board      = boardDao.findBoard( boardId );                           // ȡ�ð����Ϣ
Reply    reply      = replyDao.findReply(replyId);                            // ȡ�Ļظ���Ϣ
String   formAction = request.getParameter("tipType").equals("reply")? "manage/doUpdateReply.jsp":"manage/doUpdatePost.jsp";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>����ѧԱ��̳--�޸�����</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
<script type="text/javascript">
function check(){
	if(document.updateForm.title.value=="") {
		alert("���ⲻ��Ϊ��");
		return false;
	}
	if(document.updateForm.content.value=="") {
		alert("���ݲ���Ϊ��");
		return false;
	}
	if(document.updateForm.content.value.length>1000) {
		alert("���Ȳ��ܴ���1000");
		return false;
	}
}
</script>
</HEAD>

<BODY>
<DIV>
	<IMG src="image/logo.gif">
</DIV>
<!--      �û���Ϣ����¼��ע��        -->
<%
if(session.getAttribute("user") == null){
%>
	<DIV class="h">
		����δ��<a href="login.jsp">��¼</a>
		&nbsp;| &nbsp; <A href="reg.jsp">ע��</A> |
	</DIV>
<%
} else {
	User loginUser = (User)session.getAttribute("user");
%>
	<DIV class="h">
		���ã���<%=loginUser.getUName() %>
		&nbsp;| &nbsp; <A href="manage/doLogout.jsp">�ǳ�</A> |
	</DIV>

<%
}
%>
<!--      ����        -->
<DIV><BR/>
	<!--      ����        -->
	<DIV>
		&gt;&gt;<B><a href="index.jsp">��̳��ҳ</a></B>&gt;&gt;
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
					    <TH width="20%"><B>����</B></TH>
					    <TH><INPUT name="title" value="<%=reply.getTitle()%>" class="input" style="PADDING-LEFT: 2px; FONT: 14px Tahoma" tabIndex="1" size="60"></TH>
				    </TR>
				 
				    <TR class="tr3">
					    <TH vAlign="top">
					      <DIV><B>����</B></DIV>
					    </TH>
					    <TH colSpan="2">
					        <DIV>
						        <span><textarea  name="content" style="WIDTH: 500px;" rows="20" cols="90" tabIndex="2" ><%=reply.getContent()%></textarea></span>
						    </DIV>
					      (���ܴ���:<FONT color="blue">1000</FONT>��)
					    </TH>
					</TR>
				</TABLE>
			</DIV>

			<DIV style="MARGIN: 15px 0px; TEXT-ALIGN: center">
				<INPUT class="btn" tabIndex="3" type="submit" value="�� ��"> 
			</DIV>
		</FORM>
	
	</DIV>
	<!--      ����        -->
	<BR/>
</DIV>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd ��Ȩ����</CENTER>

</BODY>
</HTML>
