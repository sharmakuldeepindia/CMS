package com.example.kuldeep.rest.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.example.service.UserService;
import com.example.utility.DNAConstants;
import com.example.utility.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class AuthenticInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		long startTime = System.currentTimeMillis();
		System.out.println("\n-------- LogInterception.preHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("Start Time: " + System.currentTimeMillis());
		String tokenValue = "";
		Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			if (headerName.equals("token")) {
				System.out.println("HeaderName--------------------" + headerName);
				Enumeration<String> headers = request.getHeaders(headerName);
				while (headers.hasMoreElements()) {
					tokenValue = headers.nextElement();
					System.out.println("Header Values --------------------");
				}
			}

		}
		System.out.println("value of token:" + tokenValue);
		System.out.println(userService);
		Long userId = userService.findBytoken(tokenValue);
		if (userId != null) {
			request.setAttribute("startTime", startTime);
			return true;
		} else {
			System.out.println("token is not valid");
			ObjectMapper mapper = new ObjectMapper();
			ErrorResponse errorResponse = new ErrorResponse(System.currentTimeMillis(), HttpStatus.UNAUTHORIZED.value(),
					HttpStatus.UNAUTHORIZED.name(), "Not Found.", DNAConstants.DNA_LOGIN);
			response.setContentType("application/json");
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			response.getWriter().write(mapper.writeValueAsString(errorResponse));
			return false;
		}

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, //
			Object handler, ModelAndView modelAndView) throws Exception {

		System.out.println("\n-------- LogInterception.postHandle --- ");
		System.out.println("Request URL: " + request.getRequestURL());

		// You can add attributes in the modelAndView
		// and use that in the view page
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, //
			Object handler, Exception ex) throws Exception {
		System.out.println("\n-------- LogInterception.afterCompletion --- ");

		long startTime = (Long) request.getAttribute("startTime");
		long endTime = System.currentTimeMillis();
		System.out.println("Request URL: " + request.getRequestURL());
		System.out.println("End Time: " + endTime);

		System.out.println("Time Taken: " + (endTime - startTime));
	}
}
