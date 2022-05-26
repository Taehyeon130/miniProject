package com.project.blog.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.project.blog.domain.BlogDto;
import com.project.blog.domain.SearchDto;
import com.project.blog.service.BlogService;

@Controller
public class BlogController {

	@Autowired
	private BlogService blogService;

	//등록폼 보여주기
	  @GetMapping(value="/blogForm")
	  public String blogForm(int currentPage,Model model, HttpServletRequest request){
		  String searchCondi = request.getParameter("searchCondi");
		  String searchText = request.getParameter("searchText");

		  model.addAttribute("searchCondi",searchCondi);
		  model.addAttribute("searchText",searchText);
		  model.addAttribute("currentPage", currentPage);
		  return "blogForm";
	 }

		//블로그 등록
	  @PostMapping(value="/insert/blog")
	  public ModelAndView insertBlog(MultipartFile b_file,BlogDto blogdto, HttpServletRequest request){
		  String searchCondi = request.getParameter("searchCondi");
		  String searchText = request.getParameter("searchText");
		  String currentPage = request.getParameter("currentPage");

		  blogService.insertBlog(blogdto);

		  //비어있을때
		  if(!b_file.isEmpty()) {
			  BlogDto file = new BlogDto(b_file.getOriginalFilename(),
					  UUID.randomUUID().toString(), b_file.getContentType(), (int)b_file.getSize());

						file.setB_id(blogdto.getB_id());

						File f = new File("C:/uploadFile/"+file.getF_saveName()+"_"+file.getF_oriName());
			  			try {
			  				b_file.transferTo(f);
			  				blogService.insertBlogFile(file);

			  			} catch (IllegalStateException e) {
			  				e.printStackTrace();
			  			} catch (IOException e) {
			  				e.printStackTrace();
			  			}
		  }

		  ModelAndView mv = new ModelAndView("redirect:/detail/blog/?b_id="+blogdto.getB_id()+"&currentPage="+Integer.parseInt(currentPage)+"&searchCondi="+searchCondi+"&searchText="+searchText);
		  return mv;
	  }



	  //하나만삭제
	  @PostMapping(value="/delete/blog")
	  @ResponseBody
	  public String deleteOne(@RequestBody Map<String, String> param) {
		 System.out.println("컨트롤러에서"+param); //currentPage랑 b_id값 넘어와

		 int b_id = Integer.parseInt(param.get("b_id"));
		 int f_id = Integer.parseInt(param.get("f_id"));

		  blogService.deleteOne(b_id);
		  blogService.deleteBlogFile(f_id);

		  return "blogList";
	  }

	  //전체조회
	  @GetMapping(value="/show/blogList")
	  public ModelAndView AllListView(
			  @RequestParam(value = "currentPage", required = false, defaultValue = "1") int currentPage,
	            @RequestParam(value = "cntPerPage", required = false, defaultValue = "10") int cntPerPage,
	            @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize,
	            @RequestParam(value = "searchCondi", required = false, defaultValue="") String searchCondi,
	            @RequestParam(value = "searchText", required = false, defaultValue="") String searchText,
			  HttpServletRequest request) throws Exception{

		  ModelAndView mav = new ModelAndView("blogList");

		  SearchDto search = new SearchDto(currentPage, cntPerPage, pageSize);

		  search.setSearchCondi(searchCondi);
		  search.setSearchText(searchText);

		  int listCnt = blogService.selectCnt(search); //전체개수 조회했구
		  search.setTotalPageCount(listCnt);

		  System.out.println("전체 페이지 수 "+search.getTotalPageCount());

		  mav.addObject("pagination",search);
		  mav.addObject("blogList",blogService.selectAllList(search));
		  mav.addObject("totalCnt", listCnt);
		  return mav;
	  }



	  //상세보기
	  @GetMapping(value="/detail/blog")
	  public String detailBlog(int b_id, int currentPage, Model model, HttpServletRequest request) {

		  String searchCondi = request.getParameter("searchCondi");
		  String searchText = request.getParameter("searchText");

		  model.addAttribute("searchCondi",searchCondi);
		  model.addAttribute("searchText",searchText);
		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("currentPage", currentPage);
		  return "blogDetail";
	  }

	  //에딧 폼 보여주기
	  @GetMapping(value="/editForm")
	  public String showEdit(int b_id, int currentPage, Model model, HttpServletRequest request) {
		  String searchCondi = request.getParameter("searchCondi");
		  String searchText = request.getParameter("searchText");

		  model.addAttribute("searchCondi",searchCondi);
		  model.addAttribute("searchText",searchText);

		  model.addAttribute("blogDetail",blogService.selectById(b_id));
		  model.addAttribute("currentPage", currentPage);
		  return "blogEdit";
	  }

	  //수정하기
	  @PostMapping(value="/update/blog")
	  public ModelAndView updateBlog(MultipartFile b_file, BlogDto blogdto, Model model, HttpServletRequest request) {

		  String searchCondi = request.getParameter("searchCondi");
		  String searchText = request.getParameter("searchText");

		  System.out.println("수정하기 컨트롤러 file"+b_file);
		  blogService.updateBlog(blogdto);

		  //비어있을때
		  if(!b_file.isEmpty()) {
			  BlogDto file = new BlogDto(b_file.getOriginalFilename(),
					  UUID.randomUUID().toString(), b_file.getContentType(), (int)b_file.getSize());

						file.setB_id(blogdto.getB_id());

						File f = new File("C:/uploadFile/"+file.getF_saveName()+"_"+file.getF_oriName());
			  			try {
			  				b_file.transferTo(f);
			  				blogService.insertBlogFile(file);

			  			} catch (IllegalStateException e) {
			  				e.printStackTrace();
			  			} catch (IOException e) {
			  				e.printStackTrace();
			  			}
		  }

		  ModelAndView mv = new ModelAndView("redirect:/detail/blog/?b_id="+blogdto.getB_id()+"&currentPage="+blogdto.getCurrentPage()+"&searchCondi="+searchCondi+"&searchText="+searchText);
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



		  //파일 다운로드 하기
		  @GetMapping(value="/downloadFile")
		  public ResponseEntity<Object>downloadFile(HttpServletRequest request) throws UnsupportedEncodingException
		  {
			  //String f_id = request.getParameter("f_id");
			  String f_oriName = request.getParameter("f_oriName");
			  String f_saveName = request.getParameter("f_saveName");

			  //System.out.println("다운로드 받을 f_id"+f_id+"이름:"+f_oriName+"저장된 이름"+f_saveName);

			  String path = "C:/uploadFile/"+f_saveName+"_"+f_oriName;

			  try {
				  Path filePath = Paths.get(path);
				  InputStreamResource resource = new InputStreamResource(Files.newInputStream(filePath));
				 // File file = new File(path);

				  String fileName= URLEncoder.encode(f_oriName,StandardCharsets.UTF_8).replaceAll("\\+", "%20");

				  HttpHeaders headers = new HttpHeaders();
				  headers.setContentDisposition(ContentDisposition.builder("attachment").filename(fileName).build());
				  return new ResponseEntity<Object>(resource, headers, HttpStatus.OK);
			  	} catch(IOException e) {
					  return new ResponseEntity<Object>(null,HttpStatus.CONFLICT);
				 }
		  }

		  //파일 삭제하기
		  @PostMapping(value="/deleteFile")
		  @ResponseBody
		  public String  deleteBlogFile(@RequestBody Map<String, String> param) {
			  int f_id = Integer.parseInt(param.get("f_id"));
			  blogService.deleteBlogFile(f_id);
			  return "blogEdit";
		  }





}