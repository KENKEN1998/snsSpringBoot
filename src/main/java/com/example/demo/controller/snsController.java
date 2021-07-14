package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.model.Users;



@Controller
public class snsController {
	@Autowired
	JdbcTemplate jdbcTemplate;

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String homePage(HttpSession session, Model model) {

		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String index(Users user,
			HttpSession session, Model model) {

		String sql = "SELECT * FROM users WHERE name = ? AND password = ?";
		
		List<Map<String, Object>> userList = jdbcTemplate.queryForList(sql, user.getName(), user.getPassword());
		
		model.addAttribute("name", user.getName());
		model.addAttribute("password", user.getName());
        return userList.size() > 0 ? "home" : "error";

	}
	
	@RequestMapping(path = "/login/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		
		Users user = new Users();
		
		model.addAttribute("user", user);
		model.addAttribute("pageTitle", "Create New User");
		
		return "users/user_form";
	}

}
