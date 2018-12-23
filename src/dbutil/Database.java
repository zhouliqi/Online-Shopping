package dbutil;

//import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Properties;


/**
 * @author : 周立齐
 * 数据库的连接与关闭
 */

public class Database {
	private static String driver = "com.mysql.jdbc.Driver";;
	private static String url = "jdbc:mysql://localhost:3306/shopping";
	private static String user = "root";
	private static String password = "123";
	//private static Properties prop = new Properties();
	
	// 静态代码块
	/*static {
		InputStream is = Database.class.getClassLoader().getResourceAsStream("dbutil/config.properties");
		try {
			// 加载config.properties配置文件中连接数据库所需要的数据
			prop.load(is);
			driver = prop.getProperty("driver").trim();
			url = prop.getProperty("url").trim();
			user = prop.getProperty("user").trim();
			password = prop.getProperty("password").trim();
			
			// 加载数据库
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/
	
	/**
	 * 连接数据库
	 * @return : Connection对象
	 */
	public static Connection getConnection() {
		Connection conn = null;
		try{
			// 获取连接
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e){
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return conn;
	}
	
	
	/**
	 * 关闭数据库的连接
	 * @param pstmt
	 * @param rs
	 * @param conn
	 */
	public static void allClose(PreparedStatement pstmt, ResultSet rs, Connection conn)
	{
		/**
		 * 关闭PreparedStatement对象所占的资源
		 */
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		
		/**
		 * 关闭ResultSet对象所占的资源
		 */
		try
		{
			if (rs != null)
			{
				rs.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		/**
		 * 关闭Connection对象所占的资源
		 */
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * 关闭增、删、改资源。
	 * @param pstmt
	 * @param conn
	 */
	public static void close(PreparedStatement pstmt, Connection conn)
	{
		try
		{
			if (pstmt != null)
			{
				pstmt.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		try
		{
			if (conn != null)
			{
				conn.close();
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
}