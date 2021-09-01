package com.abromand.mweapp.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/base")
public class BaseController {

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
