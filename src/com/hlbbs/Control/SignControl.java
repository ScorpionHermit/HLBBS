package com.hlbbs.Control;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hlbbs.DAO.SignDAO;
import com.hlbbs.DAO.UserDAO;
import com.hlbbs.Modal.Sign;
import com.hlbbs.Modal.User;

/**
 * Servlet implementation class SignControl
 */
@WebServlet("/SignControl")
public class SignControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SignControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		boolean isSign = false;
		Sign sign = new Sign();
		User user = (User)request.getSession().getAttribute("user");
		sign.setUserId(user.getId());
		
		SignDAO sd = new SignDAO(sign);
		sd.findSign();
		if(sd.sign.getFinalSignTime()==null)
		{
        sd.addSign();
        
        user.setIntegral(user.getIntegral()+1); 
		UserDAO ud = new UserDAO(user);
		ud.updateUserIntegral();
		isSign = true;
		}
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        java.util.Date now = new java.sql.Timestamp(new Date().getTime());
        java.util.Date date = new Date();
		try {
			date = df.parse(sd.sign.getFinalSignTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        long l=now.getTime()-date.getTime();
        long day=l/(24*60*60*1000);
        long hour=(l/(60*60*1000)-day*24);
        long min=((l/(60*1000))-day*24*60-hour*60);
        long s=(l/1000-day*24*60*60-hour*60*60-min*60);
        System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
        
        if(day>=1){
        	user.setIntegral(user.getIntegral()+1); 
    		UserDAO ud = new UserDAO(user);
    		ud.updateUserIntegral();
    		
    		sd.updateSign();
    		isSign = true;
    		
        }
        response.sendRedirect("index.jsp?isSign="+isSign+"");
		
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
