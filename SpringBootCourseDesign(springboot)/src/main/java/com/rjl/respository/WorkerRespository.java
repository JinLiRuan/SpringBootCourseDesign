package com.rjl.respository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rjl.bean.Worker;

@Repository
public interface WorkerRespository extends CrudRepository<Worker, Integer> {

	
	@Query(value="select * from cd_worker where wno=?1",nativeQuery=true)
	Worker selectWorker(String wno); 
}
