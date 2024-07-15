<%@page import="kr.co.sist.domain.BoardDomain"%>
<%@page import="day0529.ExamMapper5DAO"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="subquery 조회"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div>
<c:catch var="e">
<%
ExamMapper5DAO em5DAO=ExamMapper5DAO.getInstance();
List<BoardDomain> list=em5DAO.subquery();
pageContext.setAttribute("list", list);
%>
<c:if test="${empty list }">
사원이 존재하지 않는 부서
</c:if>
<table class="table table-hover">
<thead>
<tr> 
<th style="width: 80px">번호</th>
<th style="width: 350px">제목</th>
<th style="width: 120px">작성자</th>
<th style="width: 120px">작성일</th>
<th style="width: 100px">조회수</th>
</tr>
</thead>
<c:if test="${empty list }">
<tr>
<td colspan="5" style="text-align: center">
게시글이 존재하지 않습니다.
</td>
</tr>
</c:if>
<c:forEach var="bd" items="${list }" varStatus="i" >
<tr>
<td><c:out value="${ i.count }"/></td>
<td><c:out value="${ bd.title }"/></td>
<td><c:out value="${ bd.id }"/></td>
<td><c:out value="${ bd.input_date }"/></td>
<td><c:out value="${ bd.cnt }"/></td>
</tr>
</c:forEach>
</table>
<option value="${empno }"><c:out value="${i.count}.${empno }" /></option>
</c:catch>
<c:if test="${not empty e}">
${ e }
문제발생
</c:if>
</div>
