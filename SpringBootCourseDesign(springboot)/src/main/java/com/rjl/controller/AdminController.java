package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.rjl.bean.Admin;
import com.rjl.bean.Student;
import com.rjl.bean.Worker;
import com.rjl.service.AdminService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Resource
	private AdminService adminrService;

	@Resource
	private Admin admin;
	
	@RequestMapping("/selectAdmin")
	public void selectAdmin(String login_name,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("接收到查询管理员用户信息页面发送过来的请求！！"+login_name);
		
		admin  = adminrService.selectAdminByAno(login_name);
		if (admin != null) {
			out.print(JSON.toJSONString(admin));
		}else {
			out.print("error");
		}
	}
	
	
	
	@RequestMapping("/updatePassword")
	public void updatePassword(String password,String loginName,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("接收到更改管理员用户密码页面发送过来的请求！！"+loginName+password);
		
		int num = adminrService.updatePassword(password, loginName);
	
		if (num == 1) {
			out.print("ok");
		}else {
			out.print("error");
		}
		
		
	}
	
	
	
}
