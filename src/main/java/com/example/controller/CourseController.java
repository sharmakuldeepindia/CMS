package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.model.Course;
import com.example.model.User;
import com.example.repository.CourseFilesRepository;
import com.example.repository.CourseRepository;
import com.example.service.UserService;

@Controller
public class CourseController {
	@Autowired
	CourseRepository courseRepo;
	@Autowired
	CourseFilesRepository courseFilesRepo;
	@Autowired
	UserService userService;
	
	@ModelAttribute("userName")
	public String getVersion() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("username is:"+auth.getName());		
		User user = userService.findUserByEmail(auth.getName());
	    return user.getName();
	}
	
	@GetMapping("/addCourse")
    public String showSignUpForm(Model model) {
		model.addAttribute("course", new Course());
        return "course/addCourse";
}
	
	 @PostMapping("/addCourse")
	    public String addCourse(@ModelAttribute("course") Course course, BindingResult result, 
	    		Model model) {
	        if (result.hasErrors()) {
	            return "course/addCourse";
	        }	         
	        courseRepo.save(course);
//	        model.addAttribute("courses", courseRepo.findAll());
//	        return "course/courseIndex";
	        return "redirect:/courseIndex";
	    }
		@GetMapping("/courseIndex")
	    public String showAllCourses(Model model) {
			 model.addAttribute("courses", courseRepo.findAll());
		        return "course/courseIndex";
	}

}
