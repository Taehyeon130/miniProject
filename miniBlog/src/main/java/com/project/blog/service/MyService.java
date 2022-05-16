package com.project.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.blog.domain.MemberDTO;
import com.project.blog.repository.MemberMapper;

@Service
public class MyService {
	@Autowired
	private MemberMapper memberMapper;

	public List<MemberDTO> select() throws Exception{
		return memberMapper.select();
	}
}
