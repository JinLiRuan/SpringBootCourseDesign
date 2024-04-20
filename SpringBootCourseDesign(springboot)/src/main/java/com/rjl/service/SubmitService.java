package com.rjl.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.rjl.bean.PrepareData;
import com.rjl.bean.Student;
import com.rjl.bean.SubmitData;
import com.rjl.bean.Worker;
import com.rjl.respository.StudentRespository;
import com.rjl.respository.SubmitRespository;
import com.rjl.respository.WorkerRespository;

@Service
public class SubmitService {

	@Resource
	private SubmitRespository submitRespository;
	
	public SubmitData addSubmitData(SubmitData submitData) {
		return submitRespository.save(submitData);
	}
	
	public SubmitData selectSubmitDataBySno(String sno) {
		return submitRespository.selectSubmitDataBySno(sno);
	}
	
	public List<SubmitData> selectAlLSubmitDatas() {
		
		return (List<SubmitData>) submitRespository.findAll();
	}

	
	public List<SubmitData> selectByStatus(String status) {
		status = "已上报";
		return submitRespository.selectSubmitByStatus(status);
	}
	
	public List<SubmitData> selectNoByStatus(String status) {
		status = "已上报";
		
		return submitRespository.selectSubmitNoByStatus(status);
	}
	
}
