package com.example.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.kuldeep.rest.interceptor.AuthenticInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	
	  @Override
	   public void addInterceptors(InterceptorRegistry registry) {
//	      // LogInterceptor apply to all URLs.
//	      registry.addInterceptor(new LogInterceptor());
//	 
//	      // Old Login url, no longer use.
//	      // Use OldURLInterceptor to redirect to a new URL.
//	      registry.addInterceptor(new OldLoginInterceptor())//
//	            .addPathPatterns("/admin/oldLogin");
	 
	      // This interceptor apply to URL like /admin/*
	      // Exclude /admin/oldLogin
	      registry.addInterceptor(new AuthenticInterceptor())//
	            .addPathPatterns("/api/**")//
	            .excludePathPatterns("/api/login");
	   }

}