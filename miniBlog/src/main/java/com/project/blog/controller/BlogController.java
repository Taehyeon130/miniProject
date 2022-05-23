package com.project.blog.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.project.blog.domain.BlogDto;
import com.project.blog.domain.SearchDto;
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
		 System.out.println("컨트롤러에서"+param); //currentPage랑 b_id값 넘어와

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
		  System.out.println("수정하기 컨트롤러 dto"+blogdto);
		  blogService.updateBlog(blogdto);
		  ModelAndView mv = new ModelAndView("redirect:/detail/blog/?b_id="+blogdto.getB_id()+"&currentPage="+blogdto.getCurrentPage());
		  return mv;
	  }

	  //선택 삭제하기
	  @PostMapping(value="/select/delete")
	  @ResponseBody
	  //VO 객체를 JSON으로 바꿔서 http body에 담는 스프링 어노테이션
	  //메서드의 return값을 http response의 body에 담는 역할을 함
	  //요청한 형태에 맞춰서 메시지 변환기를 통해 결과를 반환함
	  //@ResponseBody는 @RequestBody가 선택한 형식으로 결과값을 반환한다고 생각!
	  //@ResponseBody를 이욯하면 자바 객체를 HTTP응답 body로 전송할 수 있음
	  public String selectDelete(@RequestBody BlogDto blog) {
		  //Http body안에 JSON을 VO에 매핑하는 스프링 어노테이션
		  //즉 http요청 body를 자바 객체로 전달받을 수 있음
		  System.out.println("컨트롤러"+blog.getCheckBoxArr());
		  blogService.deleteSelect(blog.getCheckBoxArr());
		  return "blogList";
	  }

	  //검색하기
	  @PostMapping(value="/search/blogList")
	  @ResponseBody
	  public ModelAndView searchBlog(SearchDto search) {
		  System.out.println("search나와"+search);

		  ModelAndView mv = new ModelAndView("redirect:/show/blogList");

		  mv.addObject("blogList",blogService.searchBlog(search));
		  return mv;
	  }


}