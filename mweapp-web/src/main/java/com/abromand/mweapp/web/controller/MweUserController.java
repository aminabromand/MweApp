package com.abromand.mweapp.web.controller;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.service.impl.MweUserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class MweUserController {

  @Autowired
  MweUserServiceImpl userService;

  @GetMapping
  public List<MweUserDto> findAll() {
    return userService.findAll();
  }

}
