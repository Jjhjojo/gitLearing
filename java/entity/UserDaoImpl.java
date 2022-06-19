package entity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

import util.DBUtils;
public class UserDaoImpl implements UserDao{
	@Override
	//登录
	public User login(User user) {
		// TODO Auto-generated method stub
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBUtils.connect();//获取数据库链接
			//sql语句
			String sql="select * from ad_user where name=? and password=?";
			ps=con.prepareStatement(sql);//预编译
			//设置值
			ps.setString(1,user.getName());
			ps.setString(2,user.getPassword());
			//执行sql语句
			rs=ps.executeQuery();
			User users=null;
			if(rs.next()) {
				users=new User();
				//从数据库获取值设置到实体类的setter方法中
				users.setId(rs.getLong("id"));
				users.setName(rs.getString("name"));
				users.setPassword(rs.getString("password"));
				users.setPhone(rs.getString("phone"));
				return user;
			}else {
				return null;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return null;
	}
//插入方法注册
	@Override
	public boolean register(User user) {
		// TODO Auto-generated method stub
		String sql="insert into ad_user values(0,?,?,?)";
		List<Object> list=new ArrayList<Object>();
		list.add(user.getName());
		list.add(user.getPassword());
		list.add(user.getPhone());
		boolean flag=DBUtils.addUpdateDelete(sql,list.toArray());
		if(flag) {
			return true;
		}else {
			return false;
		}
	}
//增删改查

	public List<User> findAll(){
		List<User> list=new ArrayList<>();
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			con=DBUtils.connect();//连接数据库
			String sql="select * from ad_user";//查询语句
			ps=con.prepareStatement(sql);
			rs=ps.executeQuery();
			while(rs.next()) {
				//从rs拿出属性数据
				Long id=rs.getLong(1);
				String name=rs.getString(2);
				String password=rs.getString(3);
				String phone=rs.getString(4);
				User user=new User(id,name,password,phone);
				list.add(user);//多条数据放到集合中
			}
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(con,ps,rs);
		}
		return list;
	}
	//新增功能
	public void insert(String name,String password,String phone) {
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		try {
			connection=DBUtils.connect();
			String sql="insert into ad_user(name,password,phone) values (?,?,?)";
			preparedStatement=connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, phone);
			preparedStatement.executeUpdate();
//			connection.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DBUtils.release(connection, preparedStatement, null);
		}
	}
	//删除操作
	public void deleteById(Long id) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBUtils.connect();
			String sql="delete from ad_user where id=?";
			ps=con.prepareStatement(sql);
			ps.setLong(1, id);
			ps.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(con,ps,null);
		}
		
	}
	//id查询
	public User findById(Long id) {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		User user=null;
		try {
			con=DBUtils.connect();
			String sql="select * from ad_user where id=?";
			ps=con.prepareStatement(sql);
			ps.setLong(1, id);
			rs=ps.executeQuery();
			while(rs.next()) {
				Long id2=rs.getLong(1);
				String name=rs.getString(2);
				String password=rs.getString(3);
				String phone=rs.getString(4);
				user=new User(id,name,password,phone);
			}
			con.close();
			ps.close();
			rs.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(con, ps, rs);
		}
		return user;
	}
	//修改更新功能
	public void update(Long id,String name,String password,String phone) {
		Connection con=null;
		PreparedStatement stmt=null;
		try {
			con=DBUtils.connect();
			String sql="update ad_user set name=?, password=?, phone=? where id=?";
			stmt=con.prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, password);
			stmt.setString(3, phone);
			stmt.setLong(4, id);
			stmt.executeUpdate();
			con.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DBUtils.release(con, stmt, null);
		}
	}

}
