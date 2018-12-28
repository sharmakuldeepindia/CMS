package com.example.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.kuldeep.rest.UserRestController;
import com.example.model.Course;
import com.example.model.CourseFiles;
import com.example.model.User;
import com.example.repository.CourseFilesRepository;
import com.example.repository.CourseRepository;
import com.example.service.UserService;

@Controller
public class CourseController {
    public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);
    @Value("${UPLOADED_FOLDER}")
    private String UPLOADED_FOLDER;
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
		
	    @RequestMapping(value = "/addCourseFiles/{id}", method = RequestMethod.GET)
	    public String getCourse(@PathVariable("id") long id, Model model) {
	        logger.info("Fetching Course with id {}", id);
	   	    model.addAttribute("course", courseRepo.findById(id));
	   	    return "course/addCourseFiles";
	        
	    }
	    
	    @RequestMapping(value = "/addCourseFiles", method =RequestMethod.POST)
	    public  String uploadMultipleFiles(@RequestParam("description") String[] descriptions,
	    		@RequestParam("file") MultipartFile[] files, @RequestParam("id") long id, Model model) {
	        	logger.info("Fetching Course for upload files with id {}", id);	        
	        	Course course = courseRepo.findById(id);
	        	 String status = "";
	        	if(course!=null){	        		
	        		  if (files.length != descriptions.length){	        			
	        			 status="Mismatching no of files are equal to description";
	        			 model.addAttribute("status", status); 
	        			 return "course/addCourseFiles/"+id;  
	        		  }
	        		  logger.info(UPLOADED_FOLDER+"********************************");
	        		  File dir = new File(UPLOADED_FOLDER);
	  	              for (int i = 0; i < files.length; i++) {
	  	                MultipartFile file = files[i];
	  	                String description = descriptions[i];
	  	                try {
	  	                    byte[] bytes = file.getBytes();

	  	                    if (!dir.exists())
	  	                        dir.mkdirs();

	  	                    File uploadFile = new File(dir.getAbsolutePath()
	  	                            + File.separator + file.getOriginalFilename());
	  	                    BufferedOutputStream outputStream = new BufferedOutputStream(
	  	                            new FileOutputStream(uploadFile));
	  	                    outputStream.write(bytes);
	  	                    outputStream.close();
	  	                    
	  	                    CourseFiles cf = new CourseFiles();
	  	                    cf.setCourseId(course.getId());
	  	                    cf.setFileLocation(dir.getAbsolutePath());
	  	                    cf.setFileType(file.getContentType());
	  	                    cf.setFileName(file.getOriginalFilename());
	  	                    cf.setFileDescription(description);
	  	                    courseFilesRepo.save(cf);	
	  	                    status = status + "You successfully uploaded file=" + file.getOriginalFilename();
	  	                    model.addAttribute("status", status); 
		        			
	  	                } catch (Exception e) {
	  	                    status = status + "Failed to upload " + file.getOriginalFilename()+ " " + e.getMessage();
	  	                    model.addAttribute("status", status); 
		        			return "course/addCourseFiles/"+id; 
	  	                }
	  	             
	  	            }
	  	            return "redirect:/viewCourse/"+id; 
	        	}
	        	else{
	        		status="No course Found with Id";
	        		 model.addAttribute("status", status); 
        			 return "course/addCourseFiles/"+id; 
	        	}	          
	        
	        }
	    
	    @RequestMapping(value = "/viewCourse/{id}", method = RequestMethod.GET)
	    public String viewCourse(@PathVariable("id") long id, Model model) {
	        logger.info("Fetching Course in viewCourse with id {}", id);
	        Course c = courseRepo.findById(id);
	   	    model.addAttribute("course", c);
	   	    List<CourseFiles> cf = courseFilesRepo.findByCourseId(c.getId());
	   	    for(CourseFiles cc:  cf){
	   	    	logger.info("file name "+cc.getFileName());
	   	    }
	   	    model.addAttribute("courseFiles", cf);
	   	    return "course/showCourse";
	        
	    }
	    
	    @RequestMapping(value = "/deleteCourse/{id}", method = RequestMethod.GET)
	    public String deleteCourse(@PathVariable("id") long id, Model model) {
	        logger.info("Fetching Course in deleteCourse with id {}", id);
	        Course c = courseRepo.findById(id);
	        List<CourseFiles> cf = courseFilesRepo.findByCourseId(c.getId());
	        for(CourseFiles cc:  cf){
	        	 courseFilesRepo.delete(cc);
	        	 //write code to delete the files also*********************
	   	    }
	        courseRepo.delete(c);
	   	    return "redirect:/courseIndex";
	        
	    }
	    
	    
	    @RequestMapping(value = "/deleteCourseFile/{id}", method = RequestMethod.GET)
	    public String deleteCourseFile(@PathVariable("id") long id) {
	        logger.info("Fetching Course in deleteCourse file with id {}", id);
	        CourseFiles cf = courseFilesRepo.findOne(id);
	        Course c = courseRepo.findById(cf.getCourseId());	      
	        courseFilesRepo.delete(cf);
	        //delete file from file system as well
	        return "redirect:/viewCourse/"+c.getId(); 
	        
	    }

}
