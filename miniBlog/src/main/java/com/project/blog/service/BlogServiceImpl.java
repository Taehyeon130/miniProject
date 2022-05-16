package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.domain.BlogDto;
import com.project.blog.mapper.BlogMapper;


@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogMapper blogMapper;

	@Override
	public void insertBlog(BlogDto blog) {
		System.out.println("여기는 서비스"+blog);
		blogMapper.insertBlog(blog);
	}
}
