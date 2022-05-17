package com.project.blog.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.blog.domain.BlogDto;

@Mapper
public interface BlogMapper {
	//등록
	void insertBlog(BlogDto blog);

	//전체 조회
	 List <BlogDto>selectAllBlog();

	 //하나 삭제
	 void deleteOne(int b_id);

	 //상세보기
	 BlogDto selectById(int b_id);

	 //수정하기
	 void updateBlog(BlogDto blog);

}
