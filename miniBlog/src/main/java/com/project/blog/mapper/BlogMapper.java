package com.project.blog.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.project.blog.domain.BlogDto;

@Mapper
public interface BlogMapper {
	//등록
	public void insertBlog(BlogDto blog);
}
