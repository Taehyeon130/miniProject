package com.project.blog.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
		System.out.println("수정"+blog);
		blogMapper.updateBlog(blog);
	}



	@Override
	public Page<BlogDto> findPaginated(Pageable pageable){
		List<BlogDto> blogs = blogMapper.selectAllBlog();

		int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();
        int startItem = currentPage * pageSize;

		List<BlogDto> list;

		 if (blogs.size() < startItem) {
	            list = Collections.emptyList();
	        } else {
	            int toIndex = Math.min(startItem + pageSize, blogs.size());
	            list = blogs.subList(startItem, toIndex);
	        }

	        Page<BlogDto> bookPage
	          = new PageImpl<BlogDto>(list, PageRequest.of(currentPage, pageSize), blogs.size());

	        return bookPage;
	}

	@Override
	 public int selectCnt() {
		return blogMapper.selectCnt();
	}


}
