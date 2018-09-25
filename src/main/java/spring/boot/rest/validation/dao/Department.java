package spring.boot.rest.validation.dao;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
public class Department {

	@Id
	@GeneratedValue
	private Long departmentId;
	private LocalDate startYear;
	private LocalDate endYear;
	private String active;
	private String departmentName;
	
	@JsonIgnore
	@OneToMany(mappedBy="department", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Student> students;
	
	@ManyToMany(targetEntity=Course.class, mappedBy="departments", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Course> courses;
	
	public Department(){
		super();
	}
	
	public Department(Long id, String departmentName, String active, LocalDate startYear){
		this.departmentId=id;
		this.departmentName=departmentName;
		this.active=active;
		this.startYear=startYear;
	}
	
	public Long getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Long departmentId) {
		this.departmentId = departmentId;
	}

	public LocalDate getStartYear() {
		return startYear;
	}

	public void setStartYear(LocalDate startYear) {
		this.startYear = startYear;
	}

	public LocalDate getEndYear() {
		return endYear;
	}

	public void setEndYear(LocalDate endYear) {
		this.endYear = endYear;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
}
