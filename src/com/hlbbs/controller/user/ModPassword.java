package com.hlbbs.controller.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hlbbs.bean.UserBean;
import com.hlbbs.service.UserService;

/**
 * Servlet implementation class ModPassword
 */
@WebServlet(description = "修改密码", urlPatterns = { "/ModPassword" })
public class ModPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ModPassword() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 设置响应内容类型
		response.setContentType("text/html;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		String username = (String) session.getAttribute("username");
		UserBean userBean = new UserBean();
		UserService userService = new UserService();
		
		// 获取前台输入
		String oldPassword = request.getParameter("oldpassword");
		String newPassword = request.getParameter("newpassword");
		
		// 验证旧密码是否正确
		boolean isExist = userService.queryUser(username, oldPassword);
		
		if (isExist) {	// 如果正确则更新密码
			userBean.setPassword(request.getParameter(newPassword));
			
			// 更新密码
			boolean isSuccess = userService.modPassword(userBean.getPassword(), username);
			
			if (isSuccess) {	// 如果修改成功则退出登录并跳转到登录界面
				out.println("<script>alert('密码修改成功！');window.location.href='login.jsp';</script>");
			} else {	// 修改失败
				out.println("<script>alert('密码修改失败！');</script>");
			}
		} else {	// 如果错误就提示旧密码错误
			out.println("<script>alert('旧密码错误！');</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
