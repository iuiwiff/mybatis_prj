package day0523;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.util.cipher.DataEncrypt;
import kr.co.sist.vo.EmailVO;

public class UseMyBatisHandlerDAO {
	private static UseMyBatisHandlerDAO umhDAO;
	
	private UseMyBatisHandlerDAO() {
		
	}
	
	public static UseMyBatisHandlerDAO getInstance() {
		if(umhDAO == null) {
			umhDAO = new UseMyBatisHandlerDAO();
		}
		return umhDAO;
	}//getinstance
	
	public int insertSeq() {
		int cnt = 0;
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리문 수행 : parameterType 속성이 없는 insert
		cnt = ss.insert("kr.co.sist.exam2.insertTestMyBatis");
		if(cnt == 1) {
			ss.commit();
		}
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		return cnt;
	}
	
	public int insertSeq(int num) throws PersistenceException{
		int cnt = 0;
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리문 수행 : parameterType="int" 속성이 있는 insert (단일형으로 하나의 값을 추가)
		cnt = ss.insert("kr.co.sist.exam2.insertTestMyBatis2",num);
		if(cnt == 1) {
			ss.commit();
		}
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		return cnt;
	}
	
	public int insertEmail(EmailVO eVO) throws PersistenceException{
		int cnt = 0;
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDao = MyBatisDAO.getInstance();
		//2. 쿼리문 수행(Mapper.xml에서 id를 찾고 parsing 하여 값을 넣고 쿼리문을 실행)
		//SqlSession ss = mbDao.getMyBatisHandler(false); // 수동커밍필요
		SqlSession ss = mbDao.getMyBatisHandler(true); // auto commit임
		cnt = ss.insert("kr.co.sist.exam2.insertTestMyBatis3",eVO);
		//3. MyBatis Handler 닫기
		mbDao.closeHandler(ss);
		
		
		return cnt;
	}
	
	public static void main(String[]args) throws UnsupportedEncodingException, NoSuchAlgorithmException, GeneralSecurityException {
		DataEncrypt de = new DataEncrypt("a1234567890abcde");
		System.out.println(de.encryption("jimin5305@naver.com"));
		System.out.println(de.encryption("GTRH1BQ2R2E7"));
		
//		try {
//			EmailVO emVO = new EmailVO(1, "ywcsuwon@naver.com", "메일테스트", "잘사냐?");
//			UseMyBatisHandlerDAO.getInstance().insertSeq(2024);
//			UseMyBatisHandlerDAO.getInstance().insertEmail(emVO);
//			
//		}catch(PersistenceException pe) {
//			System.out.println("예외발생");
//			pe.printStackTrace();
//		}
	}
}