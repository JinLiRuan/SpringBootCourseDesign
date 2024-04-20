package com.rjl.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.alibaba.fastjson.JSON;
import com.rjl.bean.PrepareData;
import com.rjl.bean.SubmitData;
import com.rjl.service.SubmitService;

@Controller
@RequestMapping("/submit")
public class SubmitController {

	@Resource
	private SubmitService submitService;
	
	@Resource
	private SubmitData submitData;
	
	
	@RequestMapping("/selectSubmit")
	public void selectCheckResult(String sno,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求一个已提交数据结果页面的请求！！！！"+sno);
		
		submitData = submitService.selectSubmitDataBySno(sno);
		
		PrintWriter out = response.getWriter();
		if (submitData != null) {
			out.print(JSON.toJSONString(submitData));
		}else {
			out.print("error");
		}
		
	}
	@RequestMapping("/selectSubmitByStatus")
	public void selectPreDataByStatus(String status,HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求提交状态信息页面的请求！！！！"+status);
		List<SubmitData> submitDatas;
		if (status.equals("其他")) {
			submitDatas = submitService.selectNoByStatus(status);
		}else {
			submitDatas = submitService.selectByStatus(status);
		}
		
		
		PrintWriter out = response.getWriter();
		if (submitDatas.size() != 0) {
			out.print(JSON.toJSONString(submitDatas));
		}else {
			out.print("error");
		}
	}
	@RequestMapping("/selectAllSubmit")
	public void selectAllSubmit(HttpServletResponse response) throws IOException {
		response.setCharacterEncoding("utf-8");
		System.out.println("得到请求所有已提交信息页面的请求！！！！");
		
		List<SubmitData> submitDatas=submitService.selectAlLSubmitDatas();
		
		PrintWriter out = response.getWriter();
		if (submitDatas != null) {
			out.print(JSON.toJSONString(submitDatas));
		}else {
			out.print("error");
		}
	}
	
	
	
}
