package com.java.uitbikes.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.java.uitbikes.model.LoginForm;

import jakarta.mail.internet.MimeMessage;

@Service
public class MailServiceImplement implements MailService {
	@Autowired
	JavaMailSender mailSender;
	@Autowired
	AccountService accountService;
	
	@Value("${spring.mail.username}")
	private String email;
	
	String getRandomString(int n) {
		String AlphaNumericString = 
			"ABCDEFGHIJKLMNOPQRSTUVWXYZ"
		  + "0123456789"
		  + "abcdefghijklmnopqrstuvxyz";
		
		StringBuilder sb = new StringBuilder(n);
		
		for (int i = 0; i < n; i++) {
			int idx = (int)(AlphaNumericString.length() * Math.random());
			sb.append(AlphaNumericString.charAt(idx));
		}
		
		return sb.toString();
	}
	
	@Override
	public String sendCode(String customerMail) {
		String code = getRandomString(6);
		
		try {
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(
				message, 
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name()
			);
			helper.setFrom(email);
			helper.setTo(customerMail);
			helper.setSubject("[UITBikes] - Mã xác nhận đặt lại mật khẩu");
			String content = "<p>Xin chào khách hàng,</p>\r\n"
					+ "     <p>Đây là tin nhắn từ website UIT Bikes.</p>\r\n"
					+ "     <p>Bạn đã gửi yêu cầu đặt lại mật khẩu. Đây là mã xác nhận: <b><u>" + code + "</u></b>.</p>\r\n"
					+ "     <p>Mã xác nhận có hiệu lực trong vòng <b>5 phút</b>. Vui lòng bỏ qua tin nhắn nếu bạn không gửi yêu cầu này.</p><br/>\r\n"
					+ "     <p>Trân trọng,<br/>\r\n"
					+ "     UIT Bikes</p>";
			helper.setText(content);
			
			message.setContent(content, "text/html; charset=\"utf-8\"");
			mailSender.send(message);
			return code;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Đã xảy ra lỗi! Vui lòng thử lại!";
	}
	
	@Override
	public String sendNewPassword(String customerMail) {
		String pass = getRandomString(8);
		LoginForm acc = new LoginForm(customerMail, pass);
		
		try {
			accountService.updatePassword(acc);
			
			MimeMessage message = mailSender.createMimeMessage();
			
			MimeMessageHelper helper = new MimeMessageHelper(
				message, 
				MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
				StandardCharsets.UTF_8.name()
			);
			helper.setFrom(email);
			helper.setTo(customerMail);
			helper.setSubject("[UITBikes] - Mật khẩu mới");
			String content = "<p>Xin chào khách hàng,</p>\r\n"
					+ "     <p>Đây là tin nhắn từ website UIT Bikes.</p>\r\n"
					+ "     <p>Bạn đã gửi yêu cầu đặt lại mật khẩu. Đây là mật khẩu mới của bạn: <b><u>" + pass + "</u></b>.</p>\r\n"
					+ "     <p>Để đảm bảo bảo mật tài khoản, xin vui lòng đăng nhập và đổi mật khẩu.</p><br/>\r\n"
					+ "     <p>Trân trọng,<br/>\r\n"
					+ "     UIT Bikes</p>";
			helper.setText(content);
			message.setContent(content, "text/html; charset=\"utf-8\"");
			
			mailSender.send(message);
			return "Đặt lại mật khẩu thành công!";
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "Đã xảy ra lỗi! Vui lòng thử lại!";
	}
}
