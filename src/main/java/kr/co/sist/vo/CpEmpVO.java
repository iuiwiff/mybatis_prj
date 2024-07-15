package kr.co.sist.vo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class CpEmpVO {
	
	private int empno, sal, comm, deptno;
	private String ename, job;
	private Date hiredate;

}
