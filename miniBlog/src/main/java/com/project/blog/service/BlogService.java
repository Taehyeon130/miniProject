package com.project.blog.service;

import java.util.List;

import com.project.blog.domain.BlogDto;
import com.project.blog.domain.SearchDto;
import com.project.blog.paging.Pagination;




public interface BlogService {
	//등록
	public void insertBlog(BlogDto blog);

	//전체조회
	List<BlogDto> selectAllList(Pagination pagination) throws Exception;

	//하나만 삭제
	 public void deleteOne(int b_id);

	 //선택삭제
	 public void deleteSelect(List<Integer>checkBoxArr);

	//상세보기
	BlogDto selectById(int b_id);

	 //수정하기
	 public void updateBlog(BlogDto blog);


	 //갯수
	 public int selectCnt();

	 //조건검색
	 List<BlogDto> searchBlog(SearchDto search);

}
