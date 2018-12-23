package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ResetDao;
import entity.User;
import net.sf.json.JSONObject;

/**
 * Servlet implementation class ModdiyPassword
 */
@WebServlet("/moddiypassword")
public class ModdiyPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String oldPassword = request.getParameter("current_password");
		String newPassword = request.getParameter("new_password");
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		User user = new User();
		user.setUserName(userName);
		user.setPassword(oldPassword);
		boolean flag = ResetDao.selectUser(user);
		//System.out.println("-----------进入修改密码servlet-------------");
		//System.out.println("旧密码  = " + oldPassword + "\t新密码  = " + newPassword + "\nflag = " + flag);
		if (flag) {
			user.setPassword(newPassword);
			boolean isSuccess = ResetDao.reset(user);
			if (isSuccess) {
				JSONObject json = new JSONObject();
				json.put("success", "true");
				String str = json.toString();
				out.write(str);
			}
		} else {
			JSONObject json = new JSONObject();
			json.put("success", "false");
			json.put("message", "您输入的当前密码不正确");
			String str = json.toString();
			out.write(str);
		}
	}

}
