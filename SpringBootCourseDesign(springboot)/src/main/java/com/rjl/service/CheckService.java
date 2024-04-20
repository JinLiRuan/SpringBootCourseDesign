package com.rjl.service;

import java.util.List;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.rjl.bean.HSCheckResult;
import com.rjl.respository.CheckRespository;


@Service
public class CheckService {

	@Resource
	private CheckRespository checkRespository;
	
	public HSCheckResult addCheck(HSCheckResult hsCheckResult) {
		return checkRespository.save(hsCheckResult);
	}
	
	public List<HSCheckResult> selectAllCheck() {
		return checkRespository.selectAllCheck();
	}
	
	
}
