package kr.co.sist.user.board;

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

public class ReplyVO {
	private int num, ref_num;
	private String content, writer, ip;
	private Date input_date;
}
