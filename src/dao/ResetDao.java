package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbutil.Database;
import entity.User;

/**
 * 
 * 处理重置密码时向数据库的操作
 * @author 周立齐
 *
 */
public class ResetDao {
	/**
	 * 根据用户名和手机号
	 * 验证某一用户是否在数据库中,在的话返回true,否则返回false
	 * @param user 某一用户
	 * @return
	 */
	public static boolean verify(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = Database.getConnection(); // 获取连接
		String sql = "select * from user where username=? and phone=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPhone());
			rs = pstmt.executeQuery();
			if (rs.next()) { // 如果查询的结果有这条记录,返回true;否则返回false
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
	/**
	 * 根据用户名和密码
	 * 验证某一用户是否在数据库中,在的话返回true,否则返回false
	 * @param user 某一用户
	 * @return
	 */
	public static boolean selectUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = Database.getConnection(); // 获取连接
		//System.out.println("User : " + user.getUserName() + "\t" + user.getPassword());
		String sql = "select * from user where username=? and password=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getPassword());
			rs = pstmt.executeQuery();
			if (rs.next()) { // 如果查询的结果有这条记录,返回true;否则返回false
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
	/**
	 * 修改用户的密码
	 * @param user
	 * @return
	 */
	public static boolean reset(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int flag = 0;
		conn = Database.getConnection(); // 获取连接
		String sql = "update user set password=? where username=?";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getPassword());
			pstmt.setString(2, user.getUserName());
			flag = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库资源
			Database.close(pstmt, conn);
		}
		if(flag != 0) {
			return true;
		} else {
			return false;
		}
	}
}
