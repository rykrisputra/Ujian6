package com.jpa.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomePage {
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		

		return "login";
		
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		

		return "login";
		
	}
	
	@GetMapping("/dashboard")
	public String viewDashboard(Model model) {
		

		return "dashboard";
		
	}
	
}
