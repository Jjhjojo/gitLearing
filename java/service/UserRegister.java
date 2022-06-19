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


@WebServlet("/register")
public class UserRegister extends HttpServlet{
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
		//获取register.jsp页面提交的账户密码
		String name=req.getParameter("name");
		String password=req.getParameter("password");
		String phone=req.getParameter("phone");
		//测试数据
		System.out.println(name+""+password+""+phone);
		user.setName(name);
		user.setPassword(password);
		user.setPhone(phone);
		//引入数据交互成
		UserDao dao=new UserDaoImpl();
		boolean flag=dao.register(user);
		//测试返回值
		System.out.println(flag);
		if(flag) {
			resp.sendRedirect("/admin-template/admin/login.html");
		}else {
			resp.sendRedirect("/admin-template/admin/register.html");
		}
	}
	
}
