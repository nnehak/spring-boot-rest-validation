package spring.boot.rest.validation.integration;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import spring.boot.rest.validation.dao.Student;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringBoot2RestServiceIntegrationTests {

	@Autowired
	private TestRestTemplate restTemplate;
	
	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception{
		String URL = "/students/{id}";
		Map<String, String> params = new HashMap<String, String>();
		params.put("id", "10001");
		Student s = restTemplate.getForObject(URL, Student.class, params);
		assertEquals("Ranga", s.getName());
	}
}
