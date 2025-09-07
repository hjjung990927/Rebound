package com.example.rebound.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
public class MailService {
    private final JavaMailSender javaMailSender;

//    코드 생성
    private String createCode(){
        String codes = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String code = "";
        Random random = new Random();

        for(int i=0; i<10; i++){
            code += codes.charAt(random.nextInt(codes.length()));
        }
        return code;
    }

    public void sendMail(String mail, HttpServletRequest request, HttpServletResponse response) throws MessagingException {
        String code = createCode();

        Cookie cookie = new Cookie("code", code);
        cookie.setPath("/");
        cookie.setMaxAge(60 * 5);
        response.addCookie(cookie);

        String receiver = mail;
        String sender = "hjjung990927@gmail.com";
        String title = "Rebound 이메일 인증";

        StringBuilder body = new StringBuilder();
        body.append("<html><body>");
        body.append("<a href=\"http://13.125.213.73/mail/confirm?code=" + code + "&memberEmail=" + mail + "\">새로운 비밀번호로 변경하기</a>");
        body.append("</body></html>");

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        mimeMessageHelper.setFrom(sender);
        mimeMessageHelper.setTo(receiver);
        mimeMessageHelper.setSubject(title);
        mimeMessageHelper.setText(body.toString(), true);

        javaMailSender.send(mimeMessage);
    }
}
