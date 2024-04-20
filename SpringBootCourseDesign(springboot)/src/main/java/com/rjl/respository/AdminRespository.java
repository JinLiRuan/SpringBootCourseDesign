package com.rjl.respository;


import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rjl.bean.Admin;

@Repository
public interface AdminRespository extends CrudRepository<Admin, String> {

	@Query(value="select * from cd_admin where login_name=?1",nativeQuery=true)
	Admin selectAdminByLoginName(String name);
	

	@Transactional
	@Modifying
	@Query(value="update cd_admin set password = ?1 where login_name=?2",nativeQuery=true)
	int updateAdminPassword(String password,String loginName);
}
