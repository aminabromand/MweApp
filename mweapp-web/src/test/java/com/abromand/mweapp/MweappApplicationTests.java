package com.abromand.mweapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class MweappApplicationTests {

  @Test
  void contextLoads() {
  }

  @Autowired
  PasswordEncoder passwordEncoder;

  @Test
  void encodePassword() {

    String password;
    password = "init101";
    System.out.println(password + ": " + passwordEncoder.encode(password));
    password = "init102";
    System.out.println(password + ": " + passwordEncoder.encode(password));

  }

}
