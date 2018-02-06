package com.trim.login.crud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.trim.login.crud.model.User;
import com.trim.login.crud.service.UserService;

@Controller
public class RegistrationController {

	@Autowired
	UserService userService;

	@GetMapping("/registration")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("registration");
		return modelAndView;
	}

	@PostMapping("/registration")
	public ModelAndView saveUser(@Valid User user, BindingResult result) {
		ModelAndView modelNView = new ModelAndView();
		User userExist = userService.findUserByEmail(user.getEmail());
		if (userExist != null) {
			result.rejectValue("email", "error.user",
					"There is alredy a user registered with the email provide");
		}

		if (result.hasErrors()) {
			modelNView.setViewName("registration");
		} else {
			userService.saveUser(user);
			modelNView.addObject("successMessage",
					"User has beent registered successfully");
			modelNView.addObject("user", new User());
			modelNView.setViewName("registration");
		}
		return modelNView;
	}

}
