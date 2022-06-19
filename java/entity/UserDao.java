package entity;

import java.util.List;

public interface UserDao {
	//登陆声名
	public User login(User user);
	//注册声明
	public boolean register(User user);
	//增删改查
	public List<User> findAll();
	//删除操作
	public void deleteById(Long id);
	//id查询
	public User findById(Long id);
	//新增功能
	public void insert(String name,String password,String phone);
	//修改更新功能
	public void update(Long id,String name,String password,String phone);
}
