package com.project.blog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

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
	  public ModelAndView insertBlog(BlogDto blogdto){
		  ModelAndView mv = new ModelAndView("redirect:/show/blogList");
		  System.out.println("여기는 컨트롤러" + blogdto);
		  blogService.insertBlog(blogdto);
		  return mv;
	  }


	  //하나만삭제
	  @PostMapping(value="/delete/blog")
	  public String deleteOne(int b_id) {
		  blogService.deleteOne(b_id);
		  return "blogList";
	  }

	  //리스트 보여주기
	  @GetMapping(value="/show/blogList")
	  public String blogList(Model model) {
		  model.addAttribute("blogList",blogService.selectAllBlog());
		  return "blogList";
	  }



	  //상세보기
	  @GetMapping(value="/detail/blog")
	  public String detailBlog(int b_id,Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  return "blogDetail";
	  }

	  //에딧 폼 보여주기
	  @GetMapping(value="/editForm")
	  public String showEdit(int b_id, Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  return "blogEdit";
	  }

}
