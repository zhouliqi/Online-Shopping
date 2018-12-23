package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.User;
import net.sf.json.JSONObject;
import dao.ModifyInfo;;
/**
 * Servlet implementation class ModifyAccount
 */
@WebServlet("/modifyaccount")
public class ModifyAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		// 获取昵称和真实姓名
		String nickname = request.getParameter("nickname");
		String realname = request.getParameter("realname");
		// 获取用户已登录的账户名
		HttpSession session = request.getSession();
		String userName = (String) session.getAttribute("userName");
		User user = ModifyInfo.queryUser(userName);
		
		/*System.out.println("-----------ModifyAccount的servlet-------------");
		System.out.println("昵称  = " + nickname + "\t真实姓名  = " + realname);
		System.out.println("Session.username = " + userName);
		System.out.println("User class: " + user);*/
		
		if ("".equals(nickname) && !"".equals(realname)) {// 
			user.setName(realname);
			ModifyInfo.updateUser(user);
			JSONObject json = new JSONObject();
			json.put("success", "true");
			json.put("message", "更新成功");
			String str = json.toString();
			out.write(str);
		} else if(!"".equals(nickname) && "".equals(realname)) {
			user.setNickname(nickname);
			ModifyInfo.updateUser(user);
			JSONObject json = new JSONObject();
			json.put("success", "true");
			json.put("message", "更新成功");
			String str = json.toString();
			out.write(str);
		} else if (!("".equals(nickname)) && !("".equals(realname))) {
			user.setName(realname);
			user.setNickname(nickname);
			ModifyInfo.updateUser(user);
			JSONObject json = new JSONObject();
			json.put("success", "true");
			json.put("message", "更新成功");
			String str = json.toString();
			out.write(str);
		} else {
			JSONObject json = new JSONObject();
			json.put("success", "false");
			json.put("message", "修改失败");
			String str = json.toString();
			out.write(str);
		}
		
				
	}

}
