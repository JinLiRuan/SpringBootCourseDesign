package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.rjl.bean.PrepareData;
import com.rjl.bean.Student;
import com.rjl.bean.SubmitData;
import com.rjl.bean.Worker;
import com.rjl.service.PreDataService;
import com.rjl.service.StudentService;
import com.rjl.service.SubmitService;
import com.rjl.service.WorkerService;

@Controller
@RequestMapping("preData")
public class PreDataController {

	@Resource
	private PreDataService preDataService;
	
	@Resource
	private SubmitService submitService;
	
	@Resource
	private StudentService studentService;
	@Resource
	private WorkerService workerService;
//	@Resource
//	private Student student;
//	@Autowired
//	private PrepareData prepareData;
	@Resource
	private SubmitData submitData;


	@RequestMapping("/addPreData")
	public void addPreData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("添加预处理信息页面的请求！！！！"+request.getParameter("address"));
//		student = (Student) request.getAttribute("student");
		
//		String data = jsonObj.getString("data");
//      JSONObject dataObj = JSONObject.parseObject(data);
//      vo = JSONObject.toJavaObject(dataObj, EnterpriseProductQualityVO.class);

//		String data = request.getParameter("student"); //或者传个sno进来代替
//		JSONObject dataJsonObject = JSONObject.parseObject(data);
//		student = JSONObject.toJavaObject(dataJsonObject, Student.class);

		String sno =request.getParameter("sno");
		Student student = studentService.selectStudent(Integer.parseInt(sno));
		PrepareData prepareData =new PrepareData();
		System.out.println(prepareData);
		prepareData.setAddress(request.getParameter("address"));
		prepareData.setJkm("待验证");
		prepareData.setSname(student.getSname());
		prepareData.setStatus("未处理");
		
		student.setPrepareData(prepareData);
//		studentService.addStudent(student);
		prepareData.setStudent(student);
		PrintWriter out = response.getWriter();
		
		
		try {
			preDataService.addPreData(prepareData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("error");
		}
		
		if (prepareData != null) {
			out.print(JSON.toJSONString(prepareData));
			
			/**
			 * 关联查询得到的数据，数据库建外键根本不需要那么多，可以通过第三方表查询另一张表
			 * {"address":"南门","jkm":"y","sname":"小软","status":"未处理",
			 * "student":{"checkResult":{"id":2,"result":"阴性","sname":"小软",
			 * "student":{"$ref":".."},"wname":"张三",
			 * "worker":{"checkResult":{"$ref":".."},
			 * "submitData":{"comment":"直接上报",
			 * "data":"21-11-2021 21:39:57","id":4,"jkm":"y","sname":"小软","status":"已上报",
			 * "student":{"$ref":"$.student"},"wname":"张三",
			 * "worker":{"$ref":".."}},"wage":20,"wname":"张三","wno":1001,"wsex":"男","wtype":"南门卫"}},
			 * "classes":"19大数据3班","dept":"软件系","prepareData":{"$ref":".."},"sage":20,"sex":"男","sname":"小软","sno":1940,
			 * "submitData":{"$ref":"$.student.checkResult.worker.submitData"}}}
			 */
		}else {
			out.print("error");
		}
	
	}
	
	//更改预处理结果，把预处理的上报或打回核酸检测
	@RequestMapping("/updatePreData")
	public void updatePreData(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		
		String sno =request.getParameter("sno");
		String wno=request.getParameter("wno");//工作人员登陆处理学生上报数据
		System.out.println("得到更新一个预处理信息页面的请求！！！！"+sno+"传进来的工号为"+wno);
		Student student = studentService.selectStudent(Integer.parseInt(sno));
		PrepareData prepareData =student.getPrepareData();
		
		submitData.setSname(student.getSname());
		submitData.setJkm(request.getParameter("jkm"));
		submitData.setStudent(student);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");  
		System.out.println("当前时间为"+formatter.format(date));  
		submitData.setData(formatter.format(date));
		
	
		Worker worker=workerService.selectWorker(wno);
		submitData.setWname(worker.getWname());
		submitData.setWorker(worker);
		
		if (request.getParameter("jkm").equals("y")) {
			submitData.setStatus("已上报");
			submitData.setComment("直接上报");
			prepareData.setJkm("y");
		}else {
			submitData.setStatus("未上报");
			submitData.setComment("未核酸检测");
			prepareData.setJkm("n");
		}
		PrintWriter out = response.getWriter();
		try {
			submitService.addSubmitData(submitData);
			preDataService.addPreData(prepareData);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.print("error"); //没有输出到前端
		}
		
		preDataService.uodatePreData(Integer.valueOf(sno));
		
//		prepareData = preDataService.selectPreDataBySno(Integer.valueOf(sno));//不能用sno
		System.out.println("==========preparedata=="+prepareData.toString());
		if (prepareData != null) {
			try {
				out.print(JSON.toJSONString(prepareData));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			out.print("error");
		}
		
		
	}
	
	
	
	
	@RequestMapping("/selectPreData")
	public void selectPreData(int sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求一个预处理信息页面的请求！！！！"+sno);
		
		Student student = studentService.selectStudent(sno);
		PrepareData prepareData = student.getPrepareData();
		
		PrintWriter out = response.getWriter();
		if (prepareData != null) {
			out.print(JSON.toJSONString(prepareData));
		}else {
			out.print("error");
		}
		
	}
	
	@RequestMapping("/selectAllPreData")
	public void selectAllPreData(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到所有预处理信息页面的请求！！！！");
		
		List<PrepareData> prepareDatas =preDataService.selectAllPreData();
		PrintWriter out = response.getWriter();
		if (prepareDatas != null) {
			out.print(JSON.toJSONString(prepareDatas));
		}else {
			out.print("error");
		}
		
	}
		
	
}
