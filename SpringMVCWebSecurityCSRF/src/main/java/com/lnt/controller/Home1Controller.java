package com.lnt.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.minidev.json.JSONObject;


@Controller
public class Home1Controller {

	@GetMapping("/api/test")
	public  @ResponseBody String getTestAPI(HttpServletResponse response) {
		JSONObject obj = new JSONObject();
		obj.put("Name", "Jegadeesh");
		obj.put("Email","Jegadeeshkumar.natarajan@ltts.com");
		return obj.toString();
	}
	
	@PostMapping("/api/postTest")
	public  @ResponseBody String getPostTestAPI() {
		JSONObject obj = new JSONObject();
		obj.put("Name", "Jegadeesh");
		obj.put("Email","Jegadeeshkumar.natarajan@ltts.com");
		obj.put("Method","POST");
		return obj.toString();
	}
	@PostMapping("/api/postTest1")
	public  @ResponseBody String getPostTestAPI(@RequestParam(value="X-XSRF-TOKEN") String token) {
		JSONObject obj = new JSONObject();
		obj.put("Name", "Jegadeesh");
		obj.put("Email","Jegadeeshkumar.natarajan@ltts.com");
		obj.put("Method","POST");
		return obj.toString();
	}
	@GetMapping("/OpenAPI/test")
	public  @ResponseBody String getOpenTestAPI() {
		return "This is the response from OPEN API";
	}
}
