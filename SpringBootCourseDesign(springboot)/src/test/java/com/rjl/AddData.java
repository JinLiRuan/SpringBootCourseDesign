package com.rjl;

import org.junit.jupiter.api.Test;
import javax.annotation.Resource;

import com.rjl.bean.Admin;
import com.rjl.bean.HSCheckResult;
import com.rjl.bean.PrepareData;
import com.rjl.bean.Student;
import com.rjl.bean.SubmitData;
import com.rjl.bean.Worker;
import com.rjl.service.AdminService;
import com.rjl.service.CheckService;
import com.rjl.service.PreDataService;
import com.rjl.service.StudentService;
import com.rjl.service.SubmitService;
import com.rjl.service.WorkerService;

class AddData {

		@Resource
		private StudentService studentService;
		@Resource
		private WorkerService workerService;
		@Resource
		private AdminService adminService;
		@Resource
		private PreDataService preDataService;
		@Resource
		private SubmitService submitService;
		@Resource
		private CheckService checkService;
		
		@Test
		public void testAdd() {
			Student student =new Student();
			student.setSno(1940);
			student.setSname("阮锦利");
			student.setSage(20);
			student.setSex("男");
			student.setClasses("19大数据3班");
			student.setDept("软件系");
			
			Worker worker =new Worker();
			worker.setWno("1001");

			Admin admin =new Admin();
			admin.setId(1);
			admin.setAname("张三");
			admin.setLoginName("admin");
			admin.setPassword("admin");
			
			PrepareData prepareData = new PrepareData();
			prepareData.setId(1);
			prepareData.setJkm("y");
			prepareData.setSname("张三");
			prepareData.setStatus("待处理");
			prepareData.setAddress("南门");
			prepareData.setStudent(student);
			
			SubmitData submitData= new SubmitData();
			submitData.setId(1);
			
			HSCheckResult hsCheckResult=new HSCheckResult();
			hsCheckResult.setId(1);
			
			student.setSubmitData(submitData);
			student.setCheckResult(hsCheckResult);
			student.setPrepareData(prepareData);

			System.out.println(student.toString());
			studentService.addStudent(student);
			
		
			workerService.addWorker(worker);

			adminService.addAdmin(admin);
		
		
	}

}
