package kr.co.sist.user.board;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kr.co.sist.dao.DbConnection;

public class ReplyDAO {
	
	private static ReplyDAO bDAO;
	
	private ReplyDAO() {
	}
	
	public static ReplyDAO getInstance() {
		if(bDAO == null) {
			bDAO=new ReplyDAO();
		}//end if
		return bDAO;
	}//getInstance

	
	public List<ReplyVO> selectReply( int refNum )throws SQLException{
		List<ReplyVO> list=new ArrayList<ReplyVO>();
		
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		
		DbConnection db=DbConnection.getInstance();
		
		try {
		//1. JNDI 사용객체 생성
		//2. DataSource 얻기
		//3. Connection 얻기
			con=db.getConn("jdbc/dbcp");
		//4. 쿼리문 생성객체 얻기
			StringBuilder selectReply = new StringBuilder();
			selectReply.append("	select /*+ index_desc(reply pk_reply) */	")
			.append("	num, content, writer, input_date, ip from reply	")
			.append("	where ref_num=?	");
			
			pstmt = con.prepareStatement(selectReply.toString());
			
		//5. 바인드변수에 값 설정
			pstmt.setInt(1, refNum);
			
		//6. 쿼리문 수행 후 결과얻기
			rs=pstmt.executeQuery();
			
			ReplyVO rVO=null;
			while(rs.next()) {
				rVO=new ReplyVO(rs.getInt("num"), refNum,
						rs.getString("content"), rs.getString("writer"),
						rs.getString("ip"), rs.getDate("input_date"));
				
				list.add(rVO);
			}//end while
			
		}finally {
		//7. 연결 끊기
			db.closeCon(rs, pstmt, con);
		}//end finally
		return list;
	}//selectBoard
	
	public void insertReply( ReplyVO rVO)throws SQLException{
		
		Connection con=null;
		PreparedStatement pstmt=null;
		
		DbConnection db=DbConnection.getInstance();
		
		try {
		//1. JNDI 사용객체 생성
		//2. DataSource 얻기
		//3. Connection 얻기
			con=db.getConn("jdbc/dbcp");
		//4. 쿼리문 생성객체 얻기
			String insertReply = 
			"insert into reply(num, ref_num, content, writer, ip) values(seq_reply.nextval, ?, ?, ?, ?)";
			
			pstmt = con.prepareStatement(insertReply);
			//5. 바인드 변수에 값 설정
			pstmt.setInt(1, rVO.getNum());
			pstmt.setString(2, rVO.getContent());
			pstmt.setString(3, rVO.getWriter());
			pstmt.setString(4, rVO.getIp());
			
			pstmt.executeUpdate();
			
		}finally {
		//7. 연결 끊기
			db.closeCon(null, pstmt, con);
		}//end finally
	}//insertBoard
	
	
	public int deleteReply( int num ) throws SQLException {
		int cnt = 0;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		DbConnection db = DbConnection.getInstance();
		
		try {
			// 1. JNDI 사용객체 생성
			// 2. DataSource 얻기
			// 3. Connection 얻기
			con = db.getConn("jdbc/dbcp");
			// 4. 쿼리문 생성객체 얻기(Dynamic Query)
			
			StringBuilder deleteBoard = new StringBuilder();
			deleteBoard
			.append("	delete from reply	")
			.append("	where num=?	");
			
			pstmt = con.prepareStatement(deleteBoard.toString());
			
			// 바인드 변수에 값 설정
			pstmt.setInt(1, num);
			
			cnt = pstmt.executeUpdate();
			
		} finally {
			// 7. 연결 끊기
			db.closeCon(null, pstmt, con);
		} // end finally
		return cnt;
	}//updateBoard
	
	
	
	
}//class











