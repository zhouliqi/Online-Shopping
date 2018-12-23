package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dbutil.*;

import entity.User;

/**
 * 
 * @author 周立齐
 * 验证当前注册的用户是否在数据库中,如果用户已存在,则返回false;否则返回true
 */
public class RegisterDao {
	public boolean verify(User user) {

		Connection conn = null;
		conn = Database.getConnection();
		// 找到这一条记录
		String sql = "select * from user where username=?";
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			rs = pstmt.executeQuery();
			if (rs.next() == false) { // 如果当前注册的用户名还没有被注册，则返回true，验证通过
				return true;
			} else { // 否则返回false
				return false;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			Database.allClose(pstmt, rs, conn);
		}
		return false;
	}
	
	/**
	 * 如果用户填写的信息符合要求,则将这一条记录添加到数据库中
	 * @param u
	 * @return
	 */
	public boolean addUser(User user) {
		if ("".equals(user.getUserName()) || "".equals(user.getName()) || "".equals(user.getPassword())) {
			return false;
		}
		Connection conn = Database.getConnection();
		int flag = 0;
		// 找到这一条记录
		String sql = "insert into user(username, name, password, phone, address) values(?, ?, ?, ?, ?)";
		PreparedStatement pstmt = null;
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUserName());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getPassword());
			pstmt.setString(4, user.getPhone());
			pstmt.setString(5, user.getAddress());
			flag = pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭数据库的资源
			Database.close(pstmt, conn);
		}
		if(flag != 0) {
			return true;
		} else {
			return false;
		}
	}
}
