package com.project.blog.domain;

import lombok.Data;

@Data
public class SearchDto {
	private String searchCondi; //검색조건
	private String searchText; //검색
}
