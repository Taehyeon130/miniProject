package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {
	@RequestMapping(value="/")
	public String hi(Model model) {
		model.addAttribute("hi","Bonnie");

		return "board";
	}
}
