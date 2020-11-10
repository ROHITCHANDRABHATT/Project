
package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Employee;
import com.cg.fms.entity.Program;


@Repository
public interface IParticipantRepository extends JpaRepository<Employee, Integer>{

	
	
   //@Query("FROM Employee WHERE programId=:id")
	public  List<Employee> findByProgram( int programId);

	

}
