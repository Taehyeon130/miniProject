package com.project.blog.domain;

import java.sql.Date;

import lombok.Data;

@Data
public class BlogDto {
	private int b_id;					//아이디
	private String b_title;			//제목
	private String b_author;		//작성자
	private String b_content;	//내용
	private int b_show;			//비공개여부
	private String b_search;		//검색노출
	private String b_cate;			//카테고리
	private Date b_date;		//작성일
}
