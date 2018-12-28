package com.example.kuldeep.rest;

import java.util.Date;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;
import com.example.utility.DNAConstants;
import com.example.utility.ErrorResponse;

@RestController
@RequestMapping("/api")
public class LoginRestController {


	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public static final Logger logger = LoggerFactory.getLogger(UserRestController.class);

	@Autowired
	UserService userService; // Service which will do all data
								// retrieval/manipulation work

	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public ResponseEntity<Object> login(@RequestBody String json) {
		bCryptPasswordEncoder=new BCryptPasswordEncoder();
		ResponseEntity r;
		String userName = "";
		String password = "";
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);
			userName = (String) jsonObj.get("userName");
			password = (String) jsonObj.get("password");
			logger.info(userName+":::"+password);
			User user = userService.findUserByEmail(userName);
			logger.info(""+bCryptPasswordEncoder.matches(""+password, user.getPassword()));
			if (user != null && bCryptPasswordEncoder.matches(password, user.getPassword())) {
				Date date = new Date();
				long time = date.getTime();
				System.out.println("Time in Milliseconds: " + time);
				user.setToken(userName + "-" + time);
				user.setTokenTimeStamp(time);
				userService.saveUser(user);
				System.out.println(bCryptPasswordEncoder.matches(password, user.getPassword()));
				System.out.println("user:" + user.getEmail());
				r = new ResponseEntity<>(user, null, HttpStatus.OK);
			} else {
				logger.info("User Name or Password Not Matched");
				ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(),
						HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(),
						"User Name or Password Not Correct.", DNAConstants.DNA_LOGIN);
				r = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
			}

		} catch (JSONException e) {
			e.printStackTrace();
			logger.info("User Name or Password Not Matched");
			ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.name(), "User Name or Password Not Correct.", DNAConstants.DNA_LOGIN);
			r = new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		return r;
	}

	@RequestMapping(value = { "/loginraj" }, method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Object> loginRaj(@RequestBody String json) {
		String email = "";
		String password = "";
		JSONObject jsonObj;
		try {
			jsonObj = new JSONObject(json);

			email = (String) jsonObj.get("email");
			password = (String) jsonObj.get("password");
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		User userVO = userService.findUserByEmail(email);

		if (userVO == null) {
			ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(),
					HttpStatus.BAD_REQUEST.name(), "Activation code is not correct.", DNAConstants.DNA_LOGIN);
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		long time = System.currentTimeMillis();
		System.out.println("Time in Milliseconds: " + time);
		userVO.setToken(userVO.getName() + "-" + time);
		userVO.setTokenTimeStamp(time);
		userService.saveUser(userVO);
		return new ResponseEntity<>(userVO, null, HttpStatus.OK);

	}

}
