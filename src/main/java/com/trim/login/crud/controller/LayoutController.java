package com.trim.login.crud.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LayoutController {

	protected String getHeaderTitle() {
		return "Home";
	}

	@GetMapping("/layout")
	public String layout(Model model) {
		return "layout";
	}

	@GetMapping("/")
	public String home(Model model) {
		return "home/home";
	}

}
