package com.example.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.example.kuldeep.rest.interceptor.AuthenticInterceptor;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Autowired
	AuthenticInterceptor authenticInterceptor;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	  @Override
	    public void addResourceHandlers(ResourceHandlerRegistry registry) {
	        registry.addResourceHandler(
	                "/webjars/**",
	                "/img/**",
	                "/css/**",
	                "/js/**")
	                .addResourceLocations(
	                        "classpath:/META-INF/resources/webjars/",
	                        "classpath:/static/images/",
	                        "classpath:/static/css/",
	                        "classpath:/static/js/");
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
	      registry.addInterceptor(authenticInterceptor)//
	            .addPathPatterns("/api/**")//
	            .excludePathPatterns("/api/login");
	   }

}