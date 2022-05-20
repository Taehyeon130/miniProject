package com.project.blog.domain;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BlogDto{

	private int b_id;					//아이디

	@NotBlank(message="제목은 필수값입니다.")
	private String b_title;			//제목

	@NotBlank(message="작성자는 필수값입니다.")
	private String b_author;		//작성자

	@NotBlank(message="내용은 필수값입니다.")
	private String b_content;	//내용

	private int rn; //rownum

	private int b_show;			//비공개여부
	private String b_search;		//검색노출
	private String b_cate;			//카테고리


	private Date b_date;		//작성일
	private Date b_modDate; //수정일

	private int no; // 넘버링

	private int currentPage;

	List<Integer> checkBoxArr; //선택삭제

}
