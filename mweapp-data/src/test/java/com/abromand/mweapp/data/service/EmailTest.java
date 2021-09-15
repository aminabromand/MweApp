package com.abromand.mweapp.data.service;

import static org.junit.Assert.fail;

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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class EmailTest {

  @Test
  public void sendMail() {

    String host = "smtp.strato.de";
    int port = 587;
    String username = "mweapp@abromand.com";
    String password = "YPyJxJ1HMQJNbiD5cxZa";



    Properties prop = new Properties();
    prop.put("mail.smtp.auth", true);
    prop.put("mail.smtp.starttls.enable", "true");
    prop.put("mail.smtp.starttls.required", "true");
    prop.put("mail.smtp.host", host);
    prop.put("mail.smtp.port", port);

    prop.put("mail.smtp.ssl.trust", host);
    prop.put("mail.smtp.ssl.protocols", "TLSv1.2");

//    prop.put("mail.smtp.socketFactory.port", port);
//    prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//    prop.put("mail.smtp.socketFactory.fallback", "false");

    Session session = Session.getInstance(prop, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(username, password);
      }
    });

    try {

      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(username));
      message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("amin.abromand@metaproc.com"));
      message.setSubject("Mail Subject: MWE APP 5");

      String msg = "This is my first email using JavaMailer";

      MimeBodyPart mimeBodyPart = new MimeBodyPart();
      mimeBodyPart.setContent(msg, "text/html");

//      MimeBodyPart attachmentBodyPart = new MimeBodyPart();
//      attachmentBodyPart.attachFile(new File("pom.xml"));

      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(mimeBodyPart);
//      multipart.addBodyPart(attachmentBodyPart);

      message.setContent(multipart);

      Transport.send(message);

    } catch (Exception e) {
      e.printStackTrace();
      fail();
    }

  }

}
