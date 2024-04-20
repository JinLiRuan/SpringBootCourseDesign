package com.rjl.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rjl.bean.HSCheckResult;

@Repository
public interface CheckRespository extends CrudRepository<HSCheckResult, Integer> {

	
	@Query(value = "select * from cd_check",nativeQuery=true)
	List<HSCheckResult> selectAllCheck();
	
	
}
