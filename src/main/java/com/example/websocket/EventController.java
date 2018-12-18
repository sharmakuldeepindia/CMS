package com.example.websocket;
import java.awt.TrayIcon.MessageType;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.User;
import com.example.service.UserService;



@Controller
public class EventController {
	
	  private SimpMessagingTemplate template;
	  public static boolean tempSocket=false;
	  @Autowired
	  	UserService userService;
      @Autowired
        public EventController(SimpMessagingTemplate template) {
            this.template = template;
        }
	
	@RequestMapping(value="/event")
		public String event() {
		System.out.println("in event page");		
			return "Hello";
	}
	
	  @MessageMapping("/eventPush")
	    @SendTo("/topic/public/event")
	    public String addUser() {
		  System.out.println("in thissssssssssssssssssssssssssssss");
	        
	      return "Socket Connected";
	    }
	  
	  @RequestMapping(value="/hitChat", method=RequestMethod.GET)  
	  @ResponseBody
	    public String getChat(){
		  List<User> activeUserList = userService.findActiveUser();
		  try {
			  Date d = new Date();
			  String time = ""+d;
			  Map m = new HashMap();
			  m.put("time", time);
			  m.put("activeUser", activeUserList);
			  System.out.println("Hello in event push get");
		      this.template.convertAndSend("/topic/public/event", m); 
		   
		 	 return "Message sent successfully";
		  }
		  catch (Exception e) {
			 System.out.println("Exception Occureed while pushing message");
			 return "";
		  }
		  
	    }
	  
	  
	

}
