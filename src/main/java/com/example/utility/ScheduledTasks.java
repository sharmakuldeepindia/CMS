package com.example.utility;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;



@Component
public class ScheduledTasks {
	
//	private static final Logger logger = LoggerFactory.getLogger(ScheduledTasks.class);
//    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//
//    @Scheduled(fixedRate = 10000)
//    public void scheduleTaskWithFixedRate() {
//    	System.out.println("done");
//        logger.info("Fixed Rate Task :: Execution Time - {}", dateTimeFormatter.format(LocalDateTime.now()) );
//       // sendPostRequest();
//       
//        try {
//        	if(EventController.tempSocket) {        	
//			sendGET();
//        	}
//        	else {
//        		System.out.println("Socket is not opened");
//        	}
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//    }
//    
//    public void sendPostRequest() {
//    	final String USER_AGENT = "Mozilla/5.0";
//    	if(EventController.tempSocket) {
//    		System.out.println("posting****");	
//    		
//    		try {
//    	    	URL obj = new URL("http://192.168.10.28:8080/postChat");
//    			HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//    			con.setRequestMethod("GET");
//    			con.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
//    			con.setRequestProperty("User-Agent", USER_AGENT);
//    			JSONObject parent=new JSONObject();	   
//    			parent.put("content", "Hello");
//    			parent.put("sender","Kuldeep");
//    			parent.put("type", "ChAT");
//    			// For POST only - START
//    			con.setDoOutput(true);
//    			OutputStream os = con.getOutputStream();
//    		
//    				os.write(parent.toString().getBytes());
//    				System.out.println(con.getResponseCode());
//    				  System.out.println(con.getResponseMessage());
//    				os.flush();
//    				os.close();
//    			} catch (Exception e) {
//    				// TODO Auto-generated catch block
//    				e.printStackTrace();
//    			}
//    		
//    			// For POST only - END
//    	}
//    	else {
//    		System.out.println("cannot post");  	
//    	}  
//    	
//    }
//    
//    
//    
//    private void sendGET() throws IOException {
//    	
//    	final String USER_AGENT = "Mozilla/5.0";
//		URL obj = new URL("http://192.168.10.28:8080/hitChat");
//		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
//		con.setRequestMethod("GET");
//		con.setRequestProperty("User-Agent", USER_AGENT);
//		int responseCode = con.getResponseCode();
//		con.connect();
//		System.out.println("GET Response Code :: " + responseCode);
//		if (responseCode == HttpURLConnection.HTTP_OK) { // success
//			BufferedReader in = new BufferedReader(new InputStreamReader(
//					con.getInputStream()));
//			String inputLine;
//			StringBuffer response = new StringBuffer();
//
//			while ((inputLine = in.readLine()) != null) {
//				response.append(inputLine);
//			}
//			in.close();
//
//			// print result
//			System.out.println(response.toString());
//		} else {
//			System.out.println("GET request not worked");
//		}
//
//	}
}

