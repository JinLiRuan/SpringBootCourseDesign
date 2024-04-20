package com.rjl.service;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rjl.bean.Admin;
import com.rjl.bean.Student;
import com.rjl.bean.Worker;
import com.rjl.respository.AdminRespository;
import com.rjl.respository.StudentRespository;
import com.rjl.respository.WorkerRespository;

@Service
public class AdminService {

	@Resource
	private AdminRespository adminRespository;
	
	public Admin addAdmin(Admin admin) {
		return adminRespository.save(admin);
	}
	
	public Admin selectAdminByAno(String login_name) {
		return adminRespository.selectAdminByLoginName(login_name);
	}
	
	
	public int updatePassword(String pass,String name) {
		
		 return adminRespository.updateAdminPassword(pass, name);
	}
}
