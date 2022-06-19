package entity;

public class User {
	public Long id; //编号
	public String name;//用户名
    public String password;//密码
    public String phone;//手机号

    public User(){};
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
    public User(Long id,String name, String password,String phone) {
        this.name = name;
        this.password = password;
        this.id = id;
        this.phone = phone;
    }
    
    
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", password=" + password + ", phone=" + phone + "]";
	}
    
}
