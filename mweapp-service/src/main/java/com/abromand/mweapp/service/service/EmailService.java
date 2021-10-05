package com.abromand.mweapp.service.service;

import com.abromand.mweapp.service.dto.VerificationTokenDto;

public interface EmailService {
  void sendVerificationEmail(VerificationTokenDto tokenDto);
}
