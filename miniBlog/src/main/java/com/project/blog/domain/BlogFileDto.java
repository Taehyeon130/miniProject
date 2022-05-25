package com.project.blog.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BlogFileDto {

	private int f_id; //파일 아이디
	private int b_id; //글 아이디 FK
	private String f_oriName; //원본파일명
	private String f_saveName; //파일 저장명
	private String f_type; //파일 속성
	private String f_path; //파일저장경로
	private int f_size; //사이즈
	private String f_delYn; //삭제여부
	private Date f_inTime; //등록일시
	private Date f_delTime; //삭제일시
	//등록자
	//수정자 = 삭제자


	public BlogFileDto() {}

	public BlogFileDto(String f_oriName, String f_saveName, String f_type,int f_size) {
		this.f_oriName = f_oriName;
		this.f_saveName = f_saveName;
		this.f_type = f_type;
		this.f_size = f_size;
	}

}
