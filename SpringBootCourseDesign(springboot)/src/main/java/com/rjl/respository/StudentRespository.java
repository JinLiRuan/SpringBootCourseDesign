package com.rjl.respository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rjl.bean.Student;
@Repository
public interface StudentRespository extends CrudRepository<Student, Integer>{

//	@Modifying
//	@Query(value = "update Student s set s.name = ?1, p.sage = ?2 where s.sno = ?3")
//	void updateStudent(String name, Integer age, Integer id);
	
}
