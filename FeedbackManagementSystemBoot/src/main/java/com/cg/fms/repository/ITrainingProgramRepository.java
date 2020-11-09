package com.cg.fms.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.fms.entity.Program;

public interface ITrainingProgramRepository extends JpaRepository<Program, Integer>{
	
}
