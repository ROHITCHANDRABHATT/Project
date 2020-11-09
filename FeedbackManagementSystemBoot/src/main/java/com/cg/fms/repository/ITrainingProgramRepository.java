package com.cg.fms.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.fms.entity.Program;

public interface ITrainingProgramRepository extends JpaRepository<Program, Integer>{
	
	@Query("SELECT p FROM Program p WHERE p.startDate=:start")
	List<Program> findByLocalDate(@Param("start") LocalDate date);
	
	@Query("FROM Program WHERE facultyId=:faculty")
	List<Program> findByFacultyId(@Param("faculty") int facultyid);
}
