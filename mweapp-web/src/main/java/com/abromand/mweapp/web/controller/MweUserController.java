package com.abromand.mweapp.web.controller;

import static com.abromand.mweapp.web.security.WebSecurityConfiguration.API_BASE_MAPPING;

import com.abromand.mweapp.service.dto.MweUserDto;
import com.abromand.mweapp.service.dto.VerificationTokenDto;
import com.abromand.mweapp.service.service.MweUserService;
import com.abromand.mweapp.web.event.OnGeneratedVerificationTokenEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(API_BASE_MAPPING + MweUserController.USER_MAPPING)
public class MweUserController {

  public static final String USER_MAPPING = "/user";


  @Autowired
  ApplicationEventPublisher eventPublisher;

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
  public MweUserDto patch(
      @PathVariable("id") final Long id,
      @RequestBody Map<String, String> patch)
      throws InvocationTargetException, IllegalAccessException {
//      @RequestHeader(name = HttpHeaders.IF_MATCH) String etag) {
    MweUserDto dto = objectMapper.convertValue(patch, MweUserDto.class);
    System.out.println("patch");
    System.out.println(patch.get("ssbcount"));
    return userService.patch(id, dto);
  }

  @PostMapping("/requestpasswordreset")
  public void requestPasswordReset(@RequestBody Map<String, String> data) {

    VerificationTokenDto tokenDto = userService.generateVerificationToken(data.get("email"));

    eventPublisher.publishEvent(new OnGeneratedVerificationTokenEvent(tokenDto));

  }

}
