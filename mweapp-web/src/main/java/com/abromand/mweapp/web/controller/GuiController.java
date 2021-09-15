package com.abromand.mweapp.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping(GuiController.MAPPING)
public class GuiController {

  public static final String MAPPING = "/gui";

  @RequestMapping("/**/{path:[^.]*}")
  public ModelAndView redirect() {
    return new ModelAndView("forward:/");
  }

}
