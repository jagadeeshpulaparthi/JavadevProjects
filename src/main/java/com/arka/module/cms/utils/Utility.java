package com.arka.module.cms.utils;

import java.util.Properties;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


/**
 * @author Surekha
 *
 */

public class Utility {
	
	/**
	 * @author acer
	 *
	 */
	
	public static String sendMailtoUser(String to_Mail,String text,String subject) throws Exception{
//		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
//		mailSender.setHost("smtp.gmail.com");
//		mailSender.setPort(587);
//		mailSender.setUsername("noreplysbts@gmail.com");
//		mailSender.setPassword("abap@123");
//		Properties properties = new Properties();
//		properties.setProperty("mail.smtp.auth", "true");
//		properties.setProperty("mail.smtp.starttls.enable", "true");
//		properties.setProperty("mail.smtp.ssl.trust", "smtp.gmail.com");
//		mailSender.setJavaMailProperties(properties);
//		
//		String from = "noreplysbts@gmail.com";		 
//		SimpleMailMessage message = new SimpleMailMessage();
//		 
//		message.setFrom(from);
//		message.setTo(to_Mail);
//		message.setSubject(subject);
//		message.setText(text);
//		mailSender.send(message);
//		return subject;
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		mailSender.setHost("smtp.zoho.com");
		mailSender.setPort(465);
		mailSender.setUsername("support@arkatiss.com");
		mailSender.setPassword("Arkatiss@123");
		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.smtp.starttls.enable", "true");
		properties.setProperty("mail.smtp.ssl.trust", "smtp.zoho.com");
		properties.setProperty("mail.smtp.socketFactory.port","465");
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.socketFactory.fallback", "true");
		mailSender.setJavaMailProperties(properties);
		
		String from = "support@arkatiss.com";		 
		SimpleMailMessage message = new SimpleMailMessage();
		 
		message.setFrom(from);
		message.setTo(to_Mail);
		message.setSubject(subject);
		message.setText(text);
		mailSender.send(message);
		return subject;
	}
	/**
	 *  genarate 6 digits otp number
	 * @return opt number
	 */
	public static String genrateOTP(){
		Random random=new Random();
		String otp=String.valueOf(random.nextInt(1000000));
		while(otp.length()<6){
			otp=random.nextInt(10)+otp;
		}
		String OTP=otp;
		return OTP;
	}
	
	public static String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijk"
          +"lmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

}
