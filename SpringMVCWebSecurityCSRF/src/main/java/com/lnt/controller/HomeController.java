package com.lnt.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class HomeController {
	private static final String CURRENT_USER_PRINCIPAL = "CURRENT_USER_PRINCIPAL";
	
	@GetMapping(value="/showMessage")
	public String getMessagePage() {
		System.out.println("SHOW MESSAGE CALLED");
		return "showMessage";
	}
	
	@GetMapping("/api/userdetails")
	public  @ResponseBody String getTestAPI(HttpServletRequest request) {
//		UserPrincipal principal = (UserPrincipal) request.getSession().getAttribute(CURRENT_USER_PRINCIPAL);
		ObjectMapper mapper= new ObjectMapper();
		
		String res="";

		return res;
	}
}
