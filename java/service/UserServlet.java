package service;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import entity.User;
import entity.UserDaoImpl;

@WebServlet("/user")
public class UserServlet extends HttpServlet{
	//调用userdaoimpl增删改查方法
	private UserDaoImpl userdaoimpl=new UserDaoImpl();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		resp.setContentType("text/html;charset=utf-8");
		//判断前端标记，以此执行增删改查操作
		String method=req.getParameter("method");
		if(method==null) {
			method="findAll";
		}
		switch(method) {
		case "findAll"://查询所有数据
			List<User> list=userdaoimpl.findAll();//调用userdaoimpl中的findall方法
			req.setAttribute("list", list);//存入req中
			req.getRequestDispatcher("/admin/user.jsp").forward(req, resp);
		case "delete"://删除
			String idUser=req.getParameter("id");
			System.out.println(idUser);
			Long id=Long.parseLong(idUser);
			userdaoimpl.deleteById(id);
			resp.sendRedirect("/admin-template/user");
            break;
		case "findById"://查询
			idUser=req.getParameter("id");
			id=Long.parseLong(idUser);
			req.setAttribute("user", userdaoimpl.findById(id));
			req.getRequestDispatcher("search.jsp").forward(req, resp);
			break;
		case "insert"://增加
			req.getRequestDispatcher("/admin-template/user").forward(req, resp);
		}
	}

//	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		req.setCharacterEncoding("utf-8");
		String method=req.getParameter("method");
		switch(method){
		case "insert"://增加
			String name=req.getParameter("name");
			String password=req.getParameter("password");
			String phone=req.getParameter("phone");
			userdaoimpl.insert(name, password, phone);
			break;
		case "update"://修改
			String idUser=req.getParameter("id");
			name=req.getParameter("name");
			password=req.getParameter("password");
			phone=req.getParameter("phone");
			Long id=Long.parseLong(idUser);
			userdaoimpl.update(id, name, password, phone);
			break;
		}
		resp.sendRedirect("/admin-template/user");
	}
}
