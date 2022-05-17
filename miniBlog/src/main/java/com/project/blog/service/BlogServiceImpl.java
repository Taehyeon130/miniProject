package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.domain.BlogDto;
import com.project.blog.repository.BlogMapper;


@Service
public class BlogServiceImpl implements BlogService{


	@Autowired
	BlogMapper blogMapper;

	@Override
	public void insertBlog(BlogDto blog) {
		System.out.println("여기는 서비스"+blog);
		blogMapper.insertBlog(blog);
	}

	@Override
	public List<BlogDto> selectAllBlog(){
		return blogMapper.selectAllBlog();
	}

	@Override
	public void deleteOne(int b_id) {
		blogMapper.deleteOne(b_id);
	}


	@Override
	public BlogDto selectById(int b_id){
		return blogMapper.selectById(b_id);
	}

	@Override
	public void updateBlog(BlogDto blog) {
		blogMapper.updateBlog(blog);
	}

	 @Override
	 public int totalRecord() {
		 return blogMapper.totalRecord();
	 }




}
