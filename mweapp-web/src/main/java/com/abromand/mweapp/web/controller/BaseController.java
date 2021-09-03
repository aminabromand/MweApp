package com.abromand.mweapp.web.controller;

import static com.abromand.mweapp.web.security.WebSecurityConfiguration.API_BASE_MAPPING;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_BASE_MAPPING + BaseController.MAPPING)
public class BaseController {

  public static final String MAPPING = "/base";

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String base() {
    System.out.println("base");
    return "base";
  }

  @GetMapping(value = "/secured", produces = MediaType.TEXT_PLAIN_VALUE)
  public String secured() {
    System.out.println("secured2");
    return "secured2";
  }


}
