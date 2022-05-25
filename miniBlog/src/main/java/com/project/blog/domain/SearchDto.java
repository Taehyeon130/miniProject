package com.project.blog.domain;


import com.project.blog.paging.Pagination;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
public class SearchDto extends Pagination{

	private String searchCondi = ""; //검색조건
	private String searchText = ""; //검색

	public SearchDto(int currentPage, int cntPerPage, int pageSize) {
		super(currentPage, cntPerPage, pageSize);
	}

}
