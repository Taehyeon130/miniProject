<h1>springboot + thymeleaf + mybaits + mysql로 게시판 구현하기<h1>


<h2>1. DB 구성</h2><br>
  <h4>MYSQL</h4><br>
database명 : study_db<br>
username : TESTUSER<br>
password : surf131<br>
<br>
<br>
<br>
/*------------blog------------*/<br>
create table blog(	<br>
	b_id int NOT NULL AUTO_INCREMENT,<br>
	b_title varchar(50) ,<br>
	b_author varchar(20) ,<br>
	b_content text ,<br>
	b_show int ,<br>
	b_search varchar(100) ,<br>
	b_cate varchar(50) ,<br>
	delYn VARCHAR(10),<br>
	b_modDate<br>
	b_date TIMESTAMP,<br>
	CONSTRAINT b_PK PRIMARY KEY(b_id)<br>
)default charset=utf8 collate utf8_general_ci;	<br>
  <br>
  <br>
  

/*------------file------------*/<br>
create table bFile(<br>
	f_id int not null auto_increment primary key,<br>
	b_id int not null,<br>
	f_oriName varchar(260) ,<br>
	f_saveName varchar(40),<br>
	f_type varchar(100),<br>
	f_path varchar(200),<br>
	f_size int  ,<br>
	f_delYn varchar(10),<br>
	f_inTime TIMESTAMP,<br>
    f_delTime TIMESTAMP<br>
)default charset=utf8 collate utf8_general_ci;<br>
<br>
alter table bFile add constraint fk_f_id foreign key (b_id) references blog(b_id);<br>

<h2>2. 진행상황</h2><br>
  - 게시판 글 CRUD구현 완료<br>
  - 페이징 <br>
  - 파일 업로드, 다운로드<br>
  - 제목, 작성자, 내용을 이용한 검색<br>
  
<h2>3. 보완사항</h2><br>
  - enum 이용<br>
  - 오류처리 (예를 들어 파일 업로드 오류시 사용자가 white page를 보지 못하도록)<br>
  - 검색속도 고민해보기<br>
  - spring security를 이용해서 권한 생성해보기<br>
  - bFile테이블의 path이용하기<br>
  
<h2>4. 노력</h2><br>
  - 페이징 스스로 해보기<br>
  - crud, 페이징, 검색, 파일 첨부 모두 3~5일 걸리도록<br>
  
