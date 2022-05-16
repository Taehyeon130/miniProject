package com.project.blog.repository;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.project.blog.domain.MemberDTO;


@Mapper
public interface MemberMapper {
	List<MemberDTO> select();

}
