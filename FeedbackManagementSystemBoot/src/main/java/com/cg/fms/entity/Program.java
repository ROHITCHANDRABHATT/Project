package com.cg.fms.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


	@Entity
	@Table(name = "Program")
	public class Program {
		@Id
		@GeneratedValue(strategy = GenerationType.TABLE) 
		@Column(name = "programId")
		private int programId;

		@Column(name = "startDate")
		private LocalDate startDate;

		@Column(name = "endDate")
		private LocalDate endDate;
		
		@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		@JoinColumn(name="programId" ,referencedColumnName = "programId")
		private Course course;

		@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
		@JoinColumn(name="faculty_id")
	  @Transient
		private Trainer faculty;

		
		public Program() {
			super();
		}

		public Program(int programId, LocalDate startDate, LocalDate endDate, Course course, Trainer faculty) {
			super();
			this.programId = programId;
			this.startDate = startDate;
			this.endDate = endDate;
			this.course = course;
			this.faculty = faculty;
		}

		public int getProgramId() {
			return programId;
		}

		public void setProgramId(int programId) {
			this.programId = programId;
		}

		public LocalDate getStartDate() {
			return startDate;
		}

		public void setStartDate(LocalDate startDate) {
			this.startDate = startDate;
		}

		public LocalDate getEndDate() {
			return endDate;
		}

		public void setEndDate(LocalDate endDate) {
			this.endDate = endDate;
		}

		public Course getCourse() {
			return course;
		}

		public void setCourse(Course course) {
			this.course = course;
		}

		/*public Employee getFaculty() {
			return faculty;
		}
*/
		public void setFaculty(Trainer faculty) {
			this.faculty = faculty;
		}

		@Override
		public String toString() {
			return "Program [programId=" + programId + ", startDate=" + startDate + ", endDate=" + endDate + ", course="
					+ course + ", faculty=" + faculty + "]";
		}
	}
