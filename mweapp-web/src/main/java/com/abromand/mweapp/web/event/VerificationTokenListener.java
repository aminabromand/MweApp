package com.abromand.mweapp.web.event;

import com.abromand.mweapp.service.service.MweUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class VerificationTokenListener implements ApplicationListener<OnGeneratedVerificationTokenEvent> {

  @Autowired
  private MweUserService service;

  @Override
  public void onApplicationEvent(OnGeneratedVerificationTokenEvent event) {
    service.sendVerificationEmail(event.getTokenDto());
  }
}
