<%@page import="day0530.ExamMapper6DAO"%>
<%@page import="kr.co.sist.domain.CarDomain"%>
<%@page import="day0529.ExamMapper5Service"%>
<%@page import="kr.co.sist.domain.JoinDomain"%>
<%@page import="day0529.ExamMapper5DAO"%>
<%@page import="kr.co.sist.domain.EmpDomain"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="choose 조회"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form name="frm" action="index.jsp">
	<input type="hidden" name="link" value="day0530/choose"/>
	<h2>사원정보 조회</h2>
	<label>선택</label>
	<%  String num =request.getParameter("num"); %>
	<input type="radio" value="1" name="num">10번 부서
	<input type="radio" value="2" name="num">20번 부서
	<input type="radio" value="3" name="num">30번 부서
	<input type="submit" value="검색" class="btn btn-success btn-sm"/>
</form>
<div>
<c:if test="${not empty param.num }">
검색 결과<br>
<c:catch var="e">
<%
ExamMapper6DAO em6DAO=ExamMapper6DAO.getInstance();

List<EmpDomain> list=em6DAO.dynamicChoose(Integer.parseInt(num));
pageContext.setAttribute("list", list);
%>
<table class="table table-hover">
<tr> 
<th style="width: 120px">번호</th>
<th style="width: 100px">사원번호</th>
<th style="width: 200px">사원명</th>
<th style="width: 150px">직무</th>
<th style="width: 100px">연봉</th>
<th style="width: 150px">입사일</th>
</tr>
<c:if test="${empty list }">
<tr>
<td colspan="6" style="text-align: center">
사원이 존재하지 않습니다.
</td>
</tr>
</c:if>
<c:forEach var="ed" items="${list }" varStatus="i" >
<tr>
<td><c:out value="${ i.count }"/></td>
<td><c:out value="${ ed.empno }"/></td>
<td><c:out value="${ ed.ename }"/></td>
<td><c:out value="${ ed.job }"/></td>
<td><fmt:formatNumber pattern="#,###,###" value="${ ed.sal }"/>만원</td>
<td><fmt:formatDate pattern="yyyy-MM-dd" value="${ ed.hiredate }"/></td>
</tr>
</c:forEach>
</table>
<option value="${empno }"><c:out value="${i.count}.${empno }" /></option>
</c:catch>
<c:if test="${not empty e}">
문제 발생
</c:if>
</c:if>
</div>
