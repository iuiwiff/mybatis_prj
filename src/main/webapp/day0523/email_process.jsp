<%@page import="kr.co.sist.mail.EmailSender"%>
<%@page import="org.apache.ibatis.exceptions.PersistenceException"%>
<%@page import="day0523.UseMyBatisHandlerDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info=""%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<!--sist스타일시트 시작--> 
<link rel="stylesheet" href="http://192.168.10.230/jsp_prj/common/css/main.css" type="text/css" media="all" /> 
<link rel="stylesheet" href="http://192.168.10.230/jsp_prj/common/css/board.css" type="text/css" media="all" /> 
<!--sist스타일시트 끝-->

<!--jQuery CDN 시작-->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.4/jquery.min.js"></script>
<!--jQuery CDN 끝-->

<style type="text/css">


</style>
<script type="text/javascript">
	$(function() {
		
	});//ready
</script>
</head>
<body>
<div>
<% request.setCharacterEncoding("UTF-8"); %>
	<jsp:useBean id="eVO" class="kr.co.sist.vo.EmailVO" scope="page"/>
	<jsp:setProperty property="*" name="eVO"/>
<%
	try{
	UseMyBatisHandlerDAO.getInstance().insertEmail(eVO);
	//email전송코드
	EmailSender es = new EmailSender();
	es.mailSend( eVO );
	%>
	<strong><c:out value="${ param.email }"/></strong>로 전송하였습니다.
	<%
	}catch(PersistenceException pe) {
		pe.printStackTrace();
	%>
	메일전송 실패!
	<%
	}//end if
	%>
%>
</div>
</body>
</html>