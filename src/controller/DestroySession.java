package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class DestroySession
 */
@WebServlet("/destroysession")
public class DestroySession extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		// 获取Session
		HttpSession session = request.getSession();
		//System.out.println("----------进入销毁Session的界面---------------");
		String userName = (String) session.getAttribute("userName");
        if (userName != null) { // 如果用户已登录,返回true
        	session.invalidate();
        	JSONObject json = new JSONObject();
			json.put("success", "true");
			String str = json.toString();
			//System.out.println(str);
			out.write(str);
        } else { // 否则,返回false
        	JSONObject json = new JSONObject();
			json.put("success", "false");
			String str = json.toString();
			out.write(str);
        }
        out.close();
	}

}
