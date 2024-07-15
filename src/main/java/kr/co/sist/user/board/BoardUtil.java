package kr.co.sist.user.board;

public class BoardUtil {

	private static BoardUtil bu;
	
	private BoardUtil() {
		
	}
	
	public static BoardUtil getInstance() {
		if(bu == null) {
			bu = new BoardUtil();
		}//end if
		
		return bu;
	}//getInstance
	
	public String pageNation(String url, String param, int totalPage, int currentPage) {
		
		StringBuilder pageNation = new StringBuilder();
		//1. 한 화면에서 보여줄 페이지 인덱스 수
		int pageNumber = 3; //3개씩 보여줄 것임!
		
		//2. 화면에 보여줄 시작 페이지의 번호 설정
		int startPage = ( (currentPage-1) / pageNumber ) * pageNumber + 1;
		
		//3. 화면에 보여줄 마지막 페이지 번호 설정
		int endPage = ( ( (startPage - 1) + pageNumber ) / pageNumber ) * pageNumber;
		
		//4. 총 페이지의 수가 연산된 마지막페이지 수보다 작다면 총 페이지 수를 마지막 페이지 번호롤 설정
		if(totalPage <= endPage) {
			endPage = totalPage;
		}//end if
		
		//5. 첫 페이지가 인덱스 화면이 아닌 경우
		String prevMark = "[ << ]";
		int movePage = 0;
		
		if(currentPage > pageNumber) { //시작페이지보다 1적은 페이지로 이동
			movePage = startPage - 1;
			prevMark = "[ <a href='" + url + "?currentPage=" + movePage + param + "'> &lt;&lt; </a> ]";
		}//end if
		
		pageNation.append(prevMark).append(" ... ");
		
		//6. 시작 페이지 번호부터 끝 페이지 번호까지 화면에 출력
		
		movePage = startPage;
		while( movePage <= endPage ) {
			if( movePage == currentPage ) { //현재 페이지에 대해서는 링크를 생성하지 않는다.
				pageNation.append("[ <span style='font-size: 20px'>")
				.append(currentPage).append("</span> ]");
			}else {
				pageNation.append("[ <a href='").append(url)
				.append("?currentPage=").append(movePage).append(param).append("'>") 
				.append(movePage).append("</a> ]");
			}//end else
			movePage++;
		}//end while
		
		//7. 뒤에 페이지가 더 있는 경우
		String endMark = "[ &gt;&gt; ]";
		if( totalPage > endPage ) {
			movePage = endPage + 1;
			endMark = "[ <a href='board_list.jsp?currentPage=" + movePage + param 
					+ "'> &gt;&gt;</a> ]";
		}//end if
		
		pageNation.append(" ... ").append(endMark);
		
		return pageNation.toString();
	}
	
}//class
