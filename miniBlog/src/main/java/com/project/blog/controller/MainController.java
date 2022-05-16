package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.blog.service.MyService;

@Controller
public class MainController {

	  @Autowired private MyService myService;

	  @RequestMapping(value="/test")
		  public String members(Model model) throws Exception {

		  model.addAttribute("memberList",myService.select());
		  return "board";
	  }

	  @PostMapping(value="/test/isnert")
	  public String insertTest() {

		  return "boardList";
	  }
}
