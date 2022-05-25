package com.project.blog.repository;

import org.apache.ibatis.annotations.Mapper;

import com.project.blog.domain.BlogFileDto;

@Mapper
public interface BlogFileMapper {

	//등록
	public void insertBlogFile(BlogFileDto blogFile);

}
