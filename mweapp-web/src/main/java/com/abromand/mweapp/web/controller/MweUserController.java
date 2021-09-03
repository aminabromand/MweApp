package com.abromand.mweapp.web.controller;

import static com.abromand.mweapp.web.security.WebSecurityConfiguration.API_BASE_MAPPING;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.service.impl.MweUserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_BASE_MAPPING + MweUserController.USER_MAPPING)
public class MweUserController {

  public static final String USER_MAPPING = "/user";

  @Autowired
  MweUserServiceImpl userService;

  @GetMapping
  public List<MweUserDto> findAll() {
    return userService.findAll();
  }

}
