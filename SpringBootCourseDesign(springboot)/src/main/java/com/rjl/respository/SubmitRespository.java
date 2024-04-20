package com.rjl.respository;
import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rjl.bean.PrepareData;
import com.rjl.bean.SubmitData;

@Repository
public interface SubmitRespository extends CrudRepository<SubmitData, Integer> {
	
	@Query(value="select c.* from cd_submit c join cd_student s on s.sno = c.stu_sno where c.status = ?1",nativeQuery=true)
	List<SubmitData> selectSubmitByStatus(@Param(value = "status") String status);
	
	@Query(value="select * from cd_submit where status != ?1",nativeQuery=true)
	List<SubmitData> selectSubmitNoByStatus(@Param(value = "status") String status);
	
	@Query(value="select * from cd_submit where stu_sno=?1",nativeQuery=true)
	SubmitData selectSubmitDataBySno(String sno);
	
	@Modifying
	@Query(value="update cd_submit set status = ?1,comment = ?2 where sno = ?3",nativeQuery=true)
	void updateSubmitData(String status,String comment,int sno);
	
}

