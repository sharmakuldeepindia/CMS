package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.example.model.User;
import com.example.repository.RoleRepository;
import com.example.service.UserService;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@EnableScheduling
public class DemoApplication implements CommandLineRunner {
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		
	}

	@Override
	public void run(String... arg0) throws Exception {
		 User u = new User("admin@gmail.com","admin","admin","admin",1);
		 if(userService.findUserByEmail(u.getEmail())==null) {
			 userService.saveUser(u);
		 }
		 
		  u = new User("user1@gmail.com","user1","user1","user1",1);
		 if(userService.findUserByEmail(u.getEmail())==null) {
			 userService.saveUser(u);
		 }

		 u = new User("user2@gmail.com","user2","user2","user2",1);
		 if(userService.findUserByEmail(u.getEmail())==null) {
			 userService.saveUser(u);
		 }
		
	}	
}
