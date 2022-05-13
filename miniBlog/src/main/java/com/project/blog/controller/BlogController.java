package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BlogController {
	//테스트용
	@RequestMapping(value="/binsert")
		  public String Blog(){

		  return "blogForm";
	  }

	  @RequestMapping(value="/insert/Blog")
	  public String insertBlog(Model model){



	  return "blogForm";
  }
}
