package com.project.blog.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.project.blog.domain.BlogDto;




public interface BlogService {
	//등록
	public void insertBlog(BlogDto blog);

	//전체 조회
	public List<BlogDto> selectAllBlog();

	//하나만 삭제
	 public void deleteOne(int b_id);

	//상세보기
	BlogDto selectById(int b_id);

	 //수정하기
	 public void updateBlog(BlogDto blog);

	 public Page<BlogDto> findPaginated(Pageable pageable);

	 //갯수
	 public int selectCnt();

}
