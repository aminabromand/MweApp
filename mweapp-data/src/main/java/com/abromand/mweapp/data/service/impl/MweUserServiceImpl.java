package com.abromand.mweapp.data.service.impl;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.dto.VerificationTokenDto;
import com.abromand.mweapp.data.exception.MweServiceException;
import com.abromand.mweapp.data.mapper.MweUserMapper;
import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.model.VerificationToken;
import com.abromand.mweapp.data.repository.MweUserRepository;
import com.abromand.mweapp.data.service.MweUserService;
import com.abromand.mweapp.data.util.NullAwareBeanUtilsBean;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.UUID;
import java.util.stream.Collectors;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MweUserServiceImpl implements MweUserService {

  @Value("${mweapp.base-url.gui:http://localhost:8081}")
  private String baseUrl;

  @Autowired
  MweUserRepository userRepository;

  @Autowired
  MweUserMapper userMapper;

  @Autowired
  NullAwareBeanUtilsBean nullAwareBeanUtilsBean;

  @Override
  public List<MweUserDto> findAll() {

    return userRepository.findAll().stream()
        .map(userMapper::mweUser2MweUserDto)
        .collect(Collectors.toList());

  }

  @Transactional
  @Override
  public MweUserDto patch(Long id, MweUserDto dto)
      throws InvocationTargetException, IllegalAccessException {
    MweUser mweUser = userRepository.getById(id);
    dto.setCsb(mweUser.isCsb());
    dto.setCto(mweUser.isCto());
    nullAwareBeanUtilsBean.copyProperties(mweUser, dto);
    return userMapper.mweUser2MweUserDto(userRepository.save(mweUser));
  }

  @Override
  public VerificationTokenDto generateVerificationToken(String email) {
//      String init102encrypted = "$2a$10$d2cabNZUoC43e.OJ4TDx/.Z6TQ.8U.5nzvV5js1n1m37JhnDAr/By";

    Optional<MweUser> mweUserOptional = userRepository.findByEmail(email);
    if (mweUserOptional.isEmpty()) {
      throw new MweServiceException("user not found");
    }

    String tokenString = UUID.randomUUID().toString();
    VerificationToken token = new VerificationToken();
    token.setToken(tokenString);
    token.setUser(mweUserOptional.get());


    return new VerificationTokenDto(init102encrypted, email);

  }

  private static String bytesToHex(byte[] hash) {
    StringBuilder hexString = new StringBuilder(2 * hash.length);
    for (byte b : hash) {
      String hex = Integer.toHexString(0xff & b);
      if (hex.length() == 1) {
        hexString.append('0');
      }
      hexString.append(hex);
    }
    return hexString.toString();
  }

  public void sendVerificationEmail(VerificationTokenDto tokenDto) {

    String msg = "Your token is: " + tokenDto.getTokenString();

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

      message.setSubject("Mail Subject: MWE APP 5");

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
