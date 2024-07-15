<%@page import="java.sql.SQLException"%>
<%@page import="kr.co.sist.user.board.ReplyDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="글삭제" trimDirectiveWhitespaces="true"%>

<%
String num = request.getParameter("num");
JSONObject jsonObj = new JSONObject();
jsonObj.put("result", false);

ReplyDAO rDAO = ReplyDAO.getInstance();
try{
	int cnt = rDAO.deleteReply(Integer.parseInt(num));
	if(cnt == 1) {
		jsonObj.put("result", true);
	}//end if
}catch(NumberFormatException | SQLException e) {
	e.printStackTrace();
}

%><%= jsonObj.toJSONString() %>


