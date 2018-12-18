package com.example.CriteriaTest;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.service.FetchService;


@RunWith(SpringRunner.class)
@SpringBootTest
public class JPA_Criteria_Test {
	@Autowired 
	FetchService fs;

	@Test
	public void runCriteriaTest() {
		
//		fs.testJPACriteriaQuery();
		
	}
	@Test
	public void testUser() {
		
		fs.testUser();
		
	}
	

}
