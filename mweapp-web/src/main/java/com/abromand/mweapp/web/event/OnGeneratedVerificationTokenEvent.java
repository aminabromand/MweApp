package com.abromand.mweapp.web.event;

import com.abromand.mweapp.service.dto.VerificationTokenDto;
import org.springframework.context.ApplicationEvent;

public class OnGeneratedVerificationTokenEvent extends ApplicationEvent {

  private final VerificationTokenDto tokenDto;

  public OnGeneratedVerificationTokenEvent(VerificationTokenDto tokenDto) {
    super(tokenDto);
    this.tokenDto = tokenDto;
  }

  public VerificationTokenDto getTokenDto() {
    return tokenDto;
  }
}
