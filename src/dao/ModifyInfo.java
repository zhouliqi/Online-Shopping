package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dbutil.Database;
import entity.User;

/**
 * 
 * @author asus
 *
 */
public class ModifyInfo {
	/**
	 * 根据用户名找到这个用户
	 * @param username 用户名 ,当用户登录之后可以通过Session来获得
	 * @return
	 */
	public static User queryUser(String username) {
		User user = new User();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = Database.getConnection();
		String sql = "select * from user where username = '" + username + "'";
		//System.out.println("------queryUser--------");
		try {
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				user.setUserName(rs.getString("username"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				//user.setNickname(rs.getString("nickname"));
				user.setPhone(rs.getString("phone"));
				user.setAddress(rs.getString("address"));
			} else {
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.allClose(pstmt, rs, conn);
		}
		return user;
	}
	
	
	/**
	 * 更新用户的真实姓名或昵称
	 * @param user
	 * @return
	 */
	public static void updateUser(User user) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		conn = Database.getConnection();
		String sql = "update user set name='" + user.getName() + "',nickname='" + user.getNickname() +
					 "' where username = '" + user.getUserName() + "'";
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			Database.allClose(pstmt, rs, conn);
		}
	}
	
	
}
