package com.project.blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.domain.BlogDto;
import com.project.blog.repository.BlogMapper;


@Service
public class BlogServiceImpl implements BlogService{

	@Autowired
	BlogMapper blogMapper;

	@Override
	public void insertBlog(BlogDto params) {
		blogMapper.insertBlog(params);
	}
}
