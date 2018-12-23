package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import entity.User;
import net.sf.json.JSONObject;
import dao.RegisterDao;

/**
 * Servlet implementation class HandleRegister
 * @author 周立齐
 */
@WebServlet("/register")
public class HandleRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		//response.setContentType("application/x-json;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		String userName = ""; 		
		String name = "";		
		String password = "";	
		String checkPassword = "";
		String phone = "";		
		String address = "";
		
		userName = request.getParameter("userName");
		name = request.getParameter("name");
		password = request.getParameter("password");
		checkPassword = request.getParameter("checkPassword");
		phone = request.getParameter("phone");
		address = request.getParameter("address");
		
		User u = new User();
		
		u.setUserName(userName);
		u.setName(name);
		u.setPassword(password);
		u.setPhone(phone);
		u.setAddress(address);
		
		// 在数据库中检查用户是否已存在
		RegisterDao dao = new RegisterDao();
		boolean flag = dao.verify(u);
		
		if (!flag) { // 如果用户已存在，传给Ajax，显示提示信息
			JSONObject json = new JSONObject();
			json.put("success", "false");
			json.put("message", "该用户已存在");
			String str = json.toString();
			out.write(str);
			//String str = "{\"username\":\"learning\", \"password\": \"123\"}";
			//out.print("{\"success\": \"false\", \"message\": \"该用户已存在\"}");
		} else {
			// 检查输入的密码是否一致
			if (!checkPassword.equals(password) || (password.length() < 6)) { // 如果前后输入的密码不一致，则显示提示信息
				JSONObject json = new JSONObject();
				json.put("success", "false");
				json.put("message", "您输入的密码不一致");
				String str = json.toString();
				out.write(str);
			} else {
				// 匹配手机号码是否是11位数字
				String regex = "[\\d]{11}";
				if (phone != null && phone.length() > 0 && !phone.matches(regex)) {
					JSONObject json = new JSONObject();
					json.put("success", "false");
					json.put("message", "请正确填写11位手机号码");
					String str = json.toString();
					out.write(str);
				} else {
					flag = dao.addUser(u);
					if (flag) {// 如果信息填写正确无误,则注册成功
						JSONObject json = new JSONObject();
						json.put("success", "true");
						String str = json.toString();
						out.write(str);
					} else {
						JSONObject json = new JSONObject();
						json.put("success", "false");
						json.put("message", "注册失败");
						String str = json.toString();
						out.write(str);
					}
				}
			}
			
		}
		
		out.close();
	}

}
