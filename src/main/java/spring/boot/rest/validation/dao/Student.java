package spring.boot.rest.validation.dao;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Student {

	@Id
	@GeneratedValue
	private Long studentId;
	
	@Size(min = 2, message = "Name should have atleast 2 characters")
	private String name;
	
	private String passportNumber;
	
	@ManyToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "studentDepartmentId")
	private Department department;
	
	@ManyToMany(targetEntity=Course.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "student_course", 
	joinColumns= {@JoinColumn(name="studentId")}, inverseJoinColumns = {@JoinColumn(name="courseId")})
	private Set<Course> courses;
	
	public Student(){
		super();
	}
	
	public Student(Long id, String name, String passportNumber){
		super();
		this.studentId = id;
		this.name = name;
		this.passportNumber = passportNumber;
	}
	
	

public Long getStudentId() {
	return studentId;
}

public void setStudentId(Long studentId) {
	this.studentId = studentId;
}

public Department getDepartment() {
	return department;
}

public void setDepartment(Department department) {
	this.department = department;
}

public Set<Course> getCourses() {
	return courses;
}

public void setCourses(Set<Course> courses) {
	this.courses = courses;
}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassportNumber() {
		return passportNumber;
	}

	public void setPassportNumber(String passportNumber) {
		this.passportNumber = passportNumber;
	}

}
