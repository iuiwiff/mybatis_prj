<%@page import="kr.co.sist.domain.EmpDomain"%>
<%@page import="day0527.ExamMapper4DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="컬럼 여러개에 한 행 조회"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form name="frm" action="index.jsp">
	<input type="hidden" name="link" value="day0527/mcsr"/>
	<h2>사원번호에 해당하는 사원정보 조회</h2>
	<label>사원번호</label>
	<input type="text" name="empno"/>
	<input type="text" style="display: none"/>
	<input type="submit" value="검색" class="btn btn-success btn-sm"/>
</form>
<div>
입력하신<strong><c:out value="${param.empno}"/></strong>번 사원 검색결과<br>
<c:catch var="e">
<c:if test="${not empty param.empno }">
<%
	int empno=Integer.parseInt(request.getParameter("empno"));
ExamMapper4DAO em4DAO=ExamMapper4DAO.getInstance();
EmpDomain ed=em4DAO.mcsr(empno);
pageContext.setAttribute("ed", ed);
%>
</c:if>
<label>사원명: </label><input type="text" name="ename" value="${ed.ename }"/><br>
<label>직무: </label><input type="text" name="job" value="${ed.job }"/><br>
<label>연봉: </label><input type="text" name="sal" value="${ed.sal }"/><br>
<label>입사일: </label><input type="text" name="hiredate" readonly="readonly" value='<fmt:formatDate value="${ed.hiredate }" pattern="yyyy-MM-dd" />'/><br>
</c:catch>
<c:if test="${not empty param.empno and empty ed}">
<span style="color: #ff0000"> 사원번호가 존재하지 않습니다.</span>
</c:if>
<c:if test="${not empty e }">
사원번호는 숫자로만 구성됩니다.
</c:if>
</div>
