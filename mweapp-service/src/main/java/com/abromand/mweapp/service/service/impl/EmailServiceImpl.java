package com.abromand.mweapp.service.service.impl;

import com.abromand.mweapp.service.dto.VerificationTokenDto;
import com.abromand.mweapp.service.exception.MweServiceException;
import com.abromand.mweapp.service.service.EmailService;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {
  @Value("${mweapp.email.user}")
  private String emailUser;

  @Value("${mweapp.email.password}")
  private String emailPassword;

  @Value("${mweapp.email.port}")
  private int emailPort;

  @Value("${mweapp.email.host}")
  private String emailHost;

  @Value("${mweapp.base-url.gui:http://localhost:8081}")
  private String baseUrl;

  public void sendVerificationEmail(VerificationTokenDto tokenDto) {

    String msg = "To set your password, go to: " + baseUrl + "/#/gui/settokenpassword?token="
        + tokenDto.getTokenString();

    String host = emailHost;
    int port = emailPort;
    String username = emailUser;
    String password = emailPassword;

    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.starttls.required", "true");
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);

    prop.put("mail.smtp.ssl.trust", host);
    prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(tokenDto.getEmail()));

      message.setSubject("MweApp Reset Password");

      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(msg, "text/html");

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);

      message.setContent(multipart);

      Transport.send(message);

    } catch (Exception e) {
      e.printStackTrace();
      throw new MweServiceException("email error");
    }
  }
}
