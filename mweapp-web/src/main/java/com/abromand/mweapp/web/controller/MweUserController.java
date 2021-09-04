package com.abromand.mweapp.web.controller;

import static com.abromand.mweapp.web.security.WebSecurityConfiguration.API_BASE_MAPPING;

import com.abromand.mweapp.data.dto.MweUserDto;
import com.abromand.mweapp.data.model.MweUser;
import com.abromand.mweapp.data.service.MweUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_BASE_MAPPING + MweUserController.USER_MAPPING)
public class MweUserController {

  public static final String USER_MAPPING = "/user";

  @Autowired
  MweUserService userService;

  @Autowired
  ObjectMapper objectMapper;

  @GetMapping
  public List<MweUserDto> findAll() {
    return userService.findAll();
  }

//  @PreAuthorize("hasAnyRole('ROLE_CSB','ROLE_CTO')")
//  @PreAuthorize("hasAuthority('CSB')")
  @Secured({"ROLE_CSB","ROLE_CTO"})
  @PatchMapping("/{id}")
  public void patch(
      @PathVariable("id") final Long id,
      @RequestBody Map<String, String> patch)
      throws InvocationTargetException, IllegalAccessException {
//      @RequestHeader(name = HttpHeaders.IF_MATCH) String etag) {
    MweUserDto dto = objectMapper.convertValue(patch, MweUserDto.class);
    userService.patch(id, dto);
  }

}
