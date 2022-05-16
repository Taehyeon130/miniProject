package com.project.blog.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.project.blog.domain.MemberDTO;


@Mapper
public interface MemberMapper {
	List<MemberDTO> select();

}
