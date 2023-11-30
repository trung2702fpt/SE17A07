package Utils;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class Email {
    public static boolean sendEmail(String subject, String messageText, String email){
        // Thông tin tài khoản email
        final String username = "abclearneducation@gmail.com";
        final String password = "jtrrwlmnlafsxahp";

        // Cấu hình properties cho mail server
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Tạo phiên làm việc để gửi email
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Tạo đối tượng Message
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username)); // Email người gửi
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(email) // Email người nhận
            );
            message.setSubject(subject);
            message.setText(messageText);

            // Gửi email
            Transport.send(message);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public static void main(String[] args) {
        String email = "email for test";
        try {   
            sendEmail("test title", "test message", email);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
