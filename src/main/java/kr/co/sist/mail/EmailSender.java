package kr.co.sist.mail;

import java.io.FileInputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

import kr.co.sist.util.cipher.DataDecrypt;
import kr.co.sist.vo.EmailVO;

public class EmailSender {
	
    public void mailSend(EmailVO eVO) throws NoSuchAlgorithmException, GeneralSecurityException, IOException {
    	
    	Properties prop = new Properties();
    	//C:\dev\workspace\mybatis_prj\src\main\java\properties\email.properties
    	System.out.println(System.getProperty("user.dir"));
    	
    	try {
    		FileInputStream fis = 
    			new FileInputStream(System.getProperty("user.dir") + 
    					"/src/main/java/properties/email.properties");
    		prop.load(fis); //경로상에 존재하는 properties파일을 읽어들인다.
    		if( fis != null ) { fis.close(); }//end if
    	} catch(IOException e) {
    		e.printStackTrace();
    	}//end catch
    	
    	
    	// 발신자 이메일 계정 정보
        String username = prop.getProperty("username"); // 발신자 네이버 이메일 주소
        String password = prop.getProperty("password"); // 발신자 네이버 이메일 비밀번호

        // SMTP 서버 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); //SMTP 서버 인증을 사용하는 설정 (true - 외부에서 인증설정)
        props.put("mail.smtp.starttls.enable", "true"); //TLS보안을 사용한 연결 설정
        props.put("mail.smtp.host", "smtp.naver.com"); //SMTP서버의 호스트명 설정 smtp.naver.com
        props.put("mail.smtp.port", "587"); //SMTP port는 587

        // 세션 생성 : 이메일 메시지를 구성하고 전송하는 데 사용
        //Authenticator : SMTP서버 인증에 필요한 사용자명과 비번을 전달하는 클래스
        DataDecrypt dd = new DataDecrypt("a1234567890abcde");
        
        final String username2 = dd.decryption(username);
        final String password2 = dd.decryption(password);
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username2, password2);
            }
        });

        try {
            // 메시지 구성
        	//Message : 메시지의 헤더 설정 - 발신자, 수신자, 제목, 내용 - 메타데이터를 설정
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username2)); // 발신자 이메일
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse( eVO.getEmail() )); // 수신자 이메일
            message.setSubject( eVO.getSubject() ); // 이메일 제목
            
            //message.setText(content.toString()); // 이메일 내용
            message.setContent(eVO.getContent(), "text/html;charset=UTF-8");

            // 이메일 전송
            //Transport : SMTP 서버와 통신하여 이메일 메시지를 전송하는 역할
            Transport.send(message);

        } catch (MessagingException e) {
            e.printStackTrace(); // 디버깅을 위해 스택 트레이스 출력
            throw new RuntimeException(e);
        }//end catch
    }//mailSend
    
    public static void main(String[] args) throws NoSuchAlgorithmException, GeneralSecurityException, IOException {
		EmailSender e = new EmailSender();
		EmailVO eVO = new EmailVO(0, "jimin5305@naver.com", "djakgjal", "djalkfja");
		e.mailSend(eVO);
	}
}//class