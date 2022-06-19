package service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.sql.*;
import entity.User;
import entity.UserDao;
import entity.UserDaoImpl;
import util.DBUtils;

@WebServlet("/login")
public class UserLogin extends HttpServlet{
	private static final long serialVersionUID=1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		User user=new User();
		//获取login.jsp页面提交的账户密码
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		//测试数据
		System.out.println(name+""+password);	
		user.setName(name);
		user.setPassword(password);
		//引入数据交互成
		UserDao dao=new UserDaoImpl();
		User us=dao.login(user);
		//测
		System.out.println(us);
		if(us!=null) {
			resp.sendRedirect("/admin-template/user");
		}else {
			resp.sendRedirect("/admin-template/admin/login.html");
		}
	}
	
}
