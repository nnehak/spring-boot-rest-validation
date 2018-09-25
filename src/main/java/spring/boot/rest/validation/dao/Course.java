package spring.boot.rest.validation.dao;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Course {

	@Id
	@GeneratedValue
	private Long courseID;
	private String courseName;
	private LocalDate startYear;
	private LocalDate endYear;
	private String active;
	
	private Course(){
		super();
	}
	
	public Course(Long id, String courseName, String acive, LocalDate startYear){
		this.courseID=id;
		this.courseName=courseName;
		this.active=active;
		this.startYear=startYear;
	}
	
	@ManyToMany(targetEntity=Student.class, mappedBy="courses", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Course> students;
	
	@ManyToMany(targetEntity=Department.class, cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name="department_course", 
	joinColumns = {@JoinColumn(name="departmentId")},
	inverseJoinColumns = {@JoinColumn(name="courseId")})
	private Set<Department> departments;
	
	
	public Long getCourseID(){
		return courseID;
	}
	
	public void setCourseID(Long courseID){
		this.courseID=courseID;
	}
	
	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
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

	public Set<Course> getStudents() {
		return students;
	}

	public void setStudents(Set<Course> students) {
		this.students = students;
	}

	public Set<Department> getDepartments() {
		return departments;
	}

	public void setDepartments(Set<Department> departments) {
		this.departments = departments;
	}

}
