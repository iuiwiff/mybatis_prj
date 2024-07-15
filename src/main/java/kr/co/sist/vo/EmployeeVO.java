package kr.co.sist.vo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class EmployeeVO {
	private int empno, sal, cnt;
	private String ename, job, errMsg;
}
