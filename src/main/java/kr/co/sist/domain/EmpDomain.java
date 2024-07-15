package kr.co.sist.domain;

import java.sql.Date;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class EmpDomain {
	private int empno, sal, comm, deptno;	//ctrl+shift+y 소문자, ctrl+shift+x 대문자 
	private String ename, hiredateStr, job;
	private Date hiredate;
	
	

	
	
}
