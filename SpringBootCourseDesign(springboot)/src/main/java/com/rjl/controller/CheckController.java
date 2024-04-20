package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.rjl.bean.HSCheckResult;
import com.rjl.bean.Student;
import com.rjl.bean.SubmitData;
import com.rjl.bean.Worker;
import com.rjl.service.CheckService;
import com.rjl.service.StudentService;
import com.rjl.service.SubmitService;
import com.rjl.service.WorkerService;

@Controller
@RequestMapping("/check")
public class CheckController {

	@Resource
	private CheckService checkService;
	
	@Resource
	private StudentService studentService;
	
	@Resource
	private WorkerService workerService;
	
	@Resource
	private SubmitService submitService;

//	@Resource
//	private HSCheckResult hsCheckResult;
	
	@Resource
	private Student student;


	@RequestMapping("/addCheck")
	public void addCheck(HttpServletRequest request,HttpServletResponse response) {
		response.setCharacterEncoding("utf-8");

//		String data = request.getParameter("check");
//		JSONObject dataJsonObject = JSONObject.parseObject(data);
//		hsCheckResult = JSONObject.toJavaObject(dataJsonObject, HSCheckResult.class);

		String sno =request.getParameter("sno");
		student = studentService.selectStudent(Integer.valueOf(sno));
		
		String wno =request.getParameter("wno");
		Worker worker = workerService.selectWorker(wno);
		System.out.println("添加核酸检验信息页面的请求！！！！学号："+sno+"工号："+wno);
		SubmitData submitData=submitService.selectSubmitDataBySno(sno);
		
		
		String sname =student.getSname();
		String wname =worker.getWname();
		String result =request.getParameter("result");//根据核酸检测结果做进一步反馈，修改提交的数据
		if (result.equals("阴性")) {
			submitData.setComment("核酸检测通过");
			submitData.setStatus("已上报");
		}else {
			submitData.setComment("核酸检测不通过");
			submitData.setStatus("无法上报");
		}
		HSCheckResult hsCheckResult = new HSCheckResult();
		hsCheckResult.setSname(sname);
		hsCheckResult.setResult(result);
		hsCheckResult.setWname(wname);
		
		hsCheckResult.setWorker(worker);
		
//		Set<Worker> workers =new HashSet<Worker>();
//		workers.add(worker);
//		hsCheckResult.setWorkers(workers);
		

		hsCheckResult.setSubmitData(submitData);
		submitData.setCheckResult(hsCheckResult);
		
		hsCheckResult.setStudent(student);
		student.setCheckResult(hsCheckResult);
		
		studentService.addStudent(student);
		submitService.addSubmitData(submitData);
		checkService.addCheck(hsCheckResult);
	

	}
	
	@RequestMapping("/selectCheck")
	public void selectCheckResult(int sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求一个核酸检验结果页面的请求！！！！"+sno);
		
		Student student = studentService.selectStudent(sno);
		HSCheckResult hsCheckResult = student.getCheckResult();
		
		PrintWriter out = response.getWriter();
		if (hsCheckResult != null) {
			out.print(JSON.toJSONString(hsCheckResult));
		}else {
			out.print("error");
		}
		
	}
	
	@RequestMapping("/selectAllCheck")
	public void selectAllCheck(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求所有核酸信息页面的请求！！！！");
		
		List<HSCheckResult> hsCheckResults =checkService.selectAllCheck();
		
		PrintWriter out = response.getWriter();
		if (hsCheckResults != null) {
			out.print(JSON.toJSONString(hsCheckResults));
		}else {
			out.print("error");
		}
	}
	
	
}
