package kr.co.sist.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class JoinDomain {
	private int empno, sal, deptno;
	private String ename, dname, loc;
}
