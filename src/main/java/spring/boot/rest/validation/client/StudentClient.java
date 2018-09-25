package spring.boot.rest.validation.client;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import spring.boot.rest.validation.client.exception.RestTemplateResponseErrorHandler;
import spring.boot.rest.validation.dao.Department;
import spring.boot.rest.validation.dao.Student;

public class StudentClient {
	
	private static final RestTemplate restTemplate = buildRestTemplate();
	
	public static RestTemplate buildRestTemplate(){
		int timeout=5000;
		
		RestTemplate rs = new RestTemplateBuilder()
				.errorHandler(new RestTemplateResponseErrorHandler())
				.setConnectTimeout(timeout)
				.setReadTimeout(timeout)
				.build();
		return rs;
	}
	
	public static Student getStudent(Long id){
		String URL = "http://localhost:8090/students/{id}";
		Map<String, String> params = new HashMap<String,String>();
		params.put("id", id.toString());
		Student s= restTemplate.getForObject(URL, Student.class, params);
		return s;
	}
	
	public static void postStudent(Student s){
		String URL = "http://localhost:8090/students";
		Student added = restTemplate.postForObject(URL,s, Student.class);
		System.out.println(added);
	}
	
	public static void deleteStudent(Long id){
		String URL = "http://localhost:8090/students/{id}";
		Map<String,String> params = new HashMap<String, String>();
		params.put("id", id.toString());
		restTemplate.delete(URL, params);
	}
	
	public static void putStudent(Student s)
	{
		String URL = "http://localhost:8090/students/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", s.getStudentId().toString());
		restTemplate.put(URL, s, params);
	}
	
	public static void main(String[] args){
		System.out.println(getStudent((long)10007));
		Student s = new Student(10003L,"Test","T12345678");
		Department d = new Department(10003L,"TestDepartment","Yes", LocalDate.now());
		s.setDepartment(d);
		postStudent(s);
		s.setName("New Test Name");
		putStudent(s);
		deleteStudent(10003L);
		
	}

}
