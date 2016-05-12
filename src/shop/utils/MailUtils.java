package shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 * 邮件发送工具类
 * 
 * @author Programmer
 *
 */
public class MailUtils {

	/**
	 * 发送邮件的方法
	 * 
	 * @param to   ：收件人
	 * @param code ：激活码
	 */
	public static void sendMail(String to, String code) {
		//1.获得一个Session对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("service@shop.com", "111");
			}
		});
		//2.创建一个代表邮件的对象Message
		Message message = new MimeMessage(session);
		try {
			// 设置发件人
			message.setFrom(new InternetAddress("service@shop.com"));
			
			// 设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));
			
			// 设置标题
			message.setSubject("欢迎注册zzg商城！");
			
			// 设置正文
			message.setContent("<h1>zzg商城官方激活邮件！请点击下方链接完成激活操作：</h1><h3><a href='http://192.168.0.101:8080/shop/user_active.action?code="+code+"'>http://192.168.0.100:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
			
			//3.发送邮件Transport
			Transport.send(message);
		} catch (AddressException e) {
			e.printStackTrace();
		} catch (MessagingException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		sendMail("aaa@shop.com", "12346789abcdefg");
	}
}
