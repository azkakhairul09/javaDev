package com.school.administration.app.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import com.school.administration.app.SpringApplicationContext;
import com.school.administration.app.io.repositories.RoleRepository;
import com.school.administration.app.io.repositories.UserRepository;
import com.school.administration.app.service.UserService;
import com.school.administration.app.shared.dto.CredsDto;
import com.school.administration.app.shared.dto.UserDto;
import com.school.administration.app.ui.model.request.LoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	
	@Autowired
	RoleRepository roleRepository;
	
	@Autowired
	UserRepository userRepository;
	
	private final AuthenticationManager authenticationManager;
	
	public AuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest req,
												HttpServletResponse res) throws AuthenticationException {
		try {
			LoginRequestModel creds = new ObjectMapper()
					.readValue(req.getInputStream(), LoginRequestModel.class);
			return authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(
							creds.getUsername(),
							creds.getPassword(),
							new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	protected void successfulAuthentication(HttpServletRequest req,
											HttpServletResponse res,
											FilterChain chain,
											Authentication auth) throws IOException, ServletException {
		String username = ((User) auth.getPrincipal()).getUsername();
		
		String token = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + SecurityConstant.EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SecurityConstant.getTokenSecret())
				.compact();
		
		UserService userService = (UserService) SpringApplicationContext.getBean("userServiceImpl");
		
		UserDto userDto = userService.getUser(username);
		
		res.addHeader(SecurityConstant.HEADER_STRING, SecurityConstant.TOKEN_PREFIX + token);
		res.addHeader("userID", userDto.getUserId());
		String JWT= (SecurityConstant.TOKEN_PREFIX + token);
		String userID = userDto.getUserId();
		
		CredsDto creds = new CredsDto();
		creds.setToken(JWT);
		creds.setUserId(userID);
		creds.setUsername(username);
		
		// Creating Object of ObjectMapper define in Jakson Api 
        ObjectMapper Obj = new ObjectMapper(); 
  
        try { 
            // set object as a json string 
            String response = Obj.writeValueAsString(creds);
                        
            res.setStatus(HttpServletResponse.SC_OK);
    		res.setContentType("application/json");
    		res.getWriter().println(response);
    		res.getWriter().flush();
    		res.getWriter().close();
    		
    		System.out.println(response);
        } 
  
        catch (IOException e) { 
            e.printStackTrace(); 
        }
        
//		if (userDto.getIsActive() != false) {
//			
//			if (userID != null) {
//				
//				if (roleName.equals("Admin")) {
//				
//					CredsDto creds = new CredsDto();
//					creds.setToken(JWT);
//					creds.setUserId(userID);
//					creds.setUsername(username);
//					creds.setRole(roleName);
//					
//					// Creating Object of ObjectMapper define in Jakson Api 
//			        ObjectMapper Obj = new ObjectMapper(); 
//			  
//			        try { 
//			            // set object as a json string 
//			            String response = Obj.writeValueAsString(creds);
//			                        
//			            res.setStatus(HttpServletResponse.SC_OK);
//			    		res.setContentType("application/json");
//			    		res.getWriter().println(response);
//			    		res.getWriter().flush();
//			    		res.getWriter().close();
//			    		
//			    		System.out.println(response);
//			        } 
//			  
//			        catch (IOException e) { 
//			            e.printStackTrace(); 
//			        } 		
//			        
//				} else if (roleName.equals("Finance")) {
//					
//					CredsDto creds = new CredsDto();
//					creds.setToken(JWT);
//					creds.setUserId(userID);
//					creds.setUsername(username);
//					creds.setRole(roleName);
//					
//					// Creating Object of ObjectMapper define in Jakson Api 
//			        ObjectMapper Obj = new ObjectMapper(); 
//			  
//			        try { 
//			            // set object as a json string 
//			            String response = Obj.writeValueAsString(creds);
//			                        
//			            res.setStatus(HttpServletResponse.SC_OK);
//			    		res.setContentType("application/json");
//			    		res.getWriter().println(response);
//			    		res.getWriter().flush();
//			    		res.getWriter().close();
//			        } 
//			  
//			        catch (IOException e) { 
//			            e.printStackTrace(); 
//			        } 		
//				}
//			}
//			
//		}
		
		
	}

}
