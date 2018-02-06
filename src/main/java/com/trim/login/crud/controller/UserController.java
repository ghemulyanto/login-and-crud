package com.trim.login.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.trim.login.crud.model.User;
import com.trim.login.crud.service.UserService;

@Controller
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/add")
	public String createUser(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "users/user_add";
	}

	@PostMapping("/add")
	public String addUser(@Valid User user, BindingResult result) {

		User userExist = userService.findUserByUsername(user.getUsername());
		if (userExist != null) {
			result.rejectValue("username", "error.user",
					"There is already user registered with username "
							+ user.getUsername());
		}
		if (result.hasErrors()) {
			return "users/user_add";
		} else {
			userService.saveUser(user);
			user = new User();
			return "users/user_add";
		}

	}

}
