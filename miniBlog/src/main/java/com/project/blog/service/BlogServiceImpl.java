package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.domain.BlogDto;
import com.project.blog.domain.BlogFileDto;
import com.project.blog.domain.SearchDto;
import com.project.blog.repository.BlogFileMapper;
import com.project.blog.repository.BlogMapper;

@Service
public class BlogServiceImpl implements BlogService {

	@Autowired
	BlogMapper blogMapper;

	@Autowired
	BlogFileMapper blogFileMapper;

	@Override
	public int  insertBlog(BlogDto blog) {
		return blogMapper.insertBlog(blog);
	}

	@Override
	public List<BlogDto>selectAllList(SearchDto search){
		return blogMapper.selectAllList(search);
	}

	@Override
	public void deleteOne(int b_id) {
		blogMapper.deleteOne(b_id);
	}

	@Override
	public void deleteSelect(List<Integer> checkBoxArr) {
		blogMapper.deleteSelect(checkBoxArr);
	}

	@Override
	public BlogDto selectById(int b_id) {
		return blogMapper.selectById(b_id);
	}

	@Override
	public void updateBlog(BlogDto blog) {
		blogMapper.updateBlog(blog);
	}

	@Override
	public int selectCnt(SearchDto search) {
		return blogMapper.selectCnt(search);
	}


	  @Override
	  public void insertBlogFile(BlogFileDto blogFile) {
		  blogFileMapper.insertBlogFile(blogFile);
	}

	@Override
	public void deleteBlogFile(int f_id) {
		blogFileMapper.deleteBlogFile(f_id);

	}



}
