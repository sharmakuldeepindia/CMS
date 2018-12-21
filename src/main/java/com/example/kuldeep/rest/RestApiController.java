package com.example.kuldeep.rest;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;
 
//import com.websystique.springboot.model.User;
//import com.websystique.springboot.service.UserService;
//import com.websystique.springboot.util.CustomErrorType;
 
@RestController
@RequestMapping("/api")
public class RestApiController {
	
	 @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;
 
    public static final Logger logger = LoggerFactory.getLogger(RestApiController.class);
 
    @Autowired
    UserService userService; //Service which will do all data retrieval/manipulation work
    
    
    
    @RequestMapping(value={"/login"}, method = RequestMethod.POST)
	public Map<String, String> login(@RequestBody String json){
    	String userName="";
    	String password="";
    	JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
    		
    	 userName=(String) jsonObj.get("userName");
    	 password=(String) jsonObj.get("password");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    	  HashMap<String, String> map = new HashMap<>();
    	  System.out.println(userName+"::::"+password);
//    	password = bCryptPasswordEncoder.encode(password);
    	System.out.println(password);
    	 User user = userService.findUserByEmail(userName);
    	 if(bCryptPasswordEncoder.matches(password, user.getPassword())){
    		 Date date= new Date();    		 
    		 long time = date.getTime();
    		     System.out.println("Time in Milliseconds: " + time);
    		     user.setToken(userName+"-"+time);
    		     userService.saveUser(user);
    		     System.out.println(bCryptPasswordEncoder.matches(password, user.getPassword()));
    	    	 System.out.println("user:"+user.getEmail()); 
    	    	 map.put("token", user.getToken());
    	 }
    	 else{
    		 System.out.println("in this");
 	 		  map.put("Autorized", "false");

    	 }
    	 		return map;
	}
    
    
    
    
    
    
 
    // -------------------Retrieve All Users---------------------------------------------
 
    @RequestMapping(value = "/user/", method = RequestMethod.GET)
    public ResponseEntity<List<User>> listAllUsers() {
        List<User> users = userService.findActiveUser();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }
 
    // -------------------Retrieve Single User------------------------------------------
 
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> getUser(@PathVariable("id") long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findUserById(id);
        if (user == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new String("User with id " + id 
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }
 
    // -------------------Create a User-------------------------------------------
 
//    @RequestMapping(value = "/user/", method = RequestMethod.POST)
//    public ResponseEntity<?> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
//        logger.info("Creating User : {}", user);
// 
//        if (userService.isUserExist(user)) {
//            logger.error("Unable to create. A User with name {} already exist", user.getName());
//            return new ResponseEntity(new CustomErrorType("Unable to create. A User with name " + 
//            user.getName() + " already exist."),HttpStatus.CONFLICT);
//        }
//        userService.saveUser(user);
// 
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
//        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
//    }
 
    // ------------------- Update a User ------------------------------------------------
 
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
//    public ResponseEntity<?> updateUser(@PathVariable("id") long id, @RequestBody User user) {
//        logger.info("Updating User with id {}", id);
// 
//        User currentUser = userService.findUserById(id);
// 
//        if (currentUser == null) {
//            logger.error("Unable to update. User with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to upate. User with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
// 
//        currentUser.setName(user.getName());
////        currentUser.setAge(user.getAge());
////        currentUser.setSalary(user.getSalary());
// 
//        userService.updateUser(currentUser);
//        return new ResponseEntity<User>(currentUser, HttpStatus.OK);
//    }
 
    // ------------------- Delete a User-----------------------------------------
 
//    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
//    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
//        logger.info("Fetching & Deleting User with id {}", id);
// 
//        User user = userService.findUserById(id);
//        if (user == null) {
//            logger.error("Unable to delete. User with id {} not found.", id);
//            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."),
//                    HttpStatus.NOT_FOUND);
//        }
//        userService.deleteUserById(id);
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
// 
//    // ------------------- Delete All Users-----------------------------
// 
//    @RequestMapping(value = "/user/", method = RequestMethod.DELETE)
//    public ResponseEntity<User> deleteAllUsers() {
//        logger.info("Deleting All Users");
// 
//        userService.deleteAllUsers();
//        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
//    }
}