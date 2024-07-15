<%@page import="kr.co.sist.user.member.WebMemberVO"%>
<%@page import="kr.co.sist.user.board.BoardDomain"%>
<%@page import="java.util.List"%>
<%@page import="kr.co.sist.user.board.BoardDAO"%>
<%@page import="java.sql.SQLException"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="게시판 글작성"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 //개발의 편의성을 위해서 로그인 한 것 처럼 코드를 작성 한 후 작업진행.
 WebMemberVO wmVO=new WebMemberVO();
 wmVO.setId("kim");
 
 session.setAttribute("loginData", wmVO);

%>
<c:if test="${ empty sessionScope.loginData }">
	<c:redirect url="http://192.168.10.230/jsp_prj/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="icon" href="http://192.168.10.230/jsp_prj/common/favicon.ico"/>
<!--bootstrap 시작-->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<!--bootstrap 끝-->
<link rel="stylesheet" href="http://192.168.10.230/jsp_prj/common/css/main.css" type="text/css" media="all" />
<link rel="stylesheet" href="http://192.168.10.230/jsp_prj/common/css/board.css" type="text/css" media="all" />

<!--jQuery CDN 시작-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!--jQuery CDN 끝-->

<!-- summernote 시작 -->
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<!-- summernote 끝 -->

<style type="text/css">
	#wrap{ width: 1462px;height: 749px; margin: 0px auto; }
	#header{ height: 100px; 
	background: #FFFFFF url('http://localhost/jsp_prj/common/images/header.png') no-repeat; }
		
</style>
<script type="text/javascript">
	$(function(){
		$("#btnList").click(function(){
			history.back();
		});//click
		$("#btnWrite").click(function(){
			chkNull();
		});//click
		
	});//ready
	
	function chkNull(){
		
		if($("#title").val().trim()==""){
			alert("제목은 필수입력");
			$("#title").focus();
			return;
		}//end if
		
		if($("#content").val().trim()==""){
			alert("내용은 필수입력");
			$("#content").focus();
			return;
		}//end if
		
		if($("#cnt").val().trim()==""){
			$("#cnt").val( 0 );
		}
		
		$("#frmWirte").submit();
		

		
		
	}//chkNull
</script>

  <script>
  $(function(){ 
    $('#content').summernote({
      placeholder: '${ sessionScope.loginData.id }님 아무말이나 싸질러 주세요.',
      tabsize: 2,
      width:600,
      height: 200,
      toolbar: [
        ['style', ['style']],
        ['font', ['bold', 'underline', 'clear']],
        ['color', ['color']],
        ['para', ['ul', 'ol', 'paragraph']],
        ['table', ['table']],
        ['insert', ['link', 'picture', 'video']],
        ['view', ['fullscreen', 'codeview', 'help']]
      ]
    });//summernote
  });//ready
  </script>

</head>
<body>
<div id="wrap">
<div id="header"></div>
<div id="boardContent">
	
	<form method="post" action="board_write_frm_process.jsp" name="frmWirte" 
	 id="frmWirte">
	 <table>
	 <tr>
	 	<td colspan="2"><h3>글쓰기</h3></td>
	 </tr>
	 <tr>
	 	<td>제목</td>
	 	<td>
	 	<input type="text" name="title" id="title" style="width: 600px"/>
	 	</td>
	 </tr>
	 <tr>
	 	<td>내용</td>
	 	<td>
	 	<textarea id="content" name="content"></textarea>
	 	</td>
	 </tr>
	 <tr>
	 	<td>조회수</td>
	 	<td>
	 	<input type="text" name="cnt" id="cnt" value="0" style="width: 600px"/>
	 	</td>
	 </tr>
	 <tr>
	 	<td>작성자</td>
	 	<td><strong><c:out value="${ sessionScope.loginData.id }"/></strong></td>
	 </tr>
	 <tr>
	 	<td colspan="2" style="text-align: center;">
	 	<input type="button" value="글작성" class="btn btn-success btn-sm" id="btnWrite"/>
	 	<input type="button" value="글목록" class="btn btn-success btn-sm" id="btnList"/>
	 	</td>
	 </tr>
	 </table>
	
	
	</form>
</div>
</div>
</body>
</html>




