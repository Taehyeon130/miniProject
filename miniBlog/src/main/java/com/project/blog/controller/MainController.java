package com.project.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {



	  @PostMapping(value="/test/isnert")
	  public String insertTest() {

		  return "boardList";
	  }
}
