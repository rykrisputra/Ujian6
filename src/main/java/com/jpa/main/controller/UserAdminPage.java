package com.jpa.main.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.jpa.main.entity.UserAdmin;
import com.jpa.main.services.ModelUserAdmin;


@Controller
public class UserAdminPage {

	@Autowired
	ModelUserAdmin modelUserAdmin;
	
	@GetMapping("/useradmin/view")
	public String viewIndexUserAdmin(Model model) {
		
		model.addAttribute("listUserAdmin",modelUserAdmin.getAllUserAdmin());
		model.addAttribute("active",1);
		model.addAttribute("test","Test Aja");
		
		return "view_useradmin";
	}
	
	
	@GetMapping("/useradmin/add")
	public String viewAddUserAdmin(Model model) {
		
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("userAdmin",new UserAdmin());
		
		return "add_useradmin";
	}
	
	@PostMapping("/useradmin/view")
	  public String addUserAdmin(@ModelAttribute UserAdmin userAdmin, Model model) {
		
		// buat penampung data adminuser di halaman htmlnya
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String plainPassword = userAdmin.getPassword();
		String encodedPassword = passwordEncoder.encode(plainPassword);
        userAdmin.setPassword(encodedPassword);		
		
		this.modelUserAdmin.addUserAdmin(userAdmin);
		model.addAttribute("listUserAdmin",modelUserAdmin.getAllUserAdmin());
		
		
		return "redirect:/login";
	}
	
	
	@GetMapping("/useradmin/update/{id}")
	public String viewUpdateUserAdmin(@PathVariable String id, Model model) {
		
		UserAdmin userAdmin = modelUserAdmin.getUserAdminById(id);
		// buat penampung data adminuser di halaman htmlnya
		model.addAttribute("userAdmin",userAdmin);
		
		return "add_useradmin";
	}
	
	@GetMapping("/useradmin/delete/{id}")
	public String deleteUserAdmin(@PathVariable String id, Model model) {
		
		this.modelUserAdmin.deleteUserAdmin(id);
		model.addAttribute("listUserAdmin",modelUserAdmin.getAllUserAdmin());
		
		
		return "redirect:/useradmin/view";
	}

}