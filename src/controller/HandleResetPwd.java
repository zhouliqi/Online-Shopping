package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ResetDao;
import entity.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class HandleResetPwd
 */
@WebServlet("/resetpwd")
public class HandleResetPwd extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		// 将ajax传过来的参数封装成对象
		String userName = "";
		String password = "";
		userName = request.getParameter("userName");
		password = request.getParameter("password");
		User u = new User();
		u.setUserName(userName);
		u.setPassword(password);
		
		// 修改用户的密码,正确的话flag为true
		boolean flag = ResetDao.reset(u);
		
		if (flag) { // 设置success为true,传给ajax
			JSONObject json = new JSONObject();
			json.put("success", "true");
			String str = json.toString();
			out.write(str);
		} else { // 设置success为false,写提示信息message,传给ajax
			JSONObject json = new JSONObject();
			json.put("success", "false");
			json.put("message", "请输入正确的用户名");
			String str = json.toString();
			out.write(str);
		}
	}

}
