package com.rjl.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rjl.bean.PrepareData;
import com.rjl.bean.Student;
import com.rjl.bean.Worker;
import com.rjl.respository.PreDataRespository;
import com.rjl.respository.StudentRespository;
import com.rjl.respository.WorkerRespository;

@Service
public class PreDataService {

	@Resource
	private PreDataRespository preDataRespository;
	
	public PrepareData addPreData(PrepareData prepareData) {
		return preDataRespository.save(prepareData);
	}
	
	public PrepareData selectPreDataBySno(int sno) {
		return preDataRespository.findOne(sno);
	}
	
	public List<PrepareData> selectAllPreData() {
		List<PrepareData> prepareDatas = (List<PrepareData>) preDataRespository.findAll();
		return prepareDatas;
	}
	
	public void uodatePreData(int sno) {
		
		String status = "已处理";
		preDataRespository.updatePreData(status, sno);
	}
	
	
	
	
}
