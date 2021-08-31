package com.abromand.mweapp.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

  @GetMapping(produces = MediaType.TEXT_PLAIN_VALUE)
  public String index() {
    return "index";
  }


}
