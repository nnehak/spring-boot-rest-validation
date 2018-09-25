package spring.boot.rest.validation.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import spring.boot.rest.validation.dao.Department;
import spring.boot.rest.validation.dao.Student;
import spring.boot.rest.validation.exception.StudentNotFoundException;
import spring.boot.rest.validation.repository.DepartmentRepository;
import spring.boot.rest.validation.repository.StudentRepository;

@RestController
public class StudentResource {
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired 
	private DepartmentRepository departmentRepository;
	
	@GetMapping("/students")
	public List<Student> retrieveAllStudents(){
		return studentRepository.findAll();
	}
	
	@GetMapping("/students/{id}")
	public Resource<Student> retrieveStudent(@PathVariable long id){
		Optional<Student> student = studentRepository.findByStudentId(id);
		
		if(!student.isPresent())
			throw new StudentNotFoundException("id-"+id);
		
		Resource<Student> resource = new Resource<Student>(student.get());
		
		ControllerLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllStudents());
		resource.add(linkTo.withRel("all-student"));
		return resource;
	}
	
	@DeleteMapping("/students/{id}")
	public void deleteStudent(@PathVariable long id){
		studentRepository.deleteByStudentId(id);
	}
	
	@PostMapping("/students")
	@Transactional
	public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student){
		Department d = null;
		Optional<Department> od;
		if(student.getDepartment().getDepartmentId() != null){
			od = departmentRepository.findById(student.getDepartment().getDepartmentId());
			if(od.isPresent()){d=od.get();};
		}
		if(d== null){
			d = new Department(student.getDepartment().getDepartmentId(), student.getDepartment().getDepartmentName(),
					student.getDepartment().getActive(), LocalDate.now());
			d=departmentRepository.save(d);
		}
		student.setDepartment(d);
		Student savedStudent = studentRepository.save(student);
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getStudentId()).toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@PutMapping("/students/{id}")
	public ResponseEntity<Object> updateStudent(@Valid @RequestBody Student student, @PathVariable long id){
		Optional<Student> studentOptional = studentRepository.findByStudentId(id);
		
		if(!studentOptional.isPresent())
			return ResponseEntity.notFound().build();
		
		student.setStudentId(id);
		studentRepository.save(student);
		return ResponseEntity.noContent().build();
	}
}
