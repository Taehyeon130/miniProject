package com.project.blog.paging;

import lombok.Data;

@Data
public class Pagination{

	private int currentPage;  //현재페이지
	private int cntPerPage;	//페이지당 출력할 페이지 갯수
	private int pageSize; 		//화면 하단 페이지 사이즈 1~10, 10~20 ..
	private int totalRecordCount; //전체 데이터 개수
	private int totalPageCount; 	//전체 페이지 개수
	private int firstPage;				//페이지 리스트의 첫 페이지 번호
	private int lastPage;				//페이지 리스트의 마지막 페이지 번호
	private int firstRecordIndex;	//SQL의 조건절에 사용되는 첫 RNUM
	private int lastRecordIndex;	//SQL의 조건절에 사용되는 마지막 RNUM
	private boolean hasPreviousPage; //이전페이지 존재여부
	private boolean hasNextPage; 		//다음 페이지 존재여부


	public Pagination(int currentPage, int cntPerPage, int pageSize) {
		//강제 입력 방지
		if(currentPage<1) {
			currentPage=1;
		}

		//10,20,30개 단위 이외 처리방지
		if(cntPerPage != 10 && cntPerPage != 20 && cntPerPage != 30) {
			cntPerPage=20;
		}

		//하단 페이지 갯수 10개로 제한
		if(pageSize != 10) {
			pageSize =10;
		}
		this.currentPage = currentPage;
		this.cntPerPage = cntPerPage;
		this.pageSize = pageSize;
	}

	public void setTotalPageCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;

		if(totalRecordCount>0) {
				calculation();
		}else {

		}
	}

	public void calculation() {

		//전체 페이지수(현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수를 저장
		totalPageCount = ((totalRecordCount-1)/this.getCntPerPage())+1;
		if(this.getCurrentPage() > totalPageCount) {
			this.setCurrentPage(totalPageCount);
		}

		//페이지 리스트의 첫 번호
		firstPage = ((this.getCurrentPage()-1)/this.getPageSize())*this.getPageSize()+1;

		//페이지 리스트의 마지막 페이지 번호
		lastPage = firstPage + this.getPageSize()-1;
		if(lastPage > totalPageCount) {
			lastPage = totalPageCount;
		}

		//SQL의 조건절에 사용되는 첫번째 RNUM
		firstRecordIndex = (this.getCurrentPage()-1)*this.getCntPerPage();
		if(firstRecordIndex != 1) firstRecordIndex += 1;

		//SQL조건절에 사용되는 마지막 RNUM
		lastRecordIndex = this.getCurrentPage() * this.getCntPerPage();

		//이전 페이지 존재 여부
		hasPreviousPage = firstPage ==1 ? false: true;
		if(hasPreviousPage ==false) {
			if(currentPage != firstPage) {
				hasPreviousPage = true;
			}else {
				hasPreviousPage = false;
			}
		}

		//다음 페이지 존재 여부
		hasNextPage = (lastPage * this.getCntPerPage()) >= totalRecordCount ? false :true;
		if(hasNextPage == false) {
			//마지막 페이지에서 현재 페이지가 마지막 페이지가 아닌경우 next처리
			if(currentPage != lastPage) {
				hasNextPage= true;
			}else {
				hasNextPage = false;
			}
		}
	}
}
