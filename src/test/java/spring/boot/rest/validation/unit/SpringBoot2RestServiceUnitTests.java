package spring.boot.rest.validation.unit;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import spring.boot.rest.validation.SpringBootRestValidationApplication;
import spring.boot.rest.validation.controller.StudentResource;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes=SpringBootRestValidationApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integrationtest.properties")
public class SpringBoot2RestServiceUnitTests {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	private StudentResource resource;
	
	@Test
	public void givenEmployees_whenGetEmployees_thenStatus200() throws Exception{
		mvc.perform(MockMvcRequestBuilders.get("/students/10001").accept(MediaType.APPLICATION_JSON_UTF8))
		.andExpect(status().isOk())
		.andExpect(content().string(containsString("Ranga")));
	}
}
