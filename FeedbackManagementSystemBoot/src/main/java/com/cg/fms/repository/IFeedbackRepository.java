package com.cg.fms.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.fms.entity.Feedback;
import com.cg.fms.entity.Program;

public interface IFeedbackRepository extends JpaRepository<Feedback, Integer> {

//@Query("FROM Feedback WHERE tarinerid=:trainerid")
public List<Feedback> findByTrainerid(int trainerid);

@Query("select f FROM Feedback f WHERE f.program.programId=:programid")
public List<Feedback> viewByProgramid(@Param("programid") int programid);
}
