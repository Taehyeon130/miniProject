package com.project.blog.domain;

import lombok.Data;

@Data
public class MemberDTO {

	private int seq;
	private String mb_id;
	private String mb_pw;
	private String address;
	private String mb_tell;

}
