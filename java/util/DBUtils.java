package util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import entity.User;
/**数据库的工具类，负责连接数据库*/
public class DBUtils {
	/**连接数据库
	 * @return 连接失败返回null,否则返回java.sql.Connection对象*/
	public static Connection connect() {
		try {
			Class.forName("com.mysql.jdbc.Driver");//根据实际的数据库驱动程序版本写驱动程序名
			Connection con=DriverManager
					      .getConnection("jdbc:mysql://localhost:3306/test"
					      ,"root"
					      ,"root");
			return con;
		}catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	public static void main(String[] args) {
		System.out.println(DBUtils.connect());
	}
	//关闭数据库方法
	public static void release(Connection con,PreparedStatement ps,ResultSet rs) {
		if(rs!=null) {//关闭资源避免异常
			try {
				rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(ps!=null) {
			try {
				ps.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		if(con!=null) {
			try {
				con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}
	//同意增删改查方法
	public static boolean addUpdateDelete(String sql,Object[] arr) {
		Connection con=null;
		PreparedStatement ps=null;
		try {
			con=DBUtils.connect();//链接数据库
			ps=con.prepareStatement(sql);//预编译
			//设置值
			if(arr!= null && arr.length != 0) {
				for(int i=0;i<arr.length;i++) {
					ps.setObject(i+1,arr[i]);
				}
			}
			int count = ps.executeUpdate();//第四步：执行sql语句
			if(count>0) {
				return true;
			}else {
				return false;
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return false;
	}
}
