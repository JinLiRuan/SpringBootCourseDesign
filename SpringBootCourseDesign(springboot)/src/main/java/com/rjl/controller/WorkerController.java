package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSON;
import com.rjl.bean.Student;
import com.rjl.bean.Worker;
import com.rjl.service.StudentService;
import com.rjl.service.WorkerService;

@Controller
@RequestMapping("/worker")
public class WorkerController {

	@Resource
	private WorkerService workerService;

	
	@RequestMapping("/selectWorker")
	public void selectWorker(String wno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("接收到查询工人用户信息页面发送过来的请求！！"+wno);
		
		Worker worker  = workerService.selectWorker(wno);
		if (worker != null) {
			out.print(JSON.toJSONString(worker));
		}else {
			out.print("error");
		}
	}
	
	@RequestMapping("/selectAllWorker")
	public void selectAllStudent(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		System.out.println("接收到查询所有工人用户信息页面发送过来的请求！！");
		
		List<Worker> workers = workerService.selectAllWorker();
		if (workers.size() != 0) {
			out.print(JSON.toJSONString(workers));
		}else {
			out.print("error");
		}
	}
	
	
	
	
	
}
