package day0529;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.domain.BoardDomain;
import kr.co.sist.domain.CarDomain;
import kr.co.sist.domain.EmpDomain;
import kr.co.sist.domain.JoinDomain;

public class ExamMapper5DAO {

	private static ExamMapper5DAO em5DAO;
	
	private ExamMapper5DAO() {
	
	}//ExamMapper4DAO
	
	public static ExamMapper5DAO getInstance() {
		if(em5DAO == null) {
			em5DAO= new ExamMapper5DAO();
		}//end if
		return em5DAO;
	}//getInstance
	
	
	public List<BoardDomain> subquery( )throws PersistenceException{
		List<BoardDomain> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행 : <select id="subquery" resultMap="bdResult">
		list=ss.selectList("kr.co.sist.exam5.subquery");
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
		
	}//subquery
	
	
	public List<JoinDomain> join(int deptno) throws PersistenceException{
		List<JoinDomain> list = null;
		
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리수행 후 결과 받기
		list = ss.selectList("kr.co.sist.exam5.join", deptno);
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//join
	
	public List<CarDomain> joinSubquery(String country) throws PersistenceException{
		List<CarDomain> list = null;
		
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리수행 후 결과 받기
		list = ss.selectList("kr.co.sist.exam5.join_subquery", country);
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//joinSubquery
	
	public List<EmpDomain> dynamicTable(String tName) throws PersistenceException{
		List<EmpDomain> list = null;
		
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리수행 후 결과 받기
		list = ss.selectList("kr.co.sist.exam5.dynamicTable", tName);
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//dynamicTable
	
	public List<EmpDomain> dynamicIf(int deptno) throws PersistenceException{
		List<EmpDomain> list = null;
		
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리수행 후 결과 받기
		list = ss.selectList("kr.co.sist.exam5.dynamicIf", deptno);
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//dynamicIf
	
	public List<EmpDomain> dynamicIf2(String job) throws PersistenceException{
		List<EmpDomain> list = null;
		
		//1. MyBatis Handler 얻기
		MyBatisDAO mbDAO = MyBatisDAO.getInstance();
		SqlSession ss = mbDAO.getMyBatisHandler(false);
		//2. 쿼리수행 후 결과 받기
		list = ss.selectList("kr.co.sist.exam5.dynamicIf2", job);
		//3. MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//dynamicIf2
	
	public static void main(String[] args) {
		ExamMapper5DAO.getInstance().dynamicIf2("MANAGER");
		
	}
	
}
