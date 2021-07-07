package com.example.demo.controller;

import java.sql.ResultSet;
import java.sql.SQLException;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
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

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String index(@PathVariable("name") String name, @PathVariable("password") String password,
			HttpSession session, Model model) {


		String sql = "SELECT * FROM users WHERE name =" + name + " AND password = " + password;

		Users user = jdbcTemplate.queryForObject(sql, new RowMapper<Users>() {
			  @Override
			    public Users mapRow(ResultSet rs, int rowNum) throws SQLException {

			        Users user = new Users();
			        user.setId(rs.getInt("ID"));
			        user.setName(rs.getString("NAME"));
			        user.setPassword(rs.getString("AGE"));
			        user.setImgpath(rs.getString("IMGPATH"));
			        return user;

			    }
		});

		model.addAttribute("name", name);
		model.addAttribute("password", password);

	return user != null ? "home" : "error";
	}

}
