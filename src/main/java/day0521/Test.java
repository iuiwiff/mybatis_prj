package day0521;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Test {

	public static void main(String[] args) throws IOException{
		Reader reader = Resources.getResourceAsReader("day0522/mybatis-config.xml");
		SqlSessionFactory ssf = new SqlSessionFactoryBuilder().build(reader);
		SqlSession ss = ssf.openSession();
		List<DeptDomain> list = ss.selectList("kr.co.sist.selectDeptNo");
		for(DeptDomain dd : list) {
			System.out.println(dd.getDeptno() + " / " + dd.getDname() + " / " + dd.getLoc());
		}
		ss.close();
	}
}
