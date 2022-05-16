package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.blog.domain.BlogDto;
import com.project.blog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//입력 폼
	  @GetMapping("/blogForm")
	  public String blogForm(){
	  return "blogForm";
  }

	  //블로그 등록
	  @PostMapping(value="/insert/blog")
	  public String insertBlog(BlogDto blogdto){
		  System.out.println("여기는 컨트롤러" + blogdto);
		  blogService.insertBlog(blogdto);
		  return "blogList";
	  }

	  @GetMapping(value="/show/blogList")
	  public String blogList(Model model) {
		  model.addAttribute("blogList",blogService.selectAllBlog());
		  return "blogList";
	  }
}
