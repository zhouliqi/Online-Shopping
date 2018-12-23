package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.LoginDao;
import entity.User;
import net.sf.json.JSONObject;

/**
 * 登录界面的处理
 * Servlet implementation class HandleLogin
 */
@WebServlet("/login")
public class HandleLogin extends HttpServlet {
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
		
		// 验证用户的信息是否正确,正确的话flag为true
		boolean flag = LoginDao.verify(u);
		if (flag) { // 设置success为true,传给ajax
			//创建session对象
			HttpSession session = request.getSession();
			//把用户数据保存在session域对象中
            session.setAttribute("userName", userName);

			JSONObject json = new JSONObject();
			json.put("success", "true");
			String str = json.toString();
			out.write(str);
		} else { // 设置success为false,写提示信息message,传给ajax
			JSONObject json = new JSONObject();
			json.put("success", "false");
			json.put("message", "你输入的用户名或密码有误,请重新输入");
			String str = json.toString();
			out.write(str);
		}
	}

}
