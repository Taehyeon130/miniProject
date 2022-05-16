package com.project.blog.service;

import java.util.List;

import com.project.blog.domain.BlogDto;


public interface BlogService {
	//등록
	public void insertBlog(BlogDto blog);

	//전체 조회
	public List<BlogDto> selectAllBlog();
}
