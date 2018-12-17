package entity;

/**
 * 
 * 用户实体
 * @author : 周立齐
 * 2018/12/16
 * 
 */
public class User {
	private String user; 		// 用户名
	private String name;		// 用户姓名
	private String password;	// 登录密码
	private String phone;		// 用户手机号码
	private String address;		// 用户地址
	
	public User() {
		super();
	}
	
	/**
	 * 以下是User属性的getter()和setter()方法
	 * @return
	 */
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
}
