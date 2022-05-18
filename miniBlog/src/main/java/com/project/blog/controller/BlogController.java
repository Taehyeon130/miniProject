package com.project.blog.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		  blogService.insertBlog(blogdto);
		  ModelAndView mv = new ModelAndView("redirect:/show/blogList");
		  System.out.println("여기는 컨트롤러" + blogdto.getB_id());
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
	  public String blogList(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size) {

		  int currentPage = page.orElse(1);
		  int pageSize = size.orElse(10);

		  Page<BlogDto> blogPage = blogService.findPaginated(PageRequest.of(currentPage-1, pageSize));

		  model.addAttribute("blogPage",blogPage);

		  int totalPages = blogPage.getTotalPages();
		  if(totalPages > 0) {
			  List<Integer> pageNumbers = IntStream.rangeClosed(1,totalPages)
					  .boxed()
					  .collect(Collectors.toList());
			  model.addAttribute("pageNumbers", pageNumbers);
		  }

		  model.addAttribute("totalCnt",blogService.selectCnt());
		  //model.addAttribute("blogList",blogService.selectAllBlog());
		  model.addAttribute("currentPage",currentPage);
		  return "blogList";
	  }

	  //상세보기
	  @GetMapping(value="/detail/blog")
	  public String detailBlog(int b_id, int page, Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("page",page);
		  return "blogDetail";
	  }

	  //에딧 폼 보여주기
	  @GetMapping(value="/editForm")
	  public String showEdit(int b_id, int page, Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("page",page);
		  return "blogEdit";
	  }

	  //수정하기
	  @PostMapping(value="/update/blog")
	  public ModelAndView updateBlog(BlogDto blogdto, int page, Model model) {
		  model.addAttribute("page", page);
		  blogService.updateBlog(blogdto);
		  System.out.println("컨트롤러 "+page);
		  ModelAndView mv = new ModelAndView("redirect:/detail/blog/?b_id="+blogdto.getB_id()+"&page="+page);
		  return mv;
	  }

	  //선택 삭제하기
	  @PostMapping(value="/select/delete")
	  public String selectDelete(@RequestParam(value="checkBoxArr[]") List<Integer>ids) {
		  for(Integer id:ids) blogService.deleteOne(id);
		  return "blogList";
	  }

}