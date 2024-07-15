package day0527;

import java.util.List;

import org.apache.ibatis.exceptions.PersistenceException;
import org.apache.ibatis.session.SqlSession;

import kr.co.sist.dao.MyBatisDAO;
import kr.co.sist.domain.EmpDomain;

public class ExamMapper4DAO {

	private static ExamMapper4DAO em4DAO;
	
	private ExamMapper4DAO() {
	
	}//ExamMapper4DAO
	
	public static ExamMapper4DAO getInstance() {
		if(em4DAO == null) {
			em4DAO= new ExamMapper4DAO();
		}//end if
		return em4DAO;
	}//getInstance
	
	/**
	 * 컬럼 하나에 한 행 조회
	 * @param empno 사원번호
	 * @return 사원명
	 * @throws PersistenceException
	 */
	
	public String scsr(int empno )throws PersistenceException{
		String ename="";
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		ename=ss.selectOne("kr.co.sist.exam4.scsr",empno);
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return ename;
	}//scsr
	
	/**
	 * 컬럼 하나에 여러 행 조회
	 * @param deptno 부서번호
	 * @return 사원번호들
	 * @throws PersistenceException
	 */
	
	public List<Integer> scmr(int deptno )throws PersistenceException{
		List<Integer> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		list=ss.selectList("kr.co.sist.exam4.scmr",deptno);	//selectList가 new() 처리를 하기에 list에 null처리가 가능
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//scmr
	
	public EmpDomain mcsr(int empno )throws PersistenceException{
		EmpDomain ed=null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		ed=ss.selectOne("kr.co.sist.exam4.mcsr",empno);
		if(ed != null){
		ed.setEmpno(empno);
		}//end if
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return ed;
	}//mcsr
	
	
	public List<EmpDomain> mcmr(int deptno )throws PersistenceException{
		List<EmpDomain> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		list=ss.selectList("kr.co.sist.exam4.mcmr",deptno);	//selectList가 new() 처리를 하기에 list에 null처리가 가능
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
	}//mcmr
	
	public List<EmpDomain> lessThan(int sal )throws PersistenceException{
		List<EmpDomain> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		list=ss.selectList("kr.co.sist.exam4.lessThan",sal);
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
		
	}//lessThan
	
	public List<EmpDomain> greaterThan(int sal )throws PersistenceException{
		List<EmpDomain> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		list=ss.selectList("kr.co.sist.exam4.greaterThan",sal);
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
		
	}//greaterThan
	
	public List<EmpDomain> like( String ename )throws PersistenceException{
		List<EmpDomain> list= null;
		
		//1.MyBatis Handler 얻기
		MyBatisDAO mbDAO= MyBatisDAO.getInstance();
		SqlSession ss= mbDAO.getMyBatisHandler(false);
		//2.쿼리문 실행
		list=ss.selectList("kr.co.sist.exam4.like",ename);
		//3.MyBatis Handler 닫기
		mbDAO.closeHandler(ss);
		
		return list;
		
	}//like
	
	public static void main(String[] args) {
		ExamMapper4DAO.getInstance().like("A");
		
	}
	
}
