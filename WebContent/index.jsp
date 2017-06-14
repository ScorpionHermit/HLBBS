<%@ page language="java" pageEncoding="GBK" 
	import="java.util.*,
			com.javatrain.bbs.entity.*,
			com.javatrain.bbs.dao.*,
			com.javatrain.bbs.dao.impl.*"%>
<%
request.setCharacterEncoding("GBK");       // �����ַ���

BoardDao boardDao = new BoardDaoImpl();    // �õ����Dao��ʵ��
TopicDao topicDao = new TopicDaoImpl();    // �õ�����Dao��ʵ��
UserDao  userDao  = new UserDaoImpl();     // �õ��û�Dao��ʵ��
Map      mapBoard = boardDao.findBoard();  // ȡ��Map��ʽ�İ����Ϣ
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3c.org/TR/1999/REC-html401-19991224/loose.dtd">
<HTML>
<HEAD>
<TITLE>��ӭ��������ѧԱ��̳</TITLE>
<META http-equiv=Content-Type content="text/html; charset=gbk">
<Link rel="stylesheet" type="text/css" href="style/style.css" />
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

<DIV>

<!--      ����        -->
	<DIV class="t">
		<TABLE cellSpacing="0" cellPadding="0" width="100%">
			<TR class="tr2" align="center">
				<TD colSpan="2">��̳</TD>
				<TD style="WIDTH: 5%;">����</TD>
				<TD style="WIDTH: 25%">��󷢱�</TD>
			</TR>
		<!--       �����       -->
			<%
			List listMainBoard = (List)mapBoard.get(new Integer(0));
			for( int i=0; i<listMainBoard.size(); i++ ) {
				Board mainBoard = ((Board)listMainBoard.get(i));             // ѭ��ȡ�������
			%>
			<TR class="tr3">
				<TD colspan="4"><%=mainBoard.getBoardName() %></TD>
			</TR>
		<!--       �Ӱ��       -->
			<%
				List listSonBoard = (List)mapBoard.get( new Integer(mainBoard.getBoardId()) );
				for( int j=0; j<listSonBoard.size(); j++ ) {
					Board sonBoard  = (Board)listSonBoard.get(j);            // ѭ��ȡ���Ӱ��
					Topic topic     = new Topic();                           // ��󷢱������
					User  user      = new User();                            // ��󷢱�����������
					int   boardId   = sonBoard.getBoardId();
					List  listTopic = topicDao.findListTopic( 1, boardId );  // ȡ�øð�������б�
					if( listTopic!=null && listTopic.size()>0 ) {
						topic = (Topic)listTopic.get(0);                     // ȡ����󷢱������
						user  = userDao.findUser( topic.getUid() );
			}
			%>
			<TR class="tr3">
				<TD width="5%">&nbsp;</TD>
				<TH align="left">
					<IMG src="image/board.gif">
					<A href="list.jsp?page=1&boardId=<%=boardId %>"><%=sonBoard.getBoardName() %></A>
				</TH>
				<TD align="center"><%=topicDao.findCountTopic(boardId) %></TD>
				<TH>
					<SPAN>
						<A href="detail.jsp?page=1&boardId=<%=boardId %>&topicId=<%=topic.getTopicId() %>"><%=topic.getTitle() %></A>
					</SPAN>
					<BR/>
					<SPAN><%=user.getUName() %></SPAN>
					<SPAN class="gray">[ <%=topic.getPublishTime().substring(0,16) %> ]</SPAN>
				</TH>
			</TR>
			<%
				}
			}
			%>
		</TABLE>
	</DIV>
</DIV>

<BR/>
<CENTER class="gray">2007 Beijing Aptech Beida Jade Bird
Information Technology Co.,Ltd ��Ȩ����</CENTER>
</BODY>
</HTML>