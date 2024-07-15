<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.json.simple.JSONArray"%>
<%@page import="kr.co.sist.user.board.ReplyVO"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="kr.co.sist.user.board.ReplyDAO"%>
<%@page import="org.json.simple.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    info="리플조회" trimDirectiveWhitespaces="true"%>
<%
String refNum=request.getParameter("refNum");

JSONObject jsonObj = new JSONObject();
jsonObj.put("flag", false);
jsonObj.put("refNum", refNum);
ReplyDAO rDAO = ReplyDAO.getInstance();

try{
	List<ReplyVO> list = rDAO.selectReply(Integer.parseInt(refNum));
	jsonObj.put("flag", true);
	jsonObj.put("dataSize", list.size());
	
	JSONArray jsonArr = new JSONArray(); //조회결과를 JSONArray에 추가
	Iterator<ReplyVO> ita = list.iterator();
	
	JSONObject jsonTemp = null;
	ReplyVO rVO = null;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	while(	ita.hasNext() ) {
		rVO = ita.next();
		jsonTemp = new JSONObject();
		//레코드 하나를 JSONObject에 추가
		jsonTemp.put("num", rVO.getNum());
		jsonTemp.put("refNum", rVO.getRef_num());
		jsonTemp.put("content", rVO.getContent());
		jsonTemp.put("writer", rVO.getWriter());
		jsonTemp.put("ip", rVO.getIp());
		jsonTemp.put("input_date", sdf.format(rVO.getInput_date()));
		
		//값을 가진 JSONObject를 JSONArray에 추가
		jsonArr.add(jsonTemp);
	}//end while
	
		//JSONArray를 JSONObject에 추가
		jsonObj.put("data", jsonArr);
}catch(NumberFormatException nfe) {		
}catch(SQLException se) {
	se.printStackTrace();
}
%><%= jsonObj.toJSONString() %>
