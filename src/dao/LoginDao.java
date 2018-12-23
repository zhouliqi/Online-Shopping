package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbutil.Database;
import entity.User;

/**
 * 
 * 处理登录时向数据库查询
 * @author 周立齐
 *
 */
public class LoginDao {
	/**
	 * 根据用户名和密码
	 * 验证某一用户是否在数据库中,在的话返回true,否则返回false
	 * @param user 某一用户
	 * @return
	 */
	public static boolean verify(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = Database.getConnection();
		String sql = "select * from user where username=? and password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			// 如果查询的结果有这条记录,返回true;否则返回false
			if (rs.next()) {
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			Database.allClose(pstmt, rs, conn);
		}
		
		return false;
	}
}
