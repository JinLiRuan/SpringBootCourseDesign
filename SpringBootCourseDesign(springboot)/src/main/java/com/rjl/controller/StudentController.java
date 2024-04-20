package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.naming.java.javaURLContextFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.rjl.bean.Student;
import com.rjl.service.StudentService;

@Controller
@RequestMapping("/student")
public class StudentController {

	@Resource
	private StudentService studentService;
	
	@Resource
	private Student student; //javajvm spring容器
	

	@RequestMapping("test")
	public void test(HttpServletResponse response) throws IOException {
		PrintWriter out = response.getWriter();
		out.print("error");
		
	}
	
	@RequestMapping("/selectStudent")
	public void selectStudent(int sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("接收到查询学生用户信息页面发送过来的请求！！"+sno);
		
		Student student = studentService.selectStudent(sno);
		if (student != null) {
			out.print(JSON.toJSONString(student));
			System.out.println("==========="+student.toString());
			System.out.println("================"+JSON.toJSONString(student));
		}else {
			out.print("error");
		}
	}
	
	@RequestMapping("/selectAllStudent")
	public void selectAllStudent(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//System.out.println("接收到查询所有学生用户信息页面发送过来的请求！！");
		
		List<Student> students = studentService.selectAllStudents();
		if (students.size() != 0) {
			out.print(JSON.toJSONString(students));
		}else {
			out.print("error");
		}
	}
	
	
	
	
	
}
