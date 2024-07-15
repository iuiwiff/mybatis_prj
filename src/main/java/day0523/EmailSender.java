package day0523;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailSender {
    public static void main(String[] args) {
    	 // 발신자 이메일 계정 정보
        final String username = ""; // 발신자 네이버 이메일 주소
        final String password = ""; // 발신자 네이버 이메일 비밀번호

        // SMTP 서버 설정
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.naver.com");
        props.put("mail.smtp.port", "587");

        // 세션 생성 : 이메일 메시지를 구성하고 전송하는 데 사용
        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // 메시지 구성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("")); // 발신자 이메일
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("")); // 수신자 이메일
            message.setSubject("네이버를 사용한 메일 테스트입니다."); // 이메일 제목
            message.setText("안녕메일 테스트 고객님의 아이디는 xxx입니다."); // 이메일 내용

            // 이메일 전송
            Transport.send(message);

            System.out.println("Email sent successfully!");

        } catch (MessagingException e) {
            e.printStackTrace(); // 디버깅을 위해 스택 트레이스 출력
            throw new RuntimeException(e);
        }
    }
}