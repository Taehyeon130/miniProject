package com.project.blog.domain;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false) //equals : 두 객체의 내용이 같은지 동등성 hashCode : 두 객체가 같은 객체인지 동일성 >> 두개를 자동 생성해줌
public class BlogDto extends BlogFileDto{

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
	private String b_cate;			//카테고리 이걸로 enum하려는 건데


	private Date b_date;		//작성일
	private Date b_modDate; //수정일

	private int no; // 넘버링

	private int currentPage;

	List<Integer> checkBoxArr; //선택삭제

	public BlogDto() {}

	public BlogDto(String f_oriName, String f_saveName, String f_type,int f_size) {
		super(f_oriName,f_saveName,f_type,f_size);
	}


}
