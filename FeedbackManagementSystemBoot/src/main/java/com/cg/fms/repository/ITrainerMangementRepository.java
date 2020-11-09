package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.fms.entity.Trainer;

@Repository
public interface ITrainerMangementRepository extends JpaRepository<Trainer, Integer> {
	
	public List<Trainer> findBySkillIgnoreCase(String skill);
}
