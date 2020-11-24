 package com.cg.fms.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "Employee_ID")
	private int employeeId;
	
	@NotBlank(message = "Name can't be null or empty")
	@Size(max = 30, message = "Name can't be longer than 40 characters")
	@Column(length = 40, name = "Employee_Name")
	private String empName;
	
	@NotEmpty(message = "Password can't be null")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,15}$", message="Password must have upperCase,lowerCase,numbers and special characters,8-15characters long")
	@Column(length = 15, name = "Password")
	private String password;
	
	@NotNull(message = "Role can't be null or empty")
	@Enumerated(EnumType.STRING)
	@Column(length = 15, name = "Role")
	private Role role;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "Program_ID")
	private Program program;

	
	public Employee() {
		super();
	}

	public Employee(int employeeId, String empName, String password, Role role, Program program) {
		super();
		this.employeeId = employeeId;
		this.empName = empName;
		this.password = password;
		this.role = role;
		this.program = program;
	}
	
	public int getEmployeeId() {
		return employeeId;
	}	
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
 
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	public Program getProgram() {
		return program;
	}
	public void setProgram(Program program) {
		this.program = program;
	}
}
