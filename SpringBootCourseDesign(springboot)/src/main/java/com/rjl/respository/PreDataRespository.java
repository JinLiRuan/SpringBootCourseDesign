package com.rjl.respository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rjl.bean.PrepareData;

@Repository
public interface PreDataRespository extends CrudRepository<PrepareData, Integer> {
	
	@Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	@Modifying
	@Query(value="update cd_prepare set status = ?1 where sno = ?2",nativeQuery=true)
	void updatePreData(String status,int sno);
	
	
}
