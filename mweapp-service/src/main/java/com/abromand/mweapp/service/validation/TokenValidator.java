package com.abromand.mweapp.service.validation;

import com.abromand.mweapp.data.repository.VerificationTokenRepository;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TokenValidator implements ConstraintValidator<ValidToken, String> {

  @Autowired
  VerificationTokenRepository repository;

  @Override
  public void initialize(ValidToken constraintAnnotation) {
  }

  @Override
  public boolean isValid(String token, ConstraintValidatorContext context){
    return (validate(token));
  }

  private boolean validate(String token) {
    return repository.findByTokenString(token).isPresent();
  }
}
