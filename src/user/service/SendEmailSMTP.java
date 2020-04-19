/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package user.service;

import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author fedi
 */
public class SendEmailSMTP {

    public static void sendMail(String EMAIL_TO,String EMAIL_SUBJECT,String EMAIL_TEXT ) {

          // Recipient's email ID needs to be mentioned.
     // String to = "lahbib.fedi@gmail.com";//change accordingly

      // Sender's email ID needs to be mentioned
      //String from = "moez.taieb@esprit.tn";//change accordingly
      final String username = "pidevesprit2020@gmail.com";//change accordingly
      final String password = "esprit.2020";//change accordingly

      // Assuming you are sending email through relay.jangosmtp.net
      String host = "smtp.gmail.com";

      Properties props = new Properties();
      props.put("mail.smtp.auth", "true");
      props.put("mail.smtp.starttls.enable", "true");
      props.put("mail.smtp.host", host);
      props.put("mail.smtp.port", "587");

      // Get the Session object.
      Session session = Session.getInstance(props,
      new javax.mail.Authenticator() {
         protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(username, password);
         }
      });

      try {
         // Create a default MimeMessage object.
         Message message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(username));

         // Set To: header field of the header.
         message.setRecipients(Message.RecipientType.TO,
         InternetAddress.parse(EMAIL_TO));

         // Set Subject: header field
         message.setSubject(EMAIL_SUBJECT);

         // Now set the actual message
         message.setText(EMAIL_TEXT);

         // Send message
         Transport.send(message);

         System.out.println("Sent message successfully....");

      } catch (MessagingException e) {
          
                   System.out.println("Sent message error....");

            throw new RuntimeException(e);
      }
   }
}
