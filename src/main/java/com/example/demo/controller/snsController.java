package com.example.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
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
		
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, user.getName(), user.getPassword());
		
//		model.addAttribute("name", user.getName());
//		model.addAttribute("password", user.getName());
		
//        return userList.size() > 0 ? "home" : "error";
		System.out.println(list.size());
		
		if (list.size() == 1) {
			Map<String,Object> u = list.get(0);
            Users principal = new Users();
            principal.setId((Integer) u.get("id"));
            principal.setName((String) u.get("name"));
            model.addAttribute("principal",principal);
			return "home";
		}else {
			return "redirect:/login";
		}
	}
	
	@RequestMapping(path = "/login/new", method = RequestMethod.GET)
	public String newUser(Model model) {
		return "users/user_form";
	}
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String saveNewUser(Users user, Model model) {
		
		
		String sql = "INSERT INTO users (name, password)"
                + " VALUES (?, ?)";
        
		jdbcTemplate.update(sql, user.getName(), user.getPassword());

		return "login";
	}
	
	@RequestMapping(path = "/home/edit/{id}", method = RequestMethod.GET)
	public String editUser(@PathVariable("id") Integer id, Model model) {
		
		String sql = "SELECT * FROM users WHERE id = ?";
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql, id);
		if (list.size() == 1) {
			Map<String,Object> u = list.get(0);
            Users user = new Users();
            user.setId((Integer) u.get("id"));
            user.setName((String) u.get("name"));
            model.addAttribute("user",user);
			return "users/editUser_form";
		}else {
			return "redirect:/login";
		}
	}
	

}
