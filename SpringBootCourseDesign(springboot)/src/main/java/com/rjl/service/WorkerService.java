package com.rjl.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rjl.bean.Worker;
import com.rjl.respository.WorkerRespository;

@Service
public class WorkerService {

	@Resource
	private WorkerRespository workerRespository;
	
	public Worker addWorker(Worker worker) {
		return workerRespository.save(worker);
	}
	
	
	public Worker selectWorker(String wno) {//前面设置的类型是什么就是什么的。可以string、int
		return workerRespository.selectWorker(wno);
	}
	
	public List<Worker> selectAllWorker() {
		
		return (List<Worker>) workerRespository.findAll();
	}
	
	
}
