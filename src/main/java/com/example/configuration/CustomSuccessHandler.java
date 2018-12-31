package com.example.configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

@Component	
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Override
    protected void handle(HttpServletRequest request, 
      HttpServletResponse response, Authentication authentication) throws IOException {
        String targetUrl = determineTargetUrl(authentication,request);
  
        if (response.isCommitted()) {
            System.out.println("Can't redirect");
            return;
        }
  
        redirectStrategy.sendRedirect(request, response, targetUrl);
    }
    
    protected String determineTargetUrl(Authentication authentication,HttpServletRequest request) {
        String url="";
        
        System.out.println("Request URL: " + request.getRequestURL());
        
        Enumeration<String> headerNames = request.getHeaderNames();

		while (headerNames.hasMoreElements()) {
			System.out.println("HeaderName--------------------");
			String headerName = headerNames.nextElement();
			System.out.println(headerName);
			System.out.println("Header Values --------------------");

			Enumeration<String> headers = request.getHeaders(headerName);
			while (headers.hasMoreElements()) {
				String headerValue = headers.nextElement();
				System.out.println(headerValue);
			}
			System.out.println("--------------------");
		}
        
        
        

         System.out.println("in determineTargetUrl()");
        Collection<? extends GrantedAuthority> authorities =  authentication.getAuthorities();
         
        List<String> roles = new ArrayList<String>();
 
        for (GrantedAuthority a : authorities) {
            roles.add(a.getAuthority());
        }
 
        if (isDba(roles)) {
            url = "/db";
        } 
        else if (isAdmin(roles)) {
            url = "/admin/home";
        }
        else if (isUser(roles)) {
//            url = "/user/home";
            url = "/courseIndex";
        }
        else {
            url="/accessDenied";
        }
 
        return url;
    }
    
    
    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    protected RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }
     
    private boolean isUser(List<String> roles) {
        if (roles.contains("USER")) {
            return true;
        }
        return false;
    }
 
    private boolean isAdmin(List<String> roles) {
        if (roles.contains("ADMIN")) {
            return true;
        }
        return false;
    }
 
    private boolean isDba(List<String> roles) {
        if (roles.contains("ROLE_DBA")) {
            return true;
        }
        return false;
    }
}
