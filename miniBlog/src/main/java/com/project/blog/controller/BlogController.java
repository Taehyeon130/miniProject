package com.project.blog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.blog.domain.BlogDto;
import com.project.blog.paging.Pagination;
import com.project.blog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//등록폼 보여주기
	  @GetMapping(value="/blogForm")
	  public String blogForm(int currentPage,Model model){
		  model.addAttribute("currentPage", currentPage);
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
	  @ResponseBody
	  public String deleteOne(@RequestBody Map<String, String> param) {
		 System.out.println("컨트롤러에서"+param.get("b_id"));

		 int b_id = Integer.parseInt(param.get("b_id"));

		  blogService.deleteOne(b_id);
		  return "blogList";
	  }

	  //전체조회
	  @GetMapping(value="/show/blogList")
	  public ModelAndView AllListView(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
	            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
			  BlogDto blog, HttpServletRequest request) throws Exception{

		  ModelAndView mav = new ModelAndView("blogList");


		  int listCnt = blogService.selectCnt(); //전체개수 조회했구
		  Pagination pagination = new Pagination(currentPage, cntPerPage, pageSize);
		  pagination.setTotalPageCount(listCnt);

		  mav.addObject("pagination",pagination);
		  mav.addObject("blogList",blogService.selectAllList(pagination));
		  mav.addObject("totalCnt", blogService.selectCnt());

		  System.out.println("컨트롤러 pagination값" +pagination);
		  System.out.println("컨트롤러 blogList값 pagination" +blogService.selectAllList(pagination));
		  return mav;
	  }



	  //상세보기
	  @GetMapping(value="/detail/blog")
	  public String detailBlog(int b_id, int currentPage, Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("currentPage", currentPage);
		  return "blogDetail";
	  }

	  //에딧 폼 보여주기
	  @GetMapping(value="/editForm")
	  public String showEdit(int b_id, int currentPage, Model model) {
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("currentPage", currentPage);
		  return "blogEdit";
	  }

	  //수정하기
	  @PostMapping(value="/update/blog")
	  public ModelAndView updateBlog(BlogDto blogdto, Model model) {
		  blogService.updateBlog(blogdto);
		  ModelAndView mv = new ModelAndView("redirect:/detail/blog/?b_id="+blogdto.getB_id());
		  return mv;
	  }

	  //선택 삭제하기
	  @PostMapping(value="/select/delete")
	  @ResponseBody
	  public String selectDelete(@RequestBody BlogDto blog) {
		  System.out.println("컨트롤러"+blog.getCheckBoxArr());
		  blogService.deleteSelect(blog.getCheckBoxArr());
		  return "blogList";
	  }

}