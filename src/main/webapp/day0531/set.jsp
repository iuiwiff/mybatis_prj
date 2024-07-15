<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="day0531.ExamMapper7DAO"%>
<%@page import="day0530.ExamMapper6DAO"%>
<%@page import="kr.co.sist.domain.CarDomain"%>
<%@page import="day0529.ExamMapper5Service"%>
<%@page import="day0529.ExamMapper5DAO"%>
<%@page import="kr.co.sist.domain.JoinDomain"%>
<%@page import="kr.co.sist.domain.EmpDomain"%>
<%@page import="java.util.List"%>
<%@page import="day0527.ExamMapper4DAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="choose사용 조회"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form name="frm" action="index.jsp">
	<input type="hidden" name="link" value="day0531/set"/>
	<h2>사원정보 변경</h2>
	<label>사원번호</label>
	<input type="text" name="empno" value="7654"><br>
	<label>사원명</label>
	<input type="text" name="ename" value="MARTIN"><br>
	<label>연봉</label>
	<input type="text" name="sal"><br>
	<label>직무</label>
<% 	String deptno=request.getParameter("empno"); 
	String[] jobs="SALESMAN,ANALYST,CLERK,MANAGER,PRESIDENT".split(",");
	pageContext.setAttribute("jobs", jobs);
%>
<select name="job">
<c:forEach var="job" items="${jobs }">
<option value="${job }"<%-- ${job eq param.job: 'selected':'selected'":""}  --%>><c:out value="${job}"/></option>
</c:forEach>
</select>
<input type="submit" value="검색" class="btn btn-success btn-sm"/>
</form>
<div>

<c:if test="${not empty param.empno }">
<c:out value="${param.empno }"/>사원정보가 <br>
<c:catch var="e">
<jsp:useBean id="ceVO" class="kr.co.sist.vo.CpEmpVO" scope="page"/>
<jsp:setProperty property="*" name="ceVO"/>
<%
ExamMapper7DAO em7DAO=ExamMapper7DAO.getInstance();
HashMap<String, Object> map= new HashMap<String, Object>();
map.put("deptno",deptno);
map.put("jobList",request.getParameterValues("job"));

int cnt=em7DAO.dynamicUpdate(ceVO);
pageContext.setAttribute("cnt", cnt);
%>
<c:choose>
<c:when test="${cnt eq 0 }">
변경되지 않았습니다.<br> 사원번호를 확인해주세요
</c:when>
<c:otherwise>
변경되었습니다.
</c:otherwise>
</c:choose>
<c:out value="${cnt eq 0?'변경되지 않았습니다.':'' }"/>
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
<td><c:out value="${i.count }"/></td>
<td><c:out value="${ed.empno }"/></td>
<td><c:out value="${ed.ename }"/></td>
<td><c:out value="${ed.job }"/></td>

<td><fmt:formatNumber pattern="#,###,###" value="${ed.sal}"/>만원</td>
<td><fmt:formatDate pattern="yyyy-MM-dd" value="${ed.hiredate }"/></td>
<!-- <td><c:out value="${ed.hiredate }"/></td>-->
</tr>
</c:forEach>
</table>
<option value="${empno }"><c:out value="${i.count}.${empno }" /></option>
</c:catch>
<c:if test="${not empty e}">
문제발생
</c:if>
</c:if>
</div>
